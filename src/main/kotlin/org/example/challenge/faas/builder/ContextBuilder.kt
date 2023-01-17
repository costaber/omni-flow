package org.example.challenge.faas.builder

import org.example.challenge.faas.domain.Context
import org.example.challenge.faas.domain.StepType

interface ContextBuilder : Builder<Context> {
    fun stepType() : StepType
}