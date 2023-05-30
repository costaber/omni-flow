package costaber.com.github.omniflow.model.variable

import costaber.com.github.omniflow.model.Node
import costaber.com.github.omniflow.model.StepContext

data class AssignContext(val variables: Collection<VariableInitialization<*>>) : StepContext, Node {

    override fun childNodes(): List<Node> {
        return emptyList()
    }
}