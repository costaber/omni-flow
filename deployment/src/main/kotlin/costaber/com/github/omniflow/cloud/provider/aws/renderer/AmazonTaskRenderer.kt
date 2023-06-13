package costaber.com.github.omniflow.cloud.provider.aws.renderer

import com.fasterxml.jackson.databind.ObjectMapper
import costaber.com.github.omniflow.cloud.provider.aws.*
import costaber.com.github.omniflow.model.CallContext
import costaber.com.github.omniflow.model.Node
import costaber.com.github.omniflow.model.Term
import costaber.com.github.omniflow.model.Value
import costaber.com.github.omniflow.renderer.IndentedNodeRenderer
import costaber.com.github.omniflow.renderer.IndentedRenderingContext
import costaber.com.github.omniflow.resource.util.render

class AmazonTaskRenderer(
    private val callContext: CallContext,
    private val amazonTermResolver: AmazonTermResolver,
) : IndentedNodeRenderer {

    private val objectMapper = ObjectMapper()

    override val element: Node = callContext

    override fun internalBeginRender(renderingContext: IndentedRenderingContext): String =
        render(renderingContext) {
            addLine(AMAZON_TASK_TYPE)
            addLine(AMAZON_RESOURCE)
            addLine("$AMAZON_INPUT_PATH\"\$\",")
            addLine(AMAZON_START_PARAMETERS)
            tab {
                addLine("${AMAZON_API_ENDPOINT}\"${callContext.host}\",")
                addLine("${AMAZON_METHOD}\"${callContext.method}\",")
                add("${AMAZON_PATH}\"${callContext.path}\"")
                renderMap(AMAZON_HEADERS, callContext.header)
                renderMap(AMAZON_QUERY_PARAMETERS, callContext.query)
                renderBody()
                renderAuth()
                addEmptyLine()
            }
            add(AMAZON_CLOSE_OBJECT)
        }

    override fun internalEndRender(renderingContext: IndentedRenderingContext): String {
        val amazonContext = renderingContext as AmazonRenderingContext
        val nextStepName = amazonContext.getNextStepName()
        return render(renderingContext) {
            addLine(AMAZON_START_RESULT_SELECTOR)
            tab {
                addLine("\"${callContext.result}.\$\": \"\$.${AMAZON_RESPONSE_BODY}\"")
            }
            addLine(AMAZON_CLOSE_OBJECT)
            if (nextStepName == null) {
                add(AMAZON_END)
            } else {
                add("${AMAZON_NEXT}\"${nextStepName}\"")
            }
        }
    }


    private fun IndentedRenderingContext.renderMap(
        title: String,
        mapToRender: Map<String, Term<*>>
    ) {
        if (mapToRender.isNotEmpty()) {
            append(",")
            addEmptyLine()
            addLine(title)
            tab {
                val mutableMap = mapToRender.toMutableMap()
                val last = mutableMap.entries.lastOrNull()
                last?.let { mutableMap.remove(last.key) }
                mutableMap.forEach {
                    addLine("${renderMapEntry(it)},")
                }
                last?.let { addLine(renderMapEntry(it)) }
            }
            add("}")
        }
    }

    private fun renderMapEntry(mapEntry: Map.Entry<String, Term<*>>): String {
        var value = amazonTermResolver.resolve(mapEntry.value)
        if (mapEntry.value is Value<*>) {
            value = "[$value]"
        }
        return "\"${mapEntry.key}.\$\": $value"
    }

    private fun IndentedRenderingContext.renderBody() {
        callContext.body?.let {
            append(",")
            addEmptyLine()
            addLine("$AMAZON_REQUEST_BODY${objectMapper.writeValueAsString(callContext.body)}")
        }
    }

    private fun IndentedRenderingContext.renderAuth() {
        callContext.authentication?.let {
            append(",")
            addEmptyLine()
            add("${AMAZON_AUTH_TYPE}\"${it.type}\"")
        }
    }
}