package costaber.com.github.omniflow.model

class Variable<T>(
    name: String,
    // When the context is null is a variable declaration/usage
    val context: VariableContext<T>? = null,
) : Value(name)