package costaber.com.github.omniflow.cloud.provider.aws.deployer

import costaber.com.github.omniflow.cloud.provider.aws.renderer.AmazonRenderingContext
import costaber.com.github.omniflow.cloud.provider.aws.service.AmazonStateMachineService
import costaber.com.github.omniflow.cloud.provider.aws.strategy.AmazonPassStrategyFactory
import costaber.com.github.omniflow.cloud.provider.aws.strategy.AmazonStateMachineStrategyFactory
import costaber.com.github.omniflow.cloud.provider.aws.strategy.AmazonStateStrategyFactory
import costaber.com.github.omniflow.cloud.provider.aws.strategy.AmazonTaskStrategyFactory
import costaber.com.github.omniflow.deployer.CloudDeployer
import costaber.com.github.omniflow.factory.DefaultNodeRendererStrategyDecider
import costaber.com.github.omniflow.factory.NodeRendererStrategyDecider
import costaber.com.github.omniflow.model.Workflow
import costaber.com.github.omniflow.resource.util.joinToStringNewLines
import costaber.com.github.omniflow.traversor.DepthFirstNodeTraversor
import costaber.com.github.omniflow.visitor.NodeContextVisitor
import mu.KotlinLogging

class AmazonCloudDeployer internal constructor(
    private val nodeTraversor: DepthFirstNodeTraversor,
    private val contextVisitor: NodeContextVisitor,
    private val amazonStateMachineService: AmazonStateMachineService,
) : CloudDeployer<AmazonDeployContext> {

    private companion object {
        private val logger = KotlinLogging.logger { }
    }

    override fun deploy(workflow: Workflow, deployContext: AmazonDeployContext) {
        val context = AmazonRenderingContext()
        logger.info { "Starting to convert Workflow into a State Machine" }
        val content = nodeTraversor.traverse(contextVisitor, workflow, context)
            .filterNot(String::isEmpty)
            .joinToStringNewLines()
        println(content)
//        amazonStateMachineService.createStateMachine(
//            roleArn = deployContext.roleArn,
//            region = deployContext.region,
//            tags = deployContext.tags,
//            stateMachineName = deployContext.stateMachineName,
//            stateMachineDefinition = content,
//        )
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
                .addRendererStrategy(AmazonPassStrategyFactory())
                .build()
        }
    }
}