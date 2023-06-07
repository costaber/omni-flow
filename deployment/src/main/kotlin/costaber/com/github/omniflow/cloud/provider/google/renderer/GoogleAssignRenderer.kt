package costaber.com.github.omniflow.cloud.provider.google.renderer

import costaber.com.github.omniflow.model.AssignContext
import costaber.com.github.omniflow.model.Node
import costaber.com.github.omniflow.renderer.IndentedNodeRenderer
import costaber.com.github.omniflow.renderer.RenderingContext
import costaber.com.github.omniflow.resource.TAB

class GoogleAssignRenderer(private val assignContext: AssignContext) : IndentedNodeRenderer {

    override val element: Node = assignContext

    override fun internalBeginRender(renderingContext: RenderingContext): String {
        val prefix = getIndentationString(renderingContext)
        return buildString {
            append("${prefix}assign:")
            assignContext.variables.forEach {
                appendLine()
                append("${prefix}${TAB}- ${it.variable.name}: ${it.term.term()}")
            }
        }
    }

    override fun internalEndRender(renderingContext: RenderingContext) = "" // nothing
}