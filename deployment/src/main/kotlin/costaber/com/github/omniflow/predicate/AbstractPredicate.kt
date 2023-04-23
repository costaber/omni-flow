package costaber.com.github.omniflow.predicate

import costaber.com.github.omniflow.model.Node
import java.util.function.Predicate

abstract class AbstractPredicate<T>(private val type: Class<T>) : Predicate<Node> {

    override fun test(node: Node): Boolean {
        val isType = type.isInstance(node)
        if (!isType) return false
        return testConcrete(type.cast(node))
    }

    protected abstract fun testConcrete(element: T): Boolean
}