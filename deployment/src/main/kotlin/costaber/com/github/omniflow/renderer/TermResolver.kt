package costaber.com.github.omniflow.renderer

import costaber.com.github.omniflow.model.Term
import costaber.com.github.omniflow.model.Value
import costaber.com.github.omniflow.model.Variable

interface TermResolver {

    fun resolveVariable(variable: Variable, termContext: TermContext): String

    fun resolve(term: Term<*>, context: TermContext): String = when (term) {
        is Variable -> resolveVariable(term, context)
        is Value -> when (term.value) {
            is String -> "'${term.value}'"
            else -> term.value.toString()
        }
    }
}