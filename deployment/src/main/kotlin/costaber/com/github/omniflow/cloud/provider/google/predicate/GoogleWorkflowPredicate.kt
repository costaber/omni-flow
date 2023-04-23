package costaber.com.github.omniflow.cloud.provider.google.predicate

import costaber.com.github.omniflow.model.Workflow
import costaber.com.github.omniflow.predicate.AbstractPredicate

class GoogleWorkflowPredicate : AbstractPredicate<Workflow>(Workflow::class.java) {
    override fun testConcrete(element: Workflow): Boolean {
        return true
    }
}