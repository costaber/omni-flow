package costaber.com.github.omniflow.builder

import costaber.com.github.omniflow.model.Value
import costaber.com.github.omniflow.model.Workflow

class WorkflowBuilder : Builder<Workflow> {

    // mandatory
    private lateinit var name: String
    private lateinit var description: String
    private val steps = mutableListOf<StepBuilder>()
    private lateinit var result: Value

    // optional
    private var params: Value? = null
    private val variables = mutableListOf<VariableBuilder<*>>()

    fun name(value: String) = apply { this.name = value }

    fun description(value: String) = apply { this.description = value }

    fun params(value: Value) = apply { this.params = value }

    fun variables(vararg value: VariableBuilder<Any>) = apply { this.variables.addAll(value) }

    fun steps(vararg value: StepBuilder) = apply { this.steps.addAll(value) }

    fun result(value: Value) = apply { this.result = value }

    override fun build() = Workflow(
        name = name,
        description = description,
        input = params,
        variables = variables.map { it.build() }
            .associateBy { name },
        steps = steps.map { it.build() },
        result = result
    )
}