package costaber.com.github.omniflow.builder

import costaber.com.github.omniflow.model.Variable
import costaber.com.github.omniflow.model.VariableContext

class VariableBuilder<T> : Builder<Variable<T>> {

    private lateinit var name: String
    private var value: T? = null

    fun name(value: String) = apply { this.name = value }

    fun value(value: T) = apply { this.value = value }

    override fun build() = Variable(
        name = name,
        context = VariableContext(
            value = value
        )
    )
}