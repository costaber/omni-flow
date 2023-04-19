package costaber.com.github.omniflow.factory

import costaber.com.github.omniflow.model.Node
import costaber.com.github.omniflow.renderer.ILazyNodeRenderer

class DefaultNodeRendererStrategyDecider private constructor(
    private val rendererStrategyFactories: List<INodeRendererStrategyFactory>,
): INodeRendererStrategyDecider {

    override fun decideRenderer(node: Node): ILazyNodeRenderer {
        return rendererStrategyFactories.stream()
            .filter{ it.getMatcher().test(node) }
            .findFirst()
            .map { it.getRenderer(node) }
            .orElseThrow()
    }

    class Builder {
        private val factories: MutableList<INodeRendererStrategyFactory> = mutableListOf()

        fun addRendererStrategy(
            elementRendererStrategyFactory :INodeRendererStrategyFactory
        ): Builder {
            factories.add(elementRendererStrategyFactory)
            return this
        }

        fun build(): DefaultNodeRendererStrategyDecider {
            return DefaultNodeRendererStrategyDecider(factories)
        }
    }
}