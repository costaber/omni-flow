package costaber.com.github.omniflow.model

data class Workflow(
    val name: String,
    val description: String? = null,
    val input: String? = null,
    val variables: Map<String, Variable<*>> = emptyMap(),
    val steps: Collection<Step> = emptyList(),
    val result: String,
) : Node {

    override fun childNodes(): List<Node> {
        return steps.toList()
    }

    override fun childNodeSize(): Int {
        return steps.size
    }

    override fun childNode(index: Int): Node {
        return steps.elementAt(index)
    }
}