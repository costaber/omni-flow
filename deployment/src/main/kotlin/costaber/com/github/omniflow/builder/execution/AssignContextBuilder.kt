package costaber.com.github.omniflow.builder.execution

import costaber.com.github.omniflow.builder.ContextBuilder
import costaber.com.github.omniflow.model.StepType
import costaber.com.github.omniflow.model.variable.AssignContext
import costaber.com.github.omniflow.model.variable.Value
import costaber.com.github.omniflow.model.variable.Variable
import costaber.com.github.omniflow.model.variable.VariableInitialization

class AssignContextBuilder : ContextBuilder {

    private val variables: MutableList<Pair<String, Any>> = mutableListOf()

    fun variable(vararg value: Pair<String, Any>) = apply { this.variables.addAll(value) }

    infix fun String.equal(value: Any) = Pair(this, value)

    override fun stepType() = StepType.ASSIGN

    override fun build() = AssignContext(
        variables = variables.map {
            VariableInitialization(
                name = Variable(it.first),
                value = Value(it.second)
            )
        }
    )
}