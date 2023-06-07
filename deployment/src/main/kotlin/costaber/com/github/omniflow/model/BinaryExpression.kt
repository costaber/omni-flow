package costaber.com.github.omniflow.model

data class BinaryExpression(
    val left: Expression,
    val operator: BinaryOperator,
    val right: Expression,
) : Expression
