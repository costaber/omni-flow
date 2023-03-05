package costaber.com.github.omniflow.domain

data class Step(
    val name: String,
    val description: String,
    val type: StepType = StepType.EXECUTION,
    val context: Context,
)