package costaber.com.github.omniflow.cloud.provider.aws.renderer

import costaber.com.github.omniflow.cloud.provider.aws.AMAZON_VARIABLE
import costaber.com.github.omniflow.model.LessThanExpression
import costaber.com.github.omniflow.model.Node
import costaber.com.github.omniflow.model.Value
import costaber.com.github.omniflow.model.Variable
import costaber.com.github.omniflow.renderer.IndentedNodeRenderer
import costaber.com.github.omniflow.renderer.IndentedRenderingContext
import costaber.com.github.omniflow.resource.util.render

class AmazonLessThanExpressionRenderer(
    private val lessThanExpression: LessThanExpression<*>
): IndentedNodeRenderer {

    override val element: Node = lessThanExpression

    override fun internalBeginRender(renderingContext: IndentedRenderingContext): String =
        render(renderingContext) {
            addLine("$AMAZON_VARIABLE\"\$.${renderingContext}\",")
            when (lessThanExpression.right) {
                is Value<*> -> add("\"NumericLessThan\": ")
                is Variable -> add("\"NumericLessThanPath\": ")
            }
            add("${lessThanExpression.right.term()},")
        }

    override fun internalEndRender(renderingContext: IndentedRenderingContext): String = "" // nothing

}