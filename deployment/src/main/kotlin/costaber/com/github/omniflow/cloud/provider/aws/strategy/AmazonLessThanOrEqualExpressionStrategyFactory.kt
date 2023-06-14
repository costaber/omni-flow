package costaber.com.github.omniflow.cloud.provider.aws.strategy

import costaber.com.github.omniflow.cloud.provider.aws.renderer.AmazonLessThanOrEqualExpressionRenderer
import costaber.com.github.omniflow.factory.NodeRendererStrategyFactory
import costaber.com.github.omniflow.model.LessThanOrEqualExpression
import costaber.com.github.omniflow.model.Node
import costaber.com.github.omniflow.predicate.DefaultPredicate
import costaber.com.github.omniflow.renderer.NodeRenderer
import java.util.function.Predicate

class AmazonLessThanOrEqualExpressionStrategyFactory : NodeRendererStrategyFactory<String> {

    override fun getMatcher(): Predicate<Node> =
        DefaultPredicate(LessThanOrEqualExpression::class)

    override fun getRenderer(node: Node): NodeRenderer<String> =
        AmazonLessThanOrEqualExpressionRenderer(node as LessThanOrEqualExpression<*>)
}