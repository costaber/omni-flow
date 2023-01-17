package org.example.challenge.faas.builder

import org.example.challenge.faas.domain.Workflow
import org.example.challenge.faas.domain.getCloudProvider

class WorkflowBuilder : Builder<Workflow> {

    private lateinit var zone: String
    private lateinit var provider: String
    private lateinit var name: String
    private lateinit var description: String
    private lateinit var result: String
    private val steps = mutableListOf<StepBuilder>()

    fun zone(value: String) = apply { this.zone = value }

    fun provider(value: String) = apply { this.provider = value }

    fun name(value: String) = apply { this.name = value }

    fun description(value: String) = apply { this.description = value }

    fun step(vararg value: StepBuilder) = apply { this.steps.addAll(value) }

    fun result(value: String) = apply { this.result = value }

    override fun build() = Workflow(
        zone = zone,
        provider = getCloudProvider(provider),
        name = name,
        description = description,
        definition = steps.map { it.build() },
        result = result
    )
}