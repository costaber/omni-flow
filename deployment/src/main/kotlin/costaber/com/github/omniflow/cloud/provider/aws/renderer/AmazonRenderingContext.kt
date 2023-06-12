package costaber.com.github.omniflow.cloud.provider.aws.renderer

import costaber.com.github.omniflow.model.Step
import costaber.com.github.omniflow.model.VariableInitialization
import costaber.com.github.omniflow.renderer.IndentedRenderingContext

class AmazonRenderingContext : IndentedRenderingContext() {

    private lateinit var stepsNames: MutableList<String>
    private lateinit var variables: Collection<VariableInitialization<*>>

    fun setVariables(variables: Collection<VariableInitialization<*>>) {
        this.variables = variables
    }

    fun isLastVariable(variableInitialization: VariableInitialization<*>): Boolean =
        variables.lastOrNull() == variableInitialization

    fun setSteps(steps: Collection<Step>) {
        stepsNames = steps.map { it.name }
            .toMutableList()
    }

    fun getNextStepName(): String? =
        stepsNames.firstOrNull()

    fun getNextStepNameAndAdvance(): String? =
        stepsNames.removeFirstOrNull()
}