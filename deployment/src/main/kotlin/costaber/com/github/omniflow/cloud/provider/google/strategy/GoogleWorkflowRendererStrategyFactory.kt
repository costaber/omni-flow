package costaber.com.github.omniflow.cloud.provider.google.strategy

import costaber.com.github.omniflow.cloud.provider.google.predicate.GoogleWorkflowPredicate
import costaber.com.github.omniflow.cloud.provider.google.renderer.GoogleWorkflowRenderer
import costaber.com.github.omniflow.factory.NodeRendererStrategyFactory
import costaber.com.github.omniflow.model.Node
import costaber.com.github.omniflow.model.Workflow
import costaber.com.github.omniflow.renderer.LazyNodeRenderer
import java.util.function.Predicate

class GoogleWorkflowRendererStrategyFactory : NodeRendererStrategyFactory<String> {

    override fun getMatcher(): Predicate<Node> {
        return GoogleWorkflowPredicate()
    }

    override fun getRenderer(node: Node): LazyNodeRenderer<String> {
        return GoogleWorkflowRenderer(node as Workflow)
    }
}