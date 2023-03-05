package costaber.com.github.omniflow.builder

import costaber.com.github.omniflow.domain.Context
import costaber.com.github.omniflow.domain.StepType

interface ContextBuilder : Builder<Context> {
    fun stepType(): StepType
}