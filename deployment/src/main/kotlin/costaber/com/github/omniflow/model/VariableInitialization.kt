package costaber.com.github.omniflow.model

import costaber.com.github.omniflow.model.variable.Value
import costaber.com.github.omniflow.model.variable.Variable

data class VariableInitialization<T : Any>(
    val name: Variable,
    val value: Value<T>,
) : Node {

    override fun childNodes(): List<Node> {
        return emptyList()
    }
}