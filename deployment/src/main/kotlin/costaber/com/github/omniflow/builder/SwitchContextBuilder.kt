package costaber.com.github.omniflow.builder

import costaber.com.github.omniflow.model.StepType
import costaber.com.github.omniflow.model.SwitchContext

class SwitchContextBuilder : ContextBuilder {

    private val conditions: MutableList<SwitchConditionBuilder> = mutableListOf()
    private var default: String? = null

    fun conditions(vararg value: SwitchConditionBuilder) = apply { this.conditions.addAll(value) }

    fun default(value: String) = apply { this.default = value }

    override fun stepType() = StepType.CONDITIONAL

    override fun build() = SwitchContext(
        conditions = conditions.map { it.build() },
        default = default,
    )
}