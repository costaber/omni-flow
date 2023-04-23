package costaber.com.github.omniflow.factory

import costaber.com.github.omniflow.model.Node
import costaber.com.github.omniflow.renderer.LazyNodeRenderer

class DefaultNodeRendererStrategyDecider private constructor(
    private val rendererStrategyFactories: List<NodeRendererStrategyFactory<*>>,
) : NodeRendererStrategyDecider {

    override fun decideRenderer(node: Node): LazyNodeRenderer<*> {
        return rendererStrategyFactories.stream()
            .filter { it.getMatcher().test(node) }
            .findFirst()
            .map { it.getRenderer(node) }
            .orElseThrow()
    }

    class Builder {
        private val factories: MutableList<NodeRendererStrategyFactory<*>> = mutableListOf()

        fun addRendererStrategy(
            elementRendererStrategyFactory: NodeRendererStrategyFactory<*>
        ): Builder {
            factories.add(elementRendererStrategyFactory)
            return this
        }

        fun build(): DefaultNodeRendererStrategyDecider {
            return DefaultNodeRendererStrategyDecider(factories)
        }
    }
}