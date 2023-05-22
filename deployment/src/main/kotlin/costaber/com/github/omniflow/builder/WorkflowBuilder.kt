package costaber.com.github.omniflow.builder

import costaber.com.github.omniflow.model.Workflow

class WorkflowBuilder : Builder<Workflow> {

    // mandatory
    private lateinit var name: String
    private lateinit var description: String
    private val steps = mutableListOf<StepBuilder>()
    private lateinit var result: VariableBuilder<Unit>

    // optional
    private var params: VariableBuilder<Unit>? = null
    private val variables = mutableListOf<VariableBuilder<*>>()

    fun name(value: String) = apply { this.name = value }

    fun description(value: String) = apply { this.description = value }

    fun params(value: VariableBuilder<Unit>) = apply { this.params = value }

    fun variables(vararg value: VariableBuilder<Any>) = apply { this.variables.addAll(value) }

    fun steps(vararg value: StepBuilder) = apply { this.steps.addAll(value) }

    fun result(value: VariableBuilder<Unit>) = apply { this.result = value }

    override fun build() = Workflow(
        name = name,
        description = description,
        input = params?.build(),
        variables = variables.map { it.build() }
            .associateBy { name },
        steps = steps.map { it.build() },
        result = result.build()
    )
}