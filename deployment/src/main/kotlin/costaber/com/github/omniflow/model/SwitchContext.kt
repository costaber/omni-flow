package costaber.com.github.omniflow.model

data class SwitchContext(
    val conditions: Collection<Condition>,
    val default: String?,
) : StepContext, Node {

    override fun childNodes(): List<Node> {
        return emptyList()
    }
}
