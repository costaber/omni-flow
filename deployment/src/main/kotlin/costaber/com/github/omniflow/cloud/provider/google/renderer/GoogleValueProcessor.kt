package costaber.com.github.omniflow.cloud.provider.google.renderer

import costaber.com.github.omniflow.model.Value
import costaber.com.github.omniflow.model.Variable

fun Value.process(): String {
    if (Variable::class.isInstance(this)) {
        return "\${${name}}"
    }
    return name
}