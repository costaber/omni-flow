package costaber.com.github.omniflow.visitor

import costaber.com.github.omniflow.model.Node

interface IContextVisitor<E: Node> {
    fun visit(element: E): String
}