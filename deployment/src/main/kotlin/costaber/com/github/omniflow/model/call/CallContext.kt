package costaber.com.github.omniflow.model.call

import costaber.com.github.omniflow.model.Node
import costaber.com.github.omniflow.model.StepContext
import costaber.com.github.omniflow.model.variable.Term

data class CallContext(
    val method: HttpMethod,
    val host: String,
    val path: String,
    val authentication: Authentication? = null,
    val body: Any? = null,
    val header: Map<String, Term> = emptyMap(),
    val query: Map<String, Term> = emptyMap(),
    val timeoutInSeconds: Long? = null,
    val result: String,
) : StepContext, Node {

    override fun childNodes(): List<Node> {
        return emptyList()
    }
}