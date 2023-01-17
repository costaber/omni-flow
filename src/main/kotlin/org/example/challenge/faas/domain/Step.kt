package org.example.challenge.faas.domain

open class Step(
    val name: String,
    val description: String,
    val type: StepType = StepType.EXECUTION,
    val parameters: Map<String, Any> = emptyMap(),
)