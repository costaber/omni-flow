package costaber.com.github.omniflow.cloud.provider.aws.renderer

import costaber.com.github.omniflow.cloud.provider.aws.*
import costaber.com.github.omniflow.model.Node
import costaber.com.github.omniflow.model.call.CallContext
import costaber.com.github.omniflow.renderer.IndentedNodeRenderer
import costaber.com.github.omniflow.renderer.RenderingContext
import costaber.com.github.omniflow.resource.TAB

class AmazonTaskRenderer(
    private val callContext: CallContext,
    private val amazonTermResolver: AmazonTermResolver,
) : IndentedNodeRenderer {

    override val element: Node = callContext

    override fun internalBeginRender(renderingContext: RenderingContext): String {
        val prefix = getIndentationString(renderingContext)
        return buildString {
            appendLine("${prefix}${AMAZON_TASK_TYPE}")
            appendLine("${prefix}${AMAZON_RESOURCE}")
            appendLine("${prefix}${AMAZON_INPUT_PATH}\"\$\",")
            appendLine("${prefix}${AMAZON_PARAMETERS}")
            appendLine("${prefix}${TAB}${AMAZON_API_ENDPOINT}\"${callContext.host}\",")
            appendLine("${prefix}${TAB}${AMAZON_METHOD}\"${callContext.method}\",")
            append("${prefix}${TAB}${AMAZON_PATH}\"${callContext.path}\",")
            renderQueryParameters(this, prefix)
            renderHeaders(this, prefix)
            renderAuth(this, prefix)
            appendLine()
            append("${prefix}},")
        }
    }

    override fun internalEndRender(renderingContext: RenderingContext): String {
        val amazonContext = renderingContext as AmazonRenderingContext
        val nextStepName = amazonContext.getNextStepName()

        val prefix = getIndentationString(renderingContext)

        return buildString {
            appendLine("${prefix}${AMAZON_RESULT_SELECTOR}")
            appendLine("${prefix}${TAB}\"${callContext.result}.\$\": \"\$.${AMAZON_RESPONSE_BODY}\"")
            appendLine("${prefix}},")
            val finish = if (nextStepName == null) {
                AMAZON_END
            } else {
                "${AMAZON_NEXT}\"${nextStepName}\""
            }
            append(prefix + finish)
        }
    }

    private fun renderQueryParameters(builder: StringBuilder, prefix: String) {
        if (callContext.query.isNotEmpty()) {
            builder.appendLine()
            builder.appendLine("${prefix}${TAB}${AMAZON_QUERY_PARAMETERS}")
            callContext.query.forEach {
                val value = amazonTermResolver.resolve(it.value)
                builder.appendLine("${prefix}${TAB}${TAB}\"${it.key}.\$\": ${value}")
            }
            builder.append("${prefix}${TAB}},")
        }
    }

    private fun renderHeaders(builder: StringBuilder, prefix: String) {
        if (callContext.header.isNotEmpty()) {
            //builder.appendLine()
            //builder.append("")
        }
    }

    private fun renderAuth(builder: StringBuilder, prefix: String) {
        callContext.authentication?.let {
            builder.appendLine()
            builder.append("${prefix}${TAB}${AMAZON_AUTH_TYPE}\"${it.type}\"")
        }
    }
}