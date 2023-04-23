package costaber.com.github.omniflow.model

data class Step(
    val name: String,
    val description: String,
    val type: StepType = StepType.EXECUTION,
    val context: StepContext,
) : Node {

    override fun childNodes(): List<Node> {
        return listOf(context)
    }

    override fun childNodeSize(): Int {
        return 1
    }

    override fun childNode(index: Int): Node {
        return context
    }
}