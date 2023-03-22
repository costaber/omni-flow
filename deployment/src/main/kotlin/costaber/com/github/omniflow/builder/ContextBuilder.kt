package costaber.com.github.omniflow.builder

import costaber.com.github.omniflow.model.Context
import costaber.com.github.omniflow.model.StepType

interface ContextBuilder : Builder<Context> {
    fun stepType(): StepType
}