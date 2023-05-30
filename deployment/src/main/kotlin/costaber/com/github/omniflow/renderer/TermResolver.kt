package costaber.com.github.omniflow.renderer

import costaber.com.github.omniflow.model.variable.Term
import costaber.com.github.omniflow.model.variable.Value
import costaber.com.github.omniflow.model.variable.Variable

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