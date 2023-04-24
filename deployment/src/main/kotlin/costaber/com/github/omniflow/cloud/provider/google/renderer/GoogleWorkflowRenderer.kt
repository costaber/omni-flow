package costaber.com.github.omniflow.cloud.provider.google.renderer

import costaber.com.github.omniflow.model.Node
import costaber.com.github.omniflow.model.Workflow
import costaber.com.github.omniflow.renderer.RenderingContext
import costaber.com.github.omniflow.resource.TAB

class GoogleWorkflowRenderer(private val workflow: Workflow) : GoogleRenderer {

    override val element: Node = workflow

    override fun internalBeginRender(renderingContext: RenderingContext): String {
        val prefix = getIndentationString(renderingContext)
        val workflowStringBuilder = StringBuilder()
        workflowStringBuilder.appendLine("main:")
        workflowStringBuilder.appendLine("${prefix}params: [ ${workflow.input} ]")
        workflowStringBuilder.append("${prefix}steps:")
        return workflowStringBuilder.toString()
    }

    override fun internalEndRender(renderingContext: RenderingContext): String {
        incIndentationLevel(renderingContext)
        val prefix = getIndentationString(renderingContext)
        val workflowStringBuilder = StringBuilder()
        workflowStringBuilder.appendLine("$prefix- returnOutput:")
        workflowStringBuilder.append("$prefix${TAB}return: ${workflow.result}")
        return workflowStringBuilder.toString()
    }
}