package costaber.com.github.omniflow.cloud.provider.google.predicate

import costaber.com.github.omniflow.model.Step
import costaber.com.github.omniflow.predicate.AbstractPredicate

class GoogleStepPredicate : AbstractPredicate<Step>(Step::class.java) {
    override fun testConcrete(element: Step): Boolean {
        return true
    }
}