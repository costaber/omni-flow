package costaber.com.github.omniflow.model

data class Step(
    val name: String,
    val description: String,
    val type: StepType = StepType.EXECUTION,
    val context: Context,
) : Node