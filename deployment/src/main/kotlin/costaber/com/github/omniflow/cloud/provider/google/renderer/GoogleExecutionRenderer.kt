package costaber.com.github.omniflow.cloud.provider.google.renderer

import costaber.com.github.omniflow.model.Node
import costaber.com.github.omniflow.model.Value
import costaber.com.github.omniflow.model.execution.ExecutionContext
import costaber.com.github.omniflow.renderer.IndentedNodeRenderer
import costaber.com.github.omniflow.renderer.RenderingContext
import costaber.com.github.omniflow.resource.TAB

class GoogleExecutionRenderer(
    private val executionContext: ExecutionContext,
    //private val variableResolver: VariableResolver,
) : IndentedNodeRenderer {

    override val element: Node = executionContext

    override fun internalBeginRender(renderingContext: RenderingContext): String {
        val prefix = getIndentationString(renderingContext)
        val url = executionContext.host + executionContext.path
        return buildString {
            val httpMethod = executionContext.method.name.lowercase()
            appendLine("${prefix}call: http.${httpMethod}")
            appendLine("${prefix}args:")
            append("${prefix}${TAB}url: $url")
            renderMap("headers", executionContext.header, prefix, this)
            renderAuth(executionContext, prefix, this)
            renderMap("query", executionContext.query, prefix, this)
            renderTimeout(executionContext, prefix, this)
        }
    }

    override fun internalEndRender(renderingContext: RenderingContext): String {
        val prefix = getIndentationString(renderingContext)
        return "${prefix}result: ${executionContext.result}"
    }

    private fun renderMap(
        mapName: String,
        mapToRender: Map<String, Value>,
        prefix: String,
        stringBuilder: StringBuilder,
    ) {
        if (mapToRender.isEmpty())
            return

        stringBuilder.run {
            appendLine()
            appendLine("${prefix}${TAB}$mapName:")
            mapToRender.forEach {
                append("${prefix}${TAB}${TAB}${it.key}: ${it.value.name}")
            }
        }
    }

    private fun renderAuth(
        executionContext: ExecutionContext,
        prefix: String,
        stringBuilder: StringBuilder
    ) {
        executionContext.authentication?.let {
            stringBuilder.appendLine()
            stringBuilder.appendLine("${prefix}${TAB}auth:")
            stringBuilder.appendLine("${prefix}${TAB}${TAB}type: ${it.type}")
            stringBuilder.appendLine("${prefix}${TAB}${TAB}scope: ${it.scope}")
            stringBuilder.appendLine("${prefix}${TAB}${TAB}scopes: ${it.scopes}")
            stringBuilder.append("${prefix}${TAB}${TAB}audience: ${it.audience}")
        }
    }

    private fun renderTimeout(
        executionContext: ExecutionContext,
        prefix: String,
        stringBuilder: StringBuilder
    ) {
        executionContext.timeoutInSeconds?.let {
            stringBuilder.appendLine()
            stringBuilder.append("${prefix}${TAB}timeout: $it")
        }
    }
}