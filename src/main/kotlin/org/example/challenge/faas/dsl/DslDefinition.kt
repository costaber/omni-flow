package org.example.challenge.faas.dsl

import org.example.challenge.faas.builder.ScopeBuilder
import org.example.challenge.faas.builder.StepBuilder
import org.example.challenge.faas.builder.WorkflowBuilder
import org.example.challenge.faas.builder.execution.ExecutionContextBuilder

fun scope(scopeBuilder: ScopeBuilder.() -> Unit) {
    ScopeBuilder().apply(scopeBuilder).build()
}

fun workflow(workflowBuilder: WorkflowBuilder.() -> Unit): WorkflowBuilder {
    return WorkflowBuilder().apply(workflowBuilder)
}

fun step(stepBuilder: StepBuilder.() -> Unit): StepBuilder {
    return StepBuilder().apply(stepBuilder)
}

fun execution(executionContextBuilder: ExecutionContextBuilder.() -> Unit): ExecutionContextBuilder {
    return ExecutionContextBuilder().apply(executionContextBuilder)
}