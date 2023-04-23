package costaber.com.github.omniflow.factory

import costaber.com.github.omniflow.model.Node
import costaber.com.github.omniflow.renderer.LazyNodeRenderer

interface NodeRendererStrategyDecider {
    fun decideRenderer(node: Node): LazyNodeRenderer<*>
}