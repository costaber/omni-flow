package costaber.com.github.omniflow.builder.execution

import costaber.com.github.omniflow.builder.ContextBuilder
import costaber.com.github.omniflow.model.StepType
import costaber.com.github.omniflow.model.call.CallContext
import costaber.com.github.omniflow.model.call.HttpMethod
import costaber.com.github.omniflow.model.variable.Term

class CallContextBuilder : ContextBuilder {

    // mandatory
    private lateinit var method: HttpMethod
    private lateinit var host: String
    private lateinit var path: String
    private lateinit var result: String

    // optional
    private val header: MutableMap<String, Term<*>> = mutableMapOf()
    private val query: MutableMap<String, Term<*>> = mutableMapOf()
    private var body: Any? = null
    private var authenticationBuilder: AuthenticationBuilder? = null
    private var timeout: Long? = null

    fun host(value: String) = apply { this.host = value }

    fun path(value: String) = apply { this.path = value }

    fun method(value: HttpMethod) = apply { this.method = value }

    fun header(vararg value: Pair<String, Term<*>>) = apply { value.forEach { header[it.first] = it.second } }

    fun query(vararg value: Pair<String, Term<*>>) = apply { value.forEach { query[it.first] = it.second } }

    fun body(value: Any) = apply { this.body = value }

    fun authentication(value: AuthenticationBuilder) = apply { this.authenticationBuilder = value }

    fun timeout(value: Long) = apply { this.timeout = value }

    fun result(value: String) = apply { this.result = value }

    override fun stepType() = StepType.CALL

    override fun build() = CallContext(
        host = host,
        path = path,
        method = method,
        header = header,
        query = query,
        body = body,
        authentication = authenticationBuilder?.build(),
        timeoutInSeconds = timeout,
        result = result
    )
}