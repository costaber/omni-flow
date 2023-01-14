package org.example.challenge.faas.domain

data class Step<Argument, Result>(
    val name: String,
    val description: String,
    val type: StepType,
    val action: (Argument) -> Result,
    val metadata: Map<String, *>,
)