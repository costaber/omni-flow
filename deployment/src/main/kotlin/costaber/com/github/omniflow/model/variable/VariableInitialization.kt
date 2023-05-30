package costaber.com.github.omniflow.model.variable

data class VariableInitialization<T : Any>(
    val name: Variable,
    val value: Value<T>,
)