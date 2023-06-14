package costaber.com.github.omniflow.renderer

import costaber.com.github.omniflow.model.Term
import costaber.com.github.omniflow.model.Value
import costaber.com.github.omniflow.model.Variable

interface TermResolver {

    fun resolveVariable(variable: Variable): String

    fun resolve(term: Term<*>): String = when (term) {
        is Variable -> resolveVariable(term)
        is Value -> when (term.value) {
            is String -> "\"${term.value}\""
            else -> term.value.toString()
        }
    }
}