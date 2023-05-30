package costaber.com.github.omniflow.cloud.provider.aws.renderer

import costaber.com.github.omniflow.model.variable.Term
import costaber.com.github.omniflow.model.variable.Value
import costaber.com.github.omniflow.model.variable.Variable
import costaber.com.github.omniflow.renderer.TermResolver

object AmazonTermResolver : TermResolver<String> {
    override fun resolver(term: Term) = when (term) {
        is Variable -> {
            val termDefinition = if (term.name.isNotEmpty()) "\$.${term.name}" else "\$"
            "States.Array(States.Format('{}', ${termDefinition}))"
        }

        is Value<*> -> "\$.${term.value}"
    }
}