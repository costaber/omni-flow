package costaber.com.github.omniflow.cloud.provider.google.strategy

import costaber.com.github.omniflow.cloud.provider.google.predicate.GoogleStepPredicate
import costaber.com.github.omniflow.cloud.provider.google.renderer.GoogleStepRenderer
import costaber.com.github.omniflow.factory.NodeRendererStrategyFactory
import costaber.com.github.omniflow.model.Node
import costaber.com.github.omniflow.model.Step
import costaber.com.github.omniflow.renderer.LazyNodeRenderer
import java.util.function.Predicate

class GoogleStepStrategyFactory : NodeRendererStrategyFactory<String> {

    override fun getMatcher(): Predicate<Node> {
        return GoogleStepPredicate()
    }

    override fun getRenderer(node: Node): LazyNodeRenderer<String> {
        return GoogleStepRenderer(node as Step)
    }
}