package costaber.com.github.omniflow.cloud.provider.google.renderer

import costaber.com.github.omniflow.model.Node
import costaber.com.github.omniflow.model.execution.ExecutionContext
import costaber.com.github.omniflow.renderer.RenderingContext
import costaber.com.github.omniflow.resource.TAB

class GoogleExecutionRenderer(
    private val executionContext: ExecutionContext,
    //private val variableResolver: VariableResolver,
) : GoogleRenderer {

    override val element: Node = executionContext

    override fun internalBeginRender(renderingContext: RenderingContext): String {
        val prefix = getIndentationString(renderingContext)
        val stepBuilder = StringBuilder()
        stepBuilder.appendLine("${prefix}call: http.${executionContext.method}")
        stepBuilder.appendLine("${prefix}args:")
        stepBuilder.append("${prefix}${TAB}url: ${executionContext.url}")
        renderMap("headers", executionContext.header, prefix, stepBuilder)
        renderMap("query", executionContext.query, prefix, stepBuilder)
        renderAuth(executionContext, prefix, stepBuilder)
        renderTimeout(executionContext, prefix, stepBuilder)
        return stepBuilder.toString()
    }

    override fun internalEndRender(renderingContext: RenderingContext): String {
        val prefix = getIndentationString(renderingContext)
        return "${prefix}result: ${executionContext.result}"
    }

    private fun renderMap(
        mapName: String,
        mapToRender: Map<String, String>,
        prefix: String,
        stringBuilder: StringBuilder,
    ) {
        if (mapToRender.isEmpty())
            return

        stringBuilder.appendLine()
        stringBuilder.appendLine("${prefix}${TAB}$mapName:")
        mapToRender.forEach {
            stringBuilder.append("${prefix}${TAB}${TAB}${it.key}: ${it.value}")
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