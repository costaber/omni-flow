package org.example.challenge.faas.domain

data class Step(
    val name: String,
    val description: String,
    val type: StepType,
    val metadata: Map<String, Any>,
)