package costaber.com.github.omniflow.cloud.provider.aws.renderer

import costaber.com.github.omniflow.cloud.provider.aws.*
import costaber.com.github.omniflow.model.Node
import costaber.com.github.omniflow.model.execution.ExecutionContext
import costaber.com.github.omniflow.renderer.IndentedNodeRenderer
import costaber.com.github.omniflow.renderer.RenderingContext
import costaber.com.github.omniflow.resource.TAB

class AmazonTaskRenderer(private val executionContext: ExecutionContext) : IndentedNodeRenderer {

    override val element: Node = executionContext

    override fun internalBeginRender(renderingContext: RenderingContext): String {
        val prefix = getIndentationString(renderingContext)
        return buildString {
            appendLine("${prefix}${AMAZON_TASK_TYPE}")
            appendLine("${prefix}${AMAZON_RESOURCE}")
            appendLine("${prefix}${AMAZON_INPUT_PATH}\"\$\",")
            appendLine("${prefix}${AMAZON_PARAMETERS}")
            appendLine("${prefix}${TAB}${AMAZON_API_ENDPOINT}\"${executionContext.host}\",")
            appendLine("${prefix}${TAB}${AMAZON_METHOD}\"${executionContext.method}\",")
            append("${prefix}${TAB}${AMAZON_PATH}\"${executionContext.path}\",")
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
            appendLine("${prefix}${TAB}\"${executionContext.result}.\$\": \"\$.${AMAZON_RESPONSE_BODY}\"")
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
        if (executionContext.query.isNotEmpty()) {
            builder.appendLine()
            builder.appendLine("${prefix}${TAB}${AMAZON_QUERY_PARAMETERS}")
            executionContext.query.forEach {
                builder.appendLine("${prefix}${TAB}${TAB}\"${it.key}\": \"${it.value.amazonProcess()}\"")
            }
            builder.append("${prefix}${TAB}},")
        }
    }

    private fun renderHeaders(builder: StringBuilder, prefix: String) {
        if (executionContext.header.isNotEmpty()) {
            //builder.appendLine()
            //builder.append("")
        }
    }

    private fun renderAuth(builder: StringBuilder, prefix: String) {
        executionContext.authentication?.let {
            builder.appendLine()
            builder.append("${prefix}${TAB}${AMAZON_AUTH_TYPE}\"${it.type}\"")
        }
    }

}