package costaber.com.github.omniflow.cloud.provider.aws.renderer

import costaber.com.github.omniflow.cloud.provider.aws.AMAZON_CHOICE_TYPE
import costaber.com.github.omniflow.cloud.provider.aws.AMAZON_CLOSE_ARRAY
import costaber.com.github.omniflow.cloud.provider.aws.AMAZON_DEFAULT
import costaber.com.github.omniflow.cloud.provider.aws.AMAZON_START_CHOICES
import costaber.com.github.omniflow.model.Node
import costaber.com.github.omniflow.model.SwitchContext
import costaber.com.github.omniflow.renderer.IndentedNodeRenderer
import costaber.com.github.omniflow.renderer.IndentedRenderingContext
import costaber.com.github.omniflow.resource.util.render

class AmazonChoiceRenderer(private val switchContext: SwitchContext) : IndentedNodeRenderer {

    override val element: Node = switchContext

    override fun internalBeginRender(renderingContext: IndentedRenderingContext): String =
        render(renderingContext) {
            addLine(AMAZON_CHOICE_TYPE)
            addLine(AMAZON_START_CHOICES)
        }

    override fun internalEndRender(renderingContext: IndentedRenderingContext): String =
        render(renderingContext) {
            add(AMAZON_CLOSE_ARRAY)
            switchContext.default?.let {
                addEmptyLine()
                add("$AMAZON_DEFAULT$it")
            }
        }
}