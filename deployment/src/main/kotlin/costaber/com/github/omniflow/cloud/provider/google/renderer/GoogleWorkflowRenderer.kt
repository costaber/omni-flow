package costaber.com.github.omniflow.cloud.provider.google.renderer

import costaber.com.github.omniflow.model.Node
import costaber.com.github.omniflow.model.Workflow
import costaber.com.github.omniflow.renderer.IndentedNodeRenderer
import costaber.com.github.omniflow.renderer.RenderingContext
import costaber.com.github.omniflow.resource.TAB

class GoogleWorkflowRenderer(private val workflow: Workflow) : IndentedNodeRenderer {

    override val element: Node = workflow

    override fun internalBeginRender(renderingContext: RenderingContext): String {
        val prefix = getIndentationString(renderingContext)
        return buildString {
            appendLine("main:")
            appendLine("${prefix}params: [ ${workflow.input} ]")
            append("${prefix}steps:")
        }
    }

    override fun internalEndRender(renderingContext: RenderingContext): String {
        incIndentationLevel(renderingContext)
        val prefix = getIndentationString(renderingContext)
        return buildString {
            appendLine("$prefix- returnOutput:")
            append("$prefix${TAB}return: ${workflow.result}")
        }.apply { decIndentationLevel(renderingContext) }
    }
}

/*
fun withIdentation = with(getIdentationLevel())

class Merdas(val xpto: Int)

context(Merdas)
fun StringBuilder.appendLinesTab(s: String) {
    List(xpto) { TAB }.joinTo(this)
    appendLine(s)
}
*/