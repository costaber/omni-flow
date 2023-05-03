package costaber.com.github.omniflow.cloud.provider.aws.renderer

import costaber.com.github.omniflow.model.Step
import costaber.com.github.omniflow.renderer.IndentedRenderingContext

class AmazonRenderingContext : IndentedRenderingContext() {

    private lateinit var stepsNames: MutableList<String>

    fun setSteps(steps: Collection<Step>) {
        stepsNames = steps.map { it.name }
            .toMutableList()
    }

    fun getNextStepName(): String? =
        stepsNames.firstOrNull()

    fun getNextStepNameAndAdvance(): String? =
        stepsNames.removeFirstOrNull()
}