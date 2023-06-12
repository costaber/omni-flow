package costaber.com.github.omniflow.model

data class Condition(
    val expression: Expression,
    val jump: String,
) : Node {

    override fun childNodes() = emptyList<Node>()
}
