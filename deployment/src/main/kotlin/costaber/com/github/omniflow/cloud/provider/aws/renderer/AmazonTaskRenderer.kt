package costaber.com.github.omniflow.cloud.provider.aws.renderer

import costaber.com.github.omniflow.model.Node
import costaber.com.github.omniflow.model.execution.ExecutionContext
import costaber.com.github.omniflow.renderer.IndentedNodeRenderer
import costaber.com.github.omniflow.renderer.RenderingContext
import costaber.com.github.omniflow.resource.TAB
import java.lang.StringBuilder

class AmazonTaskRenderer(private val executionContext: ExecutionContext) : IndentedNodeRenderer {

    override val element: Node = executionContext

    override fun internalBeginRender(renderingContext: RenderingContext): String {
        val prefix = getIndentationString(renderingContext)
        return buildString {
            appendLine("${prefix}\"Type\": \"Task\",")
            appendLine("${prefix}\"Resource\": \"arn:aws:states:::apigateway:invoke\",")
            appendLine("${prefix}\"InputPath\": \"\$\",")
            appendLine("${prefix}\"Parameters\": {")
            appendLine("${prefix}${TAB}\"ApiEndpoint\": \"${executionContext.host}\",")
            appendLine("${prefix}${TAB}\"Method\": \"${executionContext.method}\",")
            append("${prefix}${TAB}\"Path\": \"${executionContext.path}\",")
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
            appendLine("${prefix}\"ResultSelector\": {")
            appendLine("${prefix}${TAB}\"${executionContext.result}.\$\": \"\$.ResponseBody\"")
            appendLine("${prefix}},")
            val finish = if (nextStepName == null) {
                "\"End\" : true"
            } else {
                "\"Next\": \"${nextStepName}\""
            }
            append(prefix + finish)
        }
    }

    private fun renderQueryParameters(builder: StringBuilder, prefix: String) {
        if (executionContext.query.isNotEmpty()) {
            builder.appendLine()
            builder.appendLine("${prefix}\"QueryParameters\": {")
            executionContext.query.forEach {
                builder.appendLine("${prefix}${TAB}\"${it.key}\": \"${it.value}\"")
            }
            builder.append("${prefix}},")
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
            builder.append("${prefix}\"AuthType\": \"${it.type}\"")
        }
    }

}