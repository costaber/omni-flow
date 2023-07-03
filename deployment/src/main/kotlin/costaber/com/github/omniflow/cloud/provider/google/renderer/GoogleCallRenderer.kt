package costaber.com.github.omniflow.cloud.provider.google.renderer

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import costaber.com.github.omniflow.model.CallContext
import costaber.com.github.omniflow.model.Node
import costaber.com.github.omniflow.model.Term
import costaber.com.github.omniflow.renderer.IndentedNodeRenderer
import costaber.com.github.omniflow.renderer.IndentedRenderingContext
import costaber.com.github.omniflow.resource.util.render

class GoogleCallRenderer(
    private val callContext: CallContext,
    private val googleTermResolver: GoogleTermResolver,
) : IndentedNodeRenderer() {

    private val objectMapper = ObjectMapper(YAMLFactory())

    override val element: Node = callContext

    override fun internalBeginRender(renderingContext: IndentedRenderingContext): String =
        render(renderingContext) {
            val httpMethod = callContext.method.name.lowercase()
            addLine("call: http.${httpMethod}")
            addLine("args:")
            tab {
                add("url: ${callContext.host + callContext.path}")
            }
            renderMap("headers", callContext.header)
            renderMap("query", callContext.query)
            renderBody()
            renderAuth()
            renderTimeout()
        }

    override fun internalEndRender(renderingContext: IndentedRenderingContext): String =
        render(renderingContext) {
            add("result: ${callContext.result}")
        }

    private fun IndentedRenderingContext.renderMap(
        mapName: String,
        mapToRender: Map<String, Term<*>>,
    ) {
        if (mapToRender.isEmpty())
            return

        tab {
            addEmptyLine()
            add("$mapName:")
            mapToRender.forEach {
                addEmptyLine()
                val value = googleTermResolver.resolve(it.value)
                tab {
                    add("${it.key}: $value")
                }
            }
        }
    }

    private fun IndentedRenderingContext.renderBody() {
        callContext.body?.let {
            val yamlString = objectMapper.writeValueAsString(it)
                .replace("---", "\n")
                .split("\n")
                .filterNot(String::isEmpty)
            addEmptyLine()
            add("body:")
            tab {
                if (yamlString.size == 1) {
                    append(yamlString.first())
                } else {
                    yamlString.forEach { line ->
                        addEmptyLine()
                        add(line)
                    }
                }
            }
        }
    }

    private fun IndentedRenderingContext.renderAuth() {
        callContext.authentication?.let {
            addEmptyLine()
            tab {
                addLine("auth:")
                tab {
                    addLine("type: ${it.type}")
                    addLine("scope: ${it.scope}")
                    addLine("scopes: ${it.scopes}")
                    add("audience: ${it.audience}")
                }
            }
        }
    }

    private fun IndentedRenderingContext.renderTimeout() {
        callContext.timeoutInSeconds?.let {
            addEmptyLine()
            tab {
                add("timeout: $it")
            }
        }
    }
}