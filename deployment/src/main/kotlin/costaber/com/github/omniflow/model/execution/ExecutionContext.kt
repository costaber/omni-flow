package costaber.com.github.omniflow.model.execution

import costaber.com.github.omniflow.model.Node
import costaber.com.github.omniflow.model.StepContext
import costaber.com.github.omniflow.model.Value

data class ExecutionContext(
    val method: HttpMethod,
    val result: String,
    val host: String,
    val path: String,
    val authentication: Authentication? = null,
    val body: Map<String, Value> = emptyMap(),
    val header: Map<String, Value> = emptyMap(),
    val query: Map<String, Value> = emptyMap(),
    val timeoutInSeconds: Long? = null,
) : StepContext, Node {

    override fun childNodes(): List<Node> {
        return emptyList()
    }

    override fun childNodeSize(): Int {
        return 0
    }

    override fun childNode(index: Int): Node? {
        return null
    }
}