package costaber.com.github.omniflow.factory

import costaber.com.github.omniflow.model.Node
import costaber.com.github.omniflow.renderer.ILazyNodeRenderer

interface INodeRendererStrategyDecider {
    fun decideRenderer(node: Node): ILazyNodeRenderer
}