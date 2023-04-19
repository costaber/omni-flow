package costaber.com.github.omniflow.factory

import costaber.com.github.omniflow.model.Node
import costaber.com.github.omniflow.renderer.ILazyNodeRenderer
import java.util.function.Predicate

interface INodeRendererStrategyFactory {
    fun getMatcher(): Predicate<Node>

    fun getRenderer(node: Node): ILazyNodeRenderer
}