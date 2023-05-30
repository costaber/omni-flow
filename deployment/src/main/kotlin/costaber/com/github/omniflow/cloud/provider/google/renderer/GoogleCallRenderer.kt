package costaber.com.github.omniflow.cloud.provider.google.renderer

import costaber.com.github.omniflow.model.Node
import costaber.com.github.omniflow.model.call.CallContext
import costaber.com.github.omniflow.model.variable.Term
import costaber.com.github.omniflow.renderer.IndentedNodeRenderer
import costaber.com.github.omniflow.renderer.RenderingContext
import costaber.com.github.omniflow.resource.TAB

class GoogleCallRenderer(
    private val callContext: CallContext,
    private val googleTermResolver: GoogleTermResolver,
) : IndentedNodeRenderer {

    override val element: Node = callContext

    override fun internalBeginRender(renderingContext: RenderingContext): String {
        val prefix = getIndentationString(renderingContext)
        val url = callContext.host + callContext.path
        return buildString {
            val httpMethod = callContext.method.name.lowercase()
            appendLine("${prefix}call: http.${httpMethod}")
            appendLine("${prefix}args:")
            append("${prefix}${TAB}url: $url")
            renderMap("headers", callContext.header, prefix, this)
            renderAuth(callContext, prefix, this)
            renderMap("query", callContext.query, prefix, this)
            renderTimeout(callContext, prefix, this)
        }
    }

    override fun internalEndRender(renderingContext: RenderingContext): String {
        val prefix = getIndentationString(renderingContext)
        return "${prefix}result: ${callContext.result}"
    }

    private fun renderMap(
        mapName: String,
        mapToRender: Map<String, Term<*>>,
        prefix: String,
        stringBuilder: StringBuilder,
    ) {
        if (mapToRender.isEmpty())
            return

        stringBuilder.run {
            appendLine()
            append("${prefix}${TAB}$mapName:")
            mapToRender.forEach {
                appendLine()
                val value = googleTermResolver.resolve(it.value)
                append("${prefix}${TAB}${TAB}${it.key}: ${value}")
            }
        }
    }

    private fun renderAuth(
        callContext: CallContext,
        prefix: String,
        stringBuilder: StringBuilder
    ) {
        callContext.authentication?.let {
            stringBuilder.appendLine()
            stringBuilder.appendLine("${prefix}${TAB}auth:")
            stringBuilder.appendLine("${prefix}${TAB}${TAB}type: ${it.type}")
            stringBuilder.appendLine("${prefix}${TAB}${TAB}scope: ${it.scope}")
            stringBuilder.appendLine("${prefix}${TAB}${TAB}scopes: ${it.scopes}")
            stringBuilder.append("${prefix}${TAB}${TAB}audience: ${it.audience}")
        }
    }

    private fun renderTimeout(
        callContext: CallContext,
        prefix: String,
        stringBuilder: StringBuilder
    ) {
        callContext.timeoutInSeconds?.let {
            stringBuilder.appendLine()
            stringBuilder.append("${prefix}${TAB}timeout: $it")
        }
    }
}