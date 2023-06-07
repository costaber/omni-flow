package costaber.com.github.omniflow.model

data class AssignContext(
    val variables: Collection<VariableInitialization<*>>
) : StepContext, Node {

    override fun childNodes(): List<Node> {
        return emptyList()
    }
}