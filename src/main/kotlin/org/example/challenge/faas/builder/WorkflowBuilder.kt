package org.example.challenge.faas.builder

import org.example.challenge.faas.domain.Workflow

class WorkflowBuilder : Builder<Workflow> {

    private lateinit var name: String
    private lateinit var description: String
    private val steps = mutableListOf<StepBuilder>()

    fun name(value: String) = apply { this.name = value }

    fun description(value: String) = apply { this.description = value }

    fun step(stepBuilder: StepBuilder) = apply { this.steps.add(stepBuilder) }

    override fun build() = Workflow(
        name = name,
        description = description,
        definition = steps.map { it.build() }
    )
}