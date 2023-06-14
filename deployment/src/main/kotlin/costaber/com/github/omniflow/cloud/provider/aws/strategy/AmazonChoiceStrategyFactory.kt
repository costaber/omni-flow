package costaber.com.github.omniflow.cloud.provider.aws.strategy

import costaber.com.github.omniflow.cloud.provider.aws.renderer.AmazonChoiceRenderer
import costaber.com.github.omniflow.factory.NodeRendererStrategyFactory
import costaber.com.github.omniflow.model.Node
import costaber.com.github.omniflow.model.SwitchContext
import costaber.com.github.omniflow.predicate.DefaultPredicate
import costaber.com.github.omniflow.renderer.NodeRenderer
import java.util.function.Predicate

class AmazonChoiceStrategyFactory : NodeRendererStrategyFactory<String> {

    override fun getMatcher(): Predicate<Node> =
        DefaultPredicate(SwitchContext::class)

    override fun getRenderer(node: Node): NodeRenderer<String> =
        AmazonChoiceRenderer(node as SwitchContext)
}