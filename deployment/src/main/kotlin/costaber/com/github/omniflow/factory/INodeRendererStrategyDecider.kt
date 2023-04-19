package costaber.com.github.omniflow.factory

import costaber.com.github.omniflow.model.Node
import costaber.com.github.omniflow.renderer.ILazyElementRenderer

interface IElementRendererStrategyDecider {
    fun decideRenderer(node: Node): ILazyElementRenderer
}