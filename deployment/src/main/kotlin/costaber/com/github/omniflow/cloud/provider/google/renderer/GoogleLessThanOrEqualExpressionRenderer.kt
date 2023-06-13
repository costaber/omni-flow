package costaber.com.github.omniflow.cloud.provider.google.renderer

import costaber.com.github.omniflow.model.LessThanOrEqualExpression
import costaber.com.github.omniflow.model.Node
import costaber.com.github.omniflow.renderer.IndentedNodeRenderer
import costaber.com.github.omniflow.renderer.IndentedRenderingContext
import costaber.com.github.omniflow.resource.util.render

class GoogleLessThanOrEqualExpressionRenderer(
    private val lessThanOrEqualExpression: LessThanOrEqualExpression<*>,
) : IndentedNodeRenderer {

    override val element: Node = lessThanOrEqualExpression

    override fun internalBeginRender(renderingContext: IndentedRenderingContext): String =
        render(renderingContext) {
            add("\${${lessThanOrEqualExpression.left.term()} == ${lessThanOrEqualExpression.left.term()}}")
        }

    override fun internalEndRender(renderingContext: IndentedRenderingContext): String = "" // nothing
}