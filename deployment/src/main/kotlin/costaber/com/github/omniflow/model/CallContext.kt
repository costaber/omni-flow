package costaber.com.github.omniflow.model

data class CallContext(
    val method: HttpMethod,
    val host: String,
    val path: String,
    val authentication: Authentication? = null,
    val body: Any? = null,
    val header: Map<String, Term<*>> = emptyMap(),
    val query: Map<String, Term<*>> = emptyMap(),
    val timeoutInSeconds: Long? = null,
    val result: String,
) : StepContext, Node {

    override fun childNodes() = emptyList<Node>()
}