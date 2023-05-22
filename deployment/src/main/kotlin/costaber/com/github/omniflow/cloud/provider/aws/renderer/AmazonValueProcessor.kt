package costaber.com.github.omniflow.cloud.provider.aws.renderer

import costaber.com.github.omniflow.model.Value
import costaber.com.github.omniflow.model.Variable

fun Value.amazonProcess(): String {
    if (Variable::class.isInstance(this)) {
        return "States.Array(States.Format('{}', \$.${name}))"
    }
    return "\$.name"
}