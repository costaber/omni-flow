package costaber.com.github.omniflow.cloud.provider.amazon.renderer

import costaber.com.github.omniflow.cloud.provider.amazon.AMAZON_CHOICE_TYPE
import costaber.com.github.omniflow.cloud.provider.amazon.AMAZON_CLOSE_ARRAY
import costaber.com.github.omniflow.cloud.provider.amazon.AMAZON_DEFAULT
import costaber.com.github.omniflow.cloud.provider.amazon.AMAZON_START_CHOICES
import costaber.com.github.omniflow.model.Node
import costaber.com.github.omniflow.model.ConditionalContext
import costaber.com.github.omniflow.renderer.IndentedNodeRenderer
import costaber.com.github.omniflow.renderer.IndentedRenderingContext
import costaber.com.github.omniflow.resource.util.render

class AmazonChoiceRenderer(private val conditionalContext: ConditionalContext) : IndentedNodeRenderer {

    override val element: Node = conditionalContext

    override fun internalBeginRender(renderingContext: IndentedRenderingContext): String {
        val amazonRenderingContext = renderingContext as AmazonRenderingContext
        amazonRenderingContext.setConditions(conditionalContext.conditions)
        return render(renderingContext) {
            addLine(AMAZON_CHOICE_TYPE)
            add(AMAZON_START_CHOICES)
        }
    }

    override fun internalEndRender(renderingContext: IndentedRenderingContext): String =
        render(renderingContext) {
            add(AMAZON_CLOSE_ARRAY)
            conditionalContext.default?.let {
                addEmptyLine()
                add("$AMAZON_DEFAULT\"$it\"")
            }
        }
}