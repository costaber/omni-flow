package costaber.com.github.omniflow.builder.execution

import costaber.com.github.omniflow.builder.ContextBuilder
import costaber.com.github.omniflow.model.StepType
import costaber.com.github.omniflow.model.execution.ExecutionContext
import costaber.com.github.omniflow.model.execution.HttpMethod

class ExecutionContextBuilder : ContextBuilder {

    // mandatory
    private lateinit var method: HttpMethod
    private lateinit var url: String
    private lateinit var result: String

    // optional
    private val header: MutableMap<String, String> = mutableMapOf()
    private val query: MutableMap<String, String> = mutableMapOf()
    private val body: MutableMap<String, String> = mutableMapOf()
    private var authenticationBuilder: AuthenticationBuilder? = null
    private var timeout: Long? = null

    fun url(value: String) = apply { this.url = value }

    fun method(value: HttpMethod) = apply { this.method = value }

    fun header(vararg value: Pair<String, String>) = apply { value.forEach { header[it.first] = it.second } }

    fun query(vararg value: Pair<String, String>) = apply { value.forEach { query[it.first] = it.second } }

    fun body(vararg value: Pair<String, String>) = apply { value.forEach { body[it.first] = it.second } }

    fun authentication(value: AuthenticationBuilder) = apply { this.authenticationBuilder = value }

    fun timeout(value: Long) = apply { this.timeout = value }

    fun result(value: String) = apply { this.result = value }

    override fun stepType() = StepType.EXECUTION

    override fun build() = ExecutionContext(
        url = url,
        method = method,
        header = header,
        query = query,
        body = body,
        authentication = authenticationBuilder?.build(),
        timeoutInSeconds = timeout,
        result = result
    )
}