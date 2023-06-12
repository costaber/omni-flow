package costaber.com.github.omniflow.model

data class BinaryExpression<T, R>(
    val left: Term<T>,
    val operator: BinaryOperator,
    val right: Term<R>,
) : Expression
