package costaber.com.github.omniflow.cloud.provider.amazon.renderer

import costaber.com.github.omniflow.model.Variable
import costaber.com.github.omniflow.renderer.TermContext
import costaber.com.github.omniflow.renderer.TermResolver

object AmazonTermResolver : TermResolver {

    private val variableTranslator = mutableMapOf<String, String>()

    override fun resolveVariable(variable: Variable, termContext: TermContext): String {
        val variableName = translateVariable(variable.name)
        val termDefinition = if (variableName.isNotEmpty()) "\$.${variableName}" else "\$"
        return "\"States.Array(States.Format('{}', ${termDefinition}))\""
    }

    fun addVariable(prefix: String, name: String) {
        variableTranslator[name] = "$prefix.$name"
    }

    private fun translateVariable(variableName: String): String =
        variableTranslator[variableName] ?: variableName
}