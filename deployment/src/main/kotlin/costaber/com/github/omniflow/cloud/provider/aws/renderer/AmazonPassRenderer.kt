package costaber.com.github.omniflow.cloud.provider.aws.renderer

import costaber.com.github.omniflow.cloud.provider.aws.AMAZON_END
import costaber.com.github.omniflow.cloud.provider.aws.AMAZON_NEXT
import costaber.com.github.omniflow.cloud.provider.aws.AMAZON_PASS_TYPE
import costaber.com.github.omniflow.model.AssignContext
import costaber.com.github.omniflow.model.Node
import costaber.com.github.omniflow.model.VariableInitialization
import costaber.com.github.omniflow.renderer.IndentedNodeRenderer
import costaber.com.github.omniflow.renderer.IndentedRenderingContext
import costaber.com.github.omniflow.resource.TAB

class AmazonPassRenderer(private val assignContext: AssignContext) : IndentedNodeRenderer {

    override val element: Node = assignContext

    override fun internalBeginRender(renderingContext: IndentedRenderingContext): String {
        val prefix = renderingContext.getIndentationString()
        return buildString {
            appendLine("${prefix}${AMAZON_PASS_TYPE}")
            appendLine("${prefix}\"Result\": {")

            val variablesMutable = mutableListOf<VariableInitialization<*>>()
            variablesMutable.addAll(assignContext.variables.toTypedArray())
            val lastVariable = variablesMutable.removeLastOrNull()
            variablesMutable.forEach { appendLine("${it.render(prefix)},") }
            lastVariable?.let { appendLine(it.render(prefix)) }
            append("${prefix}},")
        }
    }

    override fun internalEndRender(renderingContext: IndentedRenderingContext): String {
        val amazonContext = renderingContext as AmazonRenderingContext
        val nextStepName = amazonContext.getNextStepName()
        val prefix = renderingContext.getIndentationString()

        return buildString {
            val finish = if (nextStepName == null) AMAZON_END else "$AMAZON_NEXT\"${nextStepName}\""
            append(prefix + finish)
        }
    }

    private fun VariableInitialization<*>.render(prefix: String): String =
        "${TAB}${prefix}\"${variable.name}\": ${term.term()}"
}