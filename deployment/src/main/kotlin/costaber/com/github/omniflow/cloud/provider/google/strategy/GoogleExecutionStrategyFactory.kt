package costaber.com.github.omniflow.cloud.provider.google.strategy

import costaber.com.github.omniflow.cloud.provider.google.renderer.GoogleExecutionRenderer
import costaber.com.github.omniflow.factory.NodeRendererStrategyFactory
import costaber.com.github.omniflow.model.Node
import costaber.com.github.omniflow.model.execution.ExecutionContext
import costaber.com.github.omniflow.predicate.DefaultPredicate
import costaber.com.github.omniflow.renderer.LazyNodeRenderer
import java.util.function.Predicate

class GoogleExecutionStrategyFactory : NodeRendererStrategyFactory<String> {

    override fun getMatcher(): Predicate<Node> =
        DefaultPredicate(ExecutionContext::class)

    override fun getRenderer(node: Node): LazyNodeRenderer<String> =
        GoogleExecutionRenderer(node as ExecutionContext)
}