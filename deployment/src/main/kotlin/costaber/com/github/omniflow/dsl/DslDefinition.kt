package costaber.com.github.omniflow.dsl

import costaber.com.github.omniflow.builder.StepBuilder
import costaber.com.github.omniflow.builder.WorkflowBuilder
import costaber.com.github.omniflow.builder.execution.AssignContextBuilder
import costaber.com.github.omniflow.builder.execution.AuthenticationBuilder
import costaber.com.github.omniflow.builder.execution.CallContextBuilder
import costaber.com.github.omniflow.model.Workflow
import costaber.com.github.omniflow.model.variable.Value
import costaber.com.github.omniflow.model.variable.Variable

fun workflow(workflowBuilder: WorkflowBuilder.() -> Unit): Workflow {
    return WorkflowBuilder().apply(workflowBuilder).build()
}

fun step(stepBuilder: StepBuilder.() -> Unit): StepBuilder {
    return StepBuilder().apply(stepBuilder)
}

fun call(callContextBuilder: CallContextBuilder.() -> Unit): CallContextBuilder {
    return CallContextBuilder().apply(callContextBuilder)
}

fun authentication(authenticationBuilder: AuthenticationBuilder.() -> Unit): AuthenticationBuilder {
    return AuthenticationBuilder().apply(authenticationBuilder)
}

fun assign(assignContextBuilder: AssignContextBuilder.() -> Unit): AssignContextBuilder {
    return AssignContextBuilder().apply(assignContextBuilder)
}

fun variable(name: String): Variable = Variable(name)

fun <T : Any> value(value: T): Value<T> = Value(value)
