package costaber.com.github.omniflow.cloud.provider.aws.deployer

import costaber.com.github.omniflow.cloud.provider.aws.renderer.AmazonRenderingContext
import costaber.com.github.omniflow.cloud.provider.aws.service.AmazonStateMachineService
import costaber.com.github.omniflow.cloud.provider.aws.strategy.AmazonStateMachineStrategyFactory
import costaber.com.github.omniflow.cloud.provider.aws.strategy.AmazonStateStrategyFactory
import costaber.com.github.omniflow.cloud.provider.aws.strategy.AmazonTaskStrategyFactory
import costaber.com.github.omniflow.deployer.CloudDeployer
import costaber.com.github.omniflow.factory.DefaultNodeRendererStrategyDecider
import costaber.com.github.omniflow.factory.NodeRendererStrategyDecider
import costaber.com.github.omniflow.model.Workflow
import costaber.com.github.omniflow.traversor.DepthFirstNodeTraversor
import costaber.com.github.omniflow.visitor.NodeContextVisitor

class AmazonCloudDeployer internal constructor(
    private val nodeTraversor: DepthFirstNodeTraversor,
    private val contextVisitor: NodeContextVisitor,
    private val amazonStateMachineService: AmazonStateMachineService,
) : CloudDeployer<AmazonDeployContext> {

    override fun deploy(workflow: Workflow, deployContext: AmazonDeployContext) {
        val context = AmazonRenderingContext()
        val content = nodeTraversor.traverse(contextVisitor, workflow, context)
            .filter { it != "" }
            .joinToString("\n")
        amazonStateMachineService.createStateMachine(
            roleArn = deployContext.roleArn,
            region = deployContext.region,
            tags = deployContext.tags,
            stateMachineName = deployContext.stateMachineName,
            stateMachineDefinition = content,
        )
    }

    class Builder {
        fun build() = AmazonCloudDeployer(
            nodeTraversor = DepthFirstNodeTraversor(),
            contextVisitor = NodeContextVisitor(createNodeRendererStrategyDecider()),
            amazonStateMachineService = AmazonStateMachineService()
        )

        private fun createNodeRendererStrategyDecider(): NodeRendererStrategyDecider {
            return DefaultNodeRendererStrategyDecider.Builder()
                .addRendererStrategy(AmazonStateMachineStrategyFactory())
                .addRendererStrategy(AmazonStateStrategyFactory())
                .addRendererStrategy(AmazonTaskStrategyFactory())
                .build()
        }
    }
}