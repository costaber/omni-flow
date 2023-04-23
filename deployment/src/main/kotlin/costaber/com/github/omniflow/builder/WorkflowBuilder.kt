package costaber.com.github.omniflow.builder

import costaber.com.github.omniflow.model.Workflow

class WorkflowBuilder : Builder<Workflow> {

    private lateinit var name: String
    private lateinit var description: String
    private lateinit var params: String
    private lateinit var result: String
    private val steps = mutableListOf<StepBuilder>()

    fun name(value: String) = apply { this.name = value }

    fun description(value: String) = apply { this.description = value }

    fun params(value: String) = apply { this.params = value }

    fun steps(vararg value: StepBuilder) = apply { this.steps.addAll(value) }

    fun result(value: String) = apply { this.result = value }

    override fun build() = Workflow(
        name = name,
        input = params,
        description = description,
        steps = steps.map { it.build() },
        result = result
    )
}