package costaber.com.github.omniflow.builder

import costaber.com.github.omniflow.model.*

class SwitchConditionBuilder : Builder<Condition> {

    private lateinit var match: Expression
    private lateinit var jump: String

    fun match(value: Expression) = apply { match = value }

    fun jump(value: String) = apply { jump = value }

    override fun build() = Condition(
        expression = match,
        jump = jump,
    )

    fun Expression.not() = UnaryExpression(
        expression = this,
        operator = UnaryOperator.NOT,
    )

    infix fun Expression.and(expression: Expression) = BinaryExpression(
        left = this,
        operator = BinaryOperator.AND,
        right = expression,
    )

    infix fun Expression.equalTo(expression: Expression) = BinaryExpression(
        left = this,
        operator = BinaryOperator.EQUAL_TO,
        right = expression,
    )

    infix fun Expression.greaterThan(expression: Expression) = BinaryExpression(
        left = this,
        operator = BinaryOperator.GREATER_THAN,
        right = expression,
    )

    infix fun Expression.lessThan(expression: Expression) = BinaryExpression(
        left = this,
        operator = BinaryOperator.LESS_THAN,
        right = expression,
    )

    infix fun Expression.or(expression: Expression) = BinaryExpression(
        left = this,
        operator = BinaryOperator.OR,
        right = expression,
    )

}