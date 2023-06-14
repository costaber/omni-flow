package costaber.com.github.omniflow.cloud.provider.aws.renderer

import costaber.com.github.omniflow.model.Variable
import costaber.com.github.omniflow.renderer.TermResolver

object AmazonTermResolver : TermResolver {

    override fun resolveVariable(variable: Variable): String {
        val variableName = variable.name
        val termDefinition = if (variableName.isNotEmpty()) "\$.${variableName}" else "\$"
        return "\"States.Array(States.Format('{}', ${termDefinition}))\""
    }
}