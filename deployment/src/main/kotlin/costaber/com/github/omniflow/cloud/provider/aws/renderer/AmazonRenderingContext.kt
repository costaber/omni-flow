package costaber.com.github.omniflow.cloud.provider.aws.renderer

import costaber.com.github.omniflow.model.Condition
import costaber.com.github.omniflow.model.Step
import costaber.com.github.omniflow.model.VariableInitialization
import costaber.com.github.omniflow.renderer.IndentedRenderingContext

class AmazonRenderingContext : IndentedRenderingContext() {

    private lateinit var stepsNames: MutableList<String>
    private var lastVariable: VariableInitialization<*>? = null
    private var lastCondition: Condition? = null

    fun setVariables(variables: Collection<VariableInitialization<*>>) {
        lastVariable = variables.lastOrNull()
    }

    fun isNotLastVariable(variableInitialization: VariableInitialization<*>) =
        lastVariable != variableInitialization

    fun setConditions(conditions: Collection<Condition>) {
        lastCondition = conditions.lastOrNull()
    }

    fun isLastCondition(condition: Condition) =
        lastCondition == condition

    fun setSteps(steps: Collection<Step>) {
        stepsNames = steps.map { it.name }
            .toMutableList()
    }

    fun getNextStepName(): String? =
        stepsNames.firstOrNull()

    fun getNextStepNameAndAdvance(): String? =
        stepsNames.removeFirstOrNull()
}