package costaber.com.github.omniflow.cloud.provider.aws.strategy

import costaber.com.github.omniflow.cloud.provider.aws.renderer.AmazonConditionRenderer
import costaber.com.github.omniflow.factory.NodeRendererStrategyFactory
import costaber.com.github.omniflow.model.Condition
import costaber.com.github.omniflow.model.Node
import costaber.com.github.omniflow.predicate.DefaultPredicate
import costaber.com.github.omniflow.renderer.NodeRenderer
import java.util.function.Predicate

class AmazonConditionStrategyFactory : NodeRendererStrategyFactory<String> {

    override fun getMatcher(): Predicate<Node> =
        DefaultPredicate(Condition::class)

    override fun getRenderer(node: Node): NodeRenderer<String> =
        AmazonConditionRenderer(node as Condition)
}