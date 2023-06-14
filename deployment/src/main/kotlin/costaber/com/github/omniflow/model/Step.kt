package costaber.com.github.omniflow.model

data class Step(
    val name: String,
    val description: String,
    val type: StepType = StepType.CALL,
    val context: StepContext,
) : Node {

    override fun childNodes(): List<Node> {
        return listOf(context)
    }
}