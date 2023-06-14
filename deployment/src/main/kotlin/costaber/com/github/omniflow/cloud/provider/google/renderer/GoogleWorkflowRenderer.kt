package costaber.com.github.omniflow.cloud.provider.google.renderer

import costaber.com.github.omniflow.model.Node
import costaber.com.github.omniflow.model.Workflow
import costaber.com.github.omniflow.renderer.IndentedNodeRenderer
import costaber.com.github.omniflow.renderer.IndentedRenderingContext
import costaber.com.github.omniflow.resource.util.render

class GoogleWorkflowRenderer(private val workflow: Workflow) : IndentedNodeRenderer {

    override val element: Node = workflow

    override fun internalBeginRender(renderingContext: IndentedRenderingContext): String =
        render(renderingContext) {
            addLine("main:")
            incIndentationLevel()
            renderInput()
            add("steps:")
        }

    override fun internalEndRender(renderingContext: IndentedRenderingContext): String =
        render(renderingContext) {
            addLine("- returnOutput:")
            tab {
                add("return: \${${workflow.result}}")
            }
        }.apply { renderingContext.decIndentationLevel() }

    private fun IndentedRenderingContext.renderInput() {
        workflow.input?.let {
            addLine("params: [ ${workflow.input} ]")
        }
    }
}