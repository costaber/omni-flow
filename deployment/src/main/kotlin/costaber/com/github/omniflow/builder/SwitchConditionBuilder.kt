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

    fun <T> Term<T>.not() = UnaryExpression(
        term = this,
        operator = UnaryOperator.NOT,
    )

    infix fun <T, R> Term<T>.and(rightTerm: Term<R>) = BinaryExpression(
        left = this,
        operator = BinaryOperator.AND,
        right = rightTerm,
    )

    infix fun <T, R> Term<T>.equalTo(rightTerm: Term<R>) = BinaryExpression(
        left = this,
        operator = BinaryOperator.EQUAL_TO,
        right = rightTerm,
    )

    infix fun <T, R> Term<T>.greaterThan(rightTerm: Term<R>) = BinaryExpression(
        left = this,
        operator = BinaryOperator.GREATER_THAN,
        right = rightTerm,
    )

    infix fun <T, R> Term<T>.lessThan(rightTerm: Term<R>) = BinaryExpression(
        left = this,
        operator = BinaryOperator.LESS_THAN,
        right = rightTerm,
    )

    infix fun <T, R> Term<T>.or(rightTerm: Term<R>) = BinaryExpression(
        left = this,
        operator = BinaryOperator.OR,
        right = rightTerm,
    )

}