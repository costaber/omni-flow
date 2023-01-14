package org.example.challenge.faas.builder

import org.example.challenge.faas.domain.StepType
import org.example.challenge.faas.domain.getStepType

class StepTypeBuilder: Builder<StepType> {

    private lateinit var type: String

    fun type(value: String) = apply { this.type = value }

    override fun build() =
        getStepType(type)
}