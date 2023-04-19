package costaber.com.github.omniflow.predicate

import costaber.com.github.omniflow.model.Node
import java.util.function.Predicate

class DefaultNodePredicate<T>(
    private val type: Class<T>
): Predicate<Node> {

    override fun test(node: Node): Boolean {
        return type.isInstance(node)
    }
}