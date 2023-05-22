package costaber.com.github.omniflow.model

class Variable<T>(
    name: String,
    val context: VariableContext<T>? = null, // when this value is null is just a variable declaration/usage
): Value(name)