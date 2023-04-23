package costaber.com.github.omniflow.cloud.provider.google.predicate

import costaber.com.github.omniflow.model.execution.ExecutionContext
import costaber.com.github.omniflow.predicate.AbstractPredicate

class GoogleExecutionPredicate : AbstractPredicate<ExecutionContext>(ExecutionContext::class.java) {
    override fun testConcrete(element: ExecutionContext): Boolean {
        return true
    }
}