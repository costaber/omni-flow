package costaber.com.github.omniflow.model.execution

import costaber.com.github.omniflow.model.Node
import costaber.com.github.omniflow.model.StepContext

data class ExecutionContext(
    val method: HttpMethod,
    val result: String,
    val url: String,
    val authentication: Authentication? = null,
    val body: Map<String, String> = emptyMap(),
    val header: Map<String, String> = emptyMap(),
    val query: Map<String, String> = emptyMap(),
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