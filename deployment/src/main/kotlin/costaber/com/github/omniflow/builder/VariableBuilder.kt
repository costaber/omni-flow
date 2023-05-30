package costaber.com.github.omniflow.builder

import costaber.com.github.omniflow.model.VariableInitialization
import costaber.com.github.omniflow.model.variable.Value
import costaber.com.github.omniflow.model.variable.Variable

class VariableBuilder<T : Any> : Builder<VariableInitialization<T>> {

    private lateinit var name: String
    private lateinit var value: T

    fun name(value: String) = apply { this.name = value }

    fun value(value: T) = apply { this.value = value }

    override fun build() = VariableInitialization(
        name = Variable(name),
        value = Value(value)
    )
}