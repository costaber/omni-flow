package costaber.com.github.omniflow.model.variable

data class VariableInitialization<T : Any>(
    val variable: Variable,
    val term: Term<T>,
)