package costaber.com.github.omniflow.cloud.provider.google.deployer

import costaber.com.github.omniflow.cloud.provider.google.renderer.GoogleRenderingContext
import costaber.com.github.omniflow.cloud.provider.google.service.GoogleWorkflowService
import costaber.com.github.omniflow.cloud.provider.google.strategy.GoogleExecutionStrategyFactory
import costaber.com.github.omniflow.cloud.provider.google.strategy.GoogleStepStrategyFactory
import costaber.com.github.omniflow.cloud.provider.google.strategy.GoogleWorkflowRendererStrategyFactory
import costaber.com.github.omniflow.deployer.CloudDeployer
import costaber.com.github.omniflow.factory.DefaultNodeRendererStrategyDecider
import costaber.com.github.omniflow.factory.NodeRendererStrategyDecider
import costaber.com.github.omniflow.model.Workflow
import costaber.com.github.omniflow.traversor.NodeTraversor
import costaber.com.github.omniflow.visitor.NodeContextVisitor

class GoogleCloudDeployer(
    private val nodeTraversor: NodeTraversor,
    private val googleWorkflowService: GoogleWorkflowService,
) : CloudDeployer<GoogleDeployContext> {

    override fun deploy(workflow: Workflow, deployContext: GoogleDeployContext) {
        val context = GoogleRenderingContext(0)
        val contextVisitor = NodeContextVisitor(createStrategyFactory())
        val content = nodeTraversor.traverse(contextVisitor, workflow, context)
            .filter { it != "" }
            .joinToString("\n")
//        googleWorkflowService.deploy(
//            projectId = deployContext.projectId,
//            zone = deployContext.zone,
//            serviceAccount = deployContext.serviceAccount,
//            workflowId = deployContext.workflowId,
//            workflowDescription = deployContext.workflowDescription,
//            workflowLabels = deployContext.workflowLabels,
//            workflowSourceContents = content,
//        )
        print(content)
    }

    private fun createStrategyFactory(): NodeRendererStrategyDecider {
        return DefaultNodeRendererStrategyDecider.Builder()
            .addRendererStrategy(GoogleWorkflowRendererStrategyFactory())
            .addRendererStrategy(GoogleStepStrategyFactory())
            .addRendererStrategy(GoogleExecutionStrategyFactory())
            .build()
    }
}