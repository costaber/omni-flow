package costaber.com.github.omniflow.cloud.provider.google.renderer

import costaber.com.github.omniflow.model.*
import costaber.com.github.omniflow.renderer.IndentedNodeRenderer
import costaber.com.github.omniflow.renderer.IndentedRenderingContext
import costaber.com.github.omniflow.resource.util.render

class GoogleConditionRenderer(private val condition: Condition) : IndentedNodeRenderer {

    override val element: Node = condition

    override fun internalBeginRender(renderingContext: IndentedRenderingContext): String =
        render(renderingContext) {
            addLine("- condition: ${renderExpression()}")
            add("  next: ${condition.jump}")
        }

    override fun internalEndRender(renderingContext: IndentedRenderingContext) = "" // nothing

    private fun renderExpression(): String {
        return when (condition.expression) {
            is Value<*> -> condition.expression.value.toString()
            is Variable -> "\${${condition.expression.name}}"
            is UnaryExpression<*> -> renderUnaryExpression(condition.expression)
            is BinaryExpression<*, *> -> renderBinaryExpression(condition.expression)
        }
    }

    private fun renderUnaryExpression(unaryExpression: UnaryExpression<*>): String {
        if (unaryExpression.term is Variable)
            "${unaryExpression.operator.render()}\${${unaryExpression.term.term()}}"
        return "${unaryExpression.operator.render()}${unaryExpression.term.term()}"
    }

    private fun renderBinaryExpression(binaryExpression: BinaryExpression<*, *>): String {
        val expression = "${binaryExpression.left.term()} " +
                "${binaryExpression.operator.render()} " +
                "${binaryExpression.right.term()}"
        if (binaryExpression.left is Variable)
            return "\${$expression}"
        return expression
    }

    private fun BinaryOperator.render(): String {
        return when (this) {
            BinaryOperator.AND -> "&&"
            BinaryOperator.EQUAL_TO -> "=="
            BinaryOperator.GREATER_THAN -> ">"
            BinaryOperator.LESS_THAN -> "<"
            BinaryOperator.OR -> "||"
        }
    }

    private fun UnaryOperator.render(): String {
        return when (this) {
            UnaryOperator.NOT -> "!"
        }
    }

}