package costaber.com.github.omniflow.cloud.provider.aws.renderer

import costaber.com.github.omniflow.cloud.provider.aws.AMAZON_COMMENT
import costaber.com.github.omniflow.cloud.provider.aws.AMAZON_START_AT
import costaber.com.github.omniflow.cloud.provider.aws.AMAZON_STATES
import costaber.com.github.omniflow.model.Node
import costaber.com.github.omniflow.model.Workflow
import costaber.com.github.omniflow.renderer.IndentedNodeRenderer
import costaber.com.github.omniflow.renderer.RenderingContext
import costaber.com.github.omniflow.resource.TAB

class AmazonStateMachineRenderer(private val workflow: Workflow) : IndentedNodeRenderer {

    override val element: Node = workflow

    override fun internalBeginRender(renderingContext: RenderingContext): String {
        // TODO: the steps are mandatory so, make validation somewhere

        // Add steps to the context
        val context = renderingContext as AmazonRenderingContext
        context.setSteps(workflow.steps)

        val prefix = getIndentationString(renderingContext)
        val firstStepName = context.getNextStepNameAndAdvance()
        return buildString {
            appendLine("{")
            appendLine("${prefix}${AMAZON_COMMENT}: \"${workflow.description}\",")
            appendLine("${prefix}${AMAZON_START_AT}: \"${firstStepName}\",")
            append("${TAB}${AMAZON_STATES}: {")
        }
    }

    override fun internalEndRender(renderingContext: RenderingContext): String {
        val prefix = getIndentationString(renderingContext)
        return buildString {
            appendLine("${prefix}}")
            append("}")
        }
    }

}