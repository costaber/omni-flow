package costaber.com.github.omniflow.cloud.provider.google.renderer

import costaber.com.github.omniflow.model.Node
import costaber.com.github.omniflow.model.Workflow
import costaber.com.github.omniflow.renderer.IndentedNodeRenderer
import costaber.com.github.omniflow.renderer.IndentedRenderingContext
import costaber.com.github.omniflow.resource.util.render

class GoogleWorkflowRenderer(private val workflow: Workflow) : IndentedNodeRenderer {

    override val element: Node = workflow

    override fun internalBeginRender(renderingContext: IndentedRenderingContext): String {
//        val prefix = renderingContext.getIndentationString()
//        return buildString {
//            appendLine("main:")
//            renderInput(this, prefix)
//            append("${prefix}steps:")
//        }
        return render(renderingContext) {
            addLine("main:")
            incIndentationLevel()
            renderInput()
            add("steps:")
        }
    }

    override fun internalEndRender(renderingContext: IndentedRenderingContext): String {
//        renderingContext.incIndentationLevel()
//        val prefix = renderingContext.getIndentationString()
//        return buildString {
//            appendLine("$prefix- returnOutput:")
//            append("$prefix${TAB}return: \${${workflow.result}}")
//        }.apply { renderingContext.decIndentationLevel() }
        return render(renderingContext) {
            addLine("- returnOutput:")
            tab {
                add("return: \${${workflow.result}}")
            }
        }.apply { renderingContext.decIndentationLevel() }
    }

    private fun IndentedRenderingContext.renderInput() {
        workflow.input?.let {
            addLine("params: [ ${workflow.input} ]")
        }
    }

}