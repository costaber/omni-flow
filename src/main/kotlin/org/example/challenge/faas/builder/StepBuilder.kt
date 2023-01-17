package org.example.challenge.faas.builder

import org.example.challenge.faas.domain.Step

open class StepBuilder : Builder<Step> {

    private lateinit var name: String
    private lateinit var description: String
    private lateinit var contextBuilder: ContextBuilder

    fun name(value: String) = apply { this.name = value }

    fun description(value: String) = apply { this.description = value }

    fun context(value: ContextBuilder) = apply { this.contextBuilder = value }

    override fun build() = Step(
        name = name,
        description = description,
        type = contextBuilder.stepType(),
        context = contextBuilder.build()
    )
}