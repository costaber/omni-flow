package costaber.com.github.omniflow.renderer

import costaber.com.github.omniflow.model.variable.Term

interface TermResolver<T> {
    fun resolver(term: Term): T
}