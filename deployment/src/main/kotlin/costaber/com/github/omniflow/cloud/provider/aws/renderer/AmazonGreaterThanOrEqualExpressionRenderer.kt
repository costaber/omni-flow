package costaber.com.github.omniflow.cloud.provider.aws.renderer

import costaber.com.github.omniflow.cloud.provider.aws.AMAZON_VARIABLE
import costaber.com.github.omniflow.model.GreaterThanOrEqualExpression
import costaber.com.github.omniflow.model.Node
import costaber.com.github.omniflow.model.Value
import costaber.com.github.omniflow.model.Variable
import costaber.com.github.omniflow.renderer.IndentedNodeRenderer
import costaber.com.github.omniflow.renderer.IndentedRenderingContext
import costaber.com.github.omniflow.resource.util.render

class AmazonGreaterThanOrEqualExpressionRenderer(
    private val greaterThanOrEqualExpression: GreaterThanOrEqualExpression<*>,
) : IndentedNodeRenderer {

    override val element: Node = greaterThanOrEqualExpression

    override fun internalBeginRender(renderingContext: IndentedRenderingContext): String =
        render(renderingContext) {
            addLine("$AMAZON_VARIABLE\"\$.${renderingContext}\",")
            when (greaterThanOrEqualExpression.right) {
                is Value<*> -> add("\"NumericGreaterThanEquals\": ")
                is Variable -> add("\"NumericGreaterThanEqualsPath\": ")
            }
            append("${greaterThanOrEqualExpression.right.term()},")
        }

    override fun internalEndRender(renderingContext: IndentedRenderingContext): String = "" // nothing
}