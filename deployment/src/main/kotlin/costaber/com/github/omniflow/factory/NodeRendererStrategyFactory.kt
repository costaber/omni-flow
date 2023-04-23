package costaber.com.github.omniflow.factory

import costaber.com.github.omniflow.model.Node
import costaber.com.github.omniflow.renderer.LazyNodeRenderer
import java.util.function.Predicate

interface NodeRendererStrategyFactory<R> {
    fun getMatcher(): Predicate<Node>

    fun getRenderer(node: Node): LazyNodeRenderer<R>
}