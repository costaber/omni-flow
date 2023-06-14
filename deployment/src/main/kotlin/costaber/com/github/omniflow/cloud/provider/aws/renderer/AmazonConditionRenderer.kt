package costaber.com.github.omniflow.cloud.provider.aws.renderer

import costaber.com.github.omniflow.cloud.provider.aws.AMAZON_CLOSE_OBJECT
import costaber.com.github.omniflow.cloud.provider.aws.AMAZON_NEXT
import costaber.com.github.omniflow.model.Condition
import costaber.com.github.omniflow.model.Node
import costaber.com.github.omniflow.renderer.IndentedNodeRenderer
import costaber.com.github.omniflow.renderer.IndentedRenderingContext
import costaber.com.github.omniflow.resource.util.render

class AmazonConditionRenderer(private val condition: Condition) : IndentedNodeRenderer {

    override val element: Node = condition

    override fun internalBeginRender(renderingContext: IndentedRenderingContext): String =
        render(renderingContext) {
            add("{")
        }

    override fun internalEndRender(renderingContext: IndentedRenderingContext): String {
        val amazonContext = renderingContext as AmazonRenderingContext
        return render(renderingContext) {
            tab {
                addLine("$AMAZON_NEXT\"${condition.jump}\"")
            }
            if (amazonContext.isLastCondition(condition)) {
                add("}")
            } else {
                add(AMAZON_CLOSE_OBJECT)
            }
        }
    }
}