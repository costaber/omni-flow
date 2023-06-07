package costaber.com.github.omniflow.builder

import costaber.com.github.omniflow.model.*

class AssignContextBuilder : ContextBuilder {

    private val variables: MutableList<Pair<String, Any>> = mutableListOf()

    fun variable(vararg value: Pair<String, Any>) = apply { this.variables.addAll(value) }

    infix fun String.equal(value: Any) = Pair(this, value)

    override fun stepType() = StepType.ASSIGN

    override fun build() = AssignContext(
        variables = variables.map {
            VariableInitialization(
                variable = Variable(it.first),
                term = Value(it.second)
            )
        }
    )
}