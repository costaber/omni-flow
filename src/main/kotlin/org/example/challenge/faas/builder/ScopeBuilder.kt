package org.example.challenge.faas.builder

class ScopeBuilder : Builder<Unit> {

    private val workflows: MutableList<WorkflowBuilder> = mutableListOf()

    fun deployable(vararg workflow: WorkflowBuilder) {
        workflows.addAll(workflow)
    }

    override fun build() {
        workflows.map { it.build() }
            .forEach { println(it) }
    }
}