package costaber.com.github.omniflow.cloud.provider.aws.strategy

import costaber.com.github.omniflow.cloud.provider.aws.renderer.AmazonTaskRenderer
import costaber.com.github.omniflow.factory.NodeRendererStrategyFactory
import costaber.com.github.omniflow.model.Node
import costaber.com.github.omniflow.model.execution.ExecutionContext
import costaber.com.github.omniflow.predicate.DefaultPredicate
import costaber.com.github.omniflow.renderer.NodeRenderer
import java.util.function.Predicate

class AmazonTaskStrategyFactory : NodeRendererStrategyFactory<String> {
    override fun getMatcher(): Predicate<Node> =
        DefaultPredicate(ExecutionContext::class)

    override fun getRenderer(node: Node): NodeRenderer<String> =
        AmazonTaskRenderer(node as ExecutionContext)
}