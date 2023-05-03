package costaber.com.github.omniflow.dsl

import costaber.com.github.omniflow.builder.StepBuilder
import costaber.com.github.omniflow.builder.WorkflowBuilder
import costaber.com.github.omniflow.builder.execution.AuthenticationBuilder
import costaber.com.github.omniflow.builder.execution.ExecutionContextBuilder
import costaber.com.github.omniflow.model.Workflow

fun workflow(workflowBuilder: WorkflowBuilder.() -> Unit): Workflow {
    return WorkflowBuilder().apply(workflowBuilder).build()
}

fun step(stepBuilder: StepBuilder.() -> Unit): StepBuilder {
    return StepBuilder().apply(stepBuilder)
}

fun execution(executionContextBuilder: ExecutionContextBuilder.() -> Unit): ExecutionContextBuilder {
    return ExecutionContextBuilder().apply(executionContextBuilder)
}

fun authentication(authenticationBuilder: AuthenticationBuilder.() -> Unit): AuthenticationBuilder {
    return AuthenticationBuilder().apply(authenticationBuilder)
}