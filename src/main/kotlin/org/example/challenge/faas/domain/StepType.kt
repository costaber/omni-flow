package org.example.challenge.faas.domain

enum class StepType {
    EXECUTION,
    CONDITIONAL,
    ITERATION,
}

fun getStepType(value: String): StepType =
    when(value) {
        "EXECUTION" -> StepType.EXECUTION
        "CONDITIONAL" -> StepType.CONDITIONAL
        "ITERATION" -> StepType.ITERATION
        else -> throw IllegalStateException("Invalid ${StepType::class.simpleName}: $value")
    }