package costaber.com.github.omniflow.cloud.provider.google.deployer

import costaber.com.github.omniflow.cloud.provider.google.service.GoogleWorkflowService
import costaber.com.github.omniflow.cloud.provider.google.strategy.GoogleAssignStrategyFactory
import costaber.com.github.omniflow.cloud.provider.google.strategy.GoogleCallStrategyFactory
import costaber.com.github.omniflow.cloud.provider.google.strategy.GoogleStepStrategyFactory
import costaber.com.github.omniflow.cloud.provider.google.strategy.GoogleWorkflowRendererStrategyFactory
import costaber.com.github.omniflow.deployer.CloudDeployer
import costaber.com.github.omniflow.factory.DefaultNodeRendererStrategyDecider
import costaber.com.github.omniflow.factory.NodeRendererStrategyDecider
import costaber.com.github.omniflow.model.Workflow
import costaber.com.github.omniflow.renderer.IndentedRenderingContext
import costaber.com.github.omniflow.resource.util.joinToStringNewLines
import costaber.com.github.omniflow.traversor.DepthFirstNodeTraversor
import costaber.com.github.omniflow.visitor.NodeContextVisitor
import mu.KotlinLogging

class GoogleCloudDeployer internal constructor(
    private val nodeTraversor: DepthFirstNodeTraversor,
    private val contextVisitor: NodeContextVisitor,
    private val googleWorkflowService: GoogleWorkflowService,
) : CloudDeployer<GoogleDeployContext> {

    private companion object {
        private val logger = KotlinLogging.logger { }
    }

    override fun deploy(workflow: Workflow, deployContext: GoogleDeployContext) {
        val context = IndentedRenderingContext(0)
        logger.info { "Starting to convert Workflow into a Workflow" }
        val content = nodeTraversor.traverse(contextVisitor, workflow, context)
            .filterNot(String::isEmpty)
            .joinToStringNewLines()
        googleWorkflowService.deploy(
            projectId = deployContext.projectId,
            zone = deployContext.zone,
            serviceAccount = deployContext.serviceAccount,
            workflowId = deployContext.workflowId,
            workflowDescription = deployContext.workflowDescription,
            workflowLabels = deployContext.workflowLabels,
            workflowSourceContents = content,
        )
    }

    class Builder {
        fun build() = GoogleCloudDeployer(
            nodeTraversor = DepthFirstNodeTraversor(),
            contextVisitor = NodeContextVisitor(createNodeRendererStrategyDecider()),
            googleWorkflowService = GoogleWorkflowService(),
        )

        private fun createNodeRendererStrategyDecider(): NodeRendererStrategyDecider {
            return DefaultNodeRendererStrategyDecider.Builder()
                .addRendererStrategy(GoogleWorkflowRendererStrategyFactory())
                .addRendererStrategy(GoogleStepStrategyFactory())
                .addRendererStrategy(GoogleCallStrategyFactory())
                .addRendererStrategy(GoogleAssignStrategyFactory())
                .build()
        }
    }
}