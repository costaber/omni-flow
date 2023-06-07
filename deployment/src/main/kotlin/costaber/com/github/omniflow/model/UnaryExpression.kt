package costaber.com.github.omniflow.model

data class UnaryExpression(
    val expression: Expression,
    val operator: UnaryOperator,
) : Expression