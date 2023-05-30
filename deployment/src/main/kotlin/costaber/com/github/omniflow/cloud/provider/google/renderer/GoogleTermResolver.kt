package costaber.com.github.omniflow.cloud.provider.google.renderer

import costaber.com.github.omniflow.model.variable.Term
import costaber.com.github.omniflow.model.variable.Value
import costaber.com.github.omniflow.model.variable.Variable
import costaber.com.github.omniflow.renderer.TermResolver

object GoogleTermResolver : TermResolver<String> {
    override fun resolver(term: Term): String = when (term) {
        is Variable -> "\${${term.name}}"
        is Value<*> -> term.value.toString()
    }
}