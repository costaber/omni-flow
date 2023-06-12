package costaber.com.github.omniflow.model

data class UnaryExpression<T>(
    val term: Term<T>,
    val operator: UnaryOperator,
) : Expression