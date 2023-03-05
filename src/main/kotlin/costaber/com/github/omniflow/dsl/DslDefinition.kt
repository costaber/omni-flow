package costaber.com.github.omniflow.dsl

import costaber.com.github.omniflow.builder.StepBuilder
import costaber.com.github.omniflow.builder.WorkflowBuilder
import costaber.com.github.omniflow.builder.execution.ExecutionContextBuilder

fun workflow(workflowBuilder: WorkflowBuilder.() -> Unit): WorkflowBuilder {
    return WorkflowBuilder().apply(workflowBuilder)
}

fun step(stepBuilder: StepBuilder.() -> Unit): StepBuilder {
    return StepBuilder().apply(stepBuilder)
}

fun execution(executionContextBuilder: ExecutionContextBuilder.() -> Unit): ExecutionContextBuilder {
    return ExecutionContextBuilder().apply(executionContextBuilder)
}