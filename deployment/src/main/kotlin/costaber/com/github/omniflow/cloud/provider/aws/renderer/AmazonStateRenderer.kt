package costaber.com.github.omniflow.cloud.provider.aws.renderer

import costaber.com.github.omniflow.model.Node
import costaber.com.github.omniflow.model.Step
import costaber.com.github.omniflow.renderer.IndentedNodeRenderer
import costaber.com.github.omniflow.renderer.RenderingContext
import costaber.com.github.omniflow.resource.TAB

class AmazonStateRenderer(private val step: Step) : IndentedNodeRenderer {

    override val element: Node = step

    override fun internalBeginRender(renderingContext: RenderingContext): String {
        val prefix = getIndentationString(renderingContext)
        return buildString {
            appendLine("${prefix}\"${step.name}\": {")
            append("${prefix}${TAB}\"Comment\": \"${step.description}\",")
        }
    }

    override fun internalEndRender(renderingContext: RenderingContext): String {
        val amazonContext = renderingContext as AmazonRenderingContext
        val prefix = getIndentationString(renderingContext)
        val hasNextStep = amazonContext.getNextStepNameAndAdvance() != null
        return prefix + (if (hasNextStep) "}," else "}")
    }

}