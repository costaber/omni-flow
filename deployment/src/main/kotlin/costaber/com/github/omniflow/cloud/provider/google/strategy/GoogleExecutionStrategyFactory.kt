package costaber.com.github.omniflow.cloud.provider.google.strategy

import costaber.com.github.omniflow.cloud.provider.google.predicate.GoogleExecutionPredicate
import costaber.com.github.omniflow.cloud.provider.google.renderer.GoogleExecutionRenderer
import costaber.com.github.omniflow.factory.NodeRendererStrategyFactory
import costaber.com.github.omniflow.model.Node
import costaber.com.github.omniflow.model.execution.ExecutionContext
import costaber.com.github.omniflow.renderer.LazyNodeRenderer
import java.util.function.Predicate

class GoogleExecutionStrategyFactory : NodeRendererStrategyFactory<String> {

    override fun getMatcher(): Predicate<Node> {
        return GoogleExecutionPredicate()
    }

    override fun getRenderer(node: Node): LazyNodeRenderer<String> {
        return GoogleExecutionRenderer(node as ExecutionContext)
    }
}