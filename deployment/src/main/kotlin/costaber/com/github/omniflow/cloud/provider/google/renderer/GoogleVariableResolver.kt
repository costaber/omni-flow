package costaber.com.github.omniflow.cloud.provider.google.renderer

import costaber.com.github.omniflow.model.Node
import costaber.com.github.omniflow.model.VariableInitialization
import costaber.com.github.omniflow.renderer.IndentedNodeRenderer
import costaber.com.github.omniflow.renderer.IndentedRenderingContext
import costaber.com.github.omniflow.resource.util.render

class GoogleVariableResolver(
    private val variableInitialization: VariableInitialization<*>,
    private val googleTermResolver: GoogleTermResolver,
) : IndentedNodeRenderer() {

    override val element: Node = variableInitialization

    override fun internalBeginRender(renderingContext: IndentedRenderingContext): String =
        render(renderingContext) {
            val term = googleTermResolver.resolve(variableInitialization.term, termContext)
            add("- ${variableInitialization.variable.name}: $term")
        }

    override fun internalEndRender(renderingContext: IndentedRenderingContext) = "" // nothing
}