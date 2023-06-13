package costaber.com.github.omniflow.cloud.provider.aws.renderer

import costaber.com.github.omniflow.cloud.provider.aws.AMAZON_CLOSE_OBJECT
import costaber.com.github.omniflow.cloud.provider.aws.AMAZON_COMMENT
import costaber.com.github.omniflow.model.Node
import costaber.com.github.omniflow.model.Step
import costaber.com.github.omniflow.renderer.IndentedNodeRenderer
import costaber.com.github.omniflow.renderer.IndentedRenderingContext
import costaber.com.github.omniflow.resource.util.render

class AmazonStateRenderer(private val step: Step) : IndentedNodeRenderer {

    override val element: Node = step

    override fun internalBeginRender(renderingContext: IndentedRenderingContext): String =
        render(renderingContext) {
            addLine("\"${step.name}\": {")
            tab {
                add("${AMAZON_COMMENT}\"${step.description}\",")
            }
        }

    override fun internalEndRender(renderingContext: IndentedRenderingContext): String {
        val amazonContext = renderingContext as AmazonRenderingContext
        return render(renderingContext) {
            if (amazonContext.getNextStepNameAndAdvance() != null) {
                add(AMAZON_CLOSE_OBJECT)
            } else {
                add("}")
            }
        }
    }

}