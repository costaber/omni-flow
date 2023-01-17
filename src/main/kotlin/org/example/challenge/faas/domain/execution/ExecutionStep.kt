package org.example.challenge.faas.domain.execution

import org.example.challenge.faas.domain.Step
import org.example.challenge.faas.domain.StepType

class ExecutionStep(
    name: String,
    description: String,
    executionContext: ExecutionContext,
) : Step(name, description, StepType.EXECUTION, executionContext)