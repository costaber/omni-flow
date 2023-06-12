package costaber.com.github.omniflow.cloud.provider.aws.renderer

import costaber.com.github.omniflow.cloud.provider.aws.AMAZON_COMMENT
import costaber.com.github.omniflow.model.Node
import costaber.com.github.omniflow.model.Step
import costaber.com.github.omniflow.renderer.IndentedNodeRenderer
import costaber.com.github.omniflow.renderer.IndentedRenderingContext
import costaber.com.github.omniflow.resource.TAB

class AmazonStateRenderer(private val step: Step) : IndentedNodeRenderer {

    override val element: Node = step

    override fun internalBeginRender(renderingContext: IndentedRenderingContext): String {
        val prefix = renderingContext.getIndentationString()
        return buildString {
            appendLine("${prefix}\"${step.name}\": {")
            append("${prefix}${TAB}${AMAZON_COMMENT}: \"${step.description}\",")
        }
    }

    override fun internalEndRender(renderingContext: IndentedRenderingContext): String {
        val amazonContext = renderingContext as AmazonRenderingContext
        val prefix = amazonContext.getIndentationString()
        val hasNextStep = amazonContext.getNextStepNameAndAdvance() != null
        return prefix + (if (hasNextStep) "}," else "}")
    }

}