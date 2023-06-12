package costaber.com.github.omniflow.cloud.provider.google.renderer

import costaber.com.github.omniflow.model.Node
import costaber.com.github.omniflow.model.VariableInitialization
import costaber.com.github.omniflow.renderer.IndentedNodeRenderer
import costaber.com.github.omniflow.renderer.IndentedRenderingContext
import costaber.com.github.omniflow.resource.util.render

class GoogleVariableInitializationResolver(
    private val variableInitialization: VariableInitialization<*>
) : IndentedNodeRenderer {

    override val element: Node = variableInitialization

    override fun internalBeginRender(renderingContext: IndentedRenderingContext): String =
        render(renderingContext) {
            add("- ${variableInitialization.variable.name}: ${variableInitialization.term.term()}")
        }

    override fun internalEndRender(renderingContext: IndentedRenderingContext) = "" // nothing

}