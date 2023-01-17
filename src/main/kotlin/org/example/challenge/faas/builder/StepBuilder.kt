package org.example.challenge.faas.builder

import org.example.challenge.faas.domain.Step

class StepBuilder : Builder<Step> {

    private lateinit var name: String
    private lateinit var description: String
    private lateinit var stepTypeBuilder: StepTypeBuilder
    private val metadata: MutableMap<String, Any> = mutableMapOf()

    fun name(value: String) = apply { this.name = value }

    fun description(value: String) = apply { this.description = value }

    fun type(value: StepTypeBuilder) = apply { this.stepTypeBuilder = stepTypeBuilder }

    fun metadata(key: String, value: String) = apply { this.metadata[key] = value }

    override fun build() = Step(
        name = name,
        description = description,
        type = stepTypeBuilder.build(),
        parameters = metadata
    )
}