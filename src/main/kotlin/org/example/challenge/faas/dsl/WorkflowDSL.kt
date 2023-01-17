package org.example.challenge.faas.dsl

class WorkflowDSL {

    // infrastructure
    private lateinit var zone: String
    private lateinit var provider: CloudProvider
    // metadata
    private lateinit var name: String
    private lateinit var description: String
    private val steps: MutableCollection<StepDSL> = mutableListOf()
    private lateinit var result: String

    fun zone(zone: String) {
        this.zone = zone
    }

    fun provider(provider: CloudProvider) {
        this.provider = provider
    }

    fun name(name: String) {
        this.name = name
    }

    fun description(description: String) {
        this.description = description
    }

    fun steps(vararg step: StepDSL) {
        steps.addAll(step)
    }

    fun result(output: String) {
        this.result = result
    }

}

fun workflow(init: WorkflowDSL.() -> Unit): WorkflowDSL {
    return WorkflowDSL().apply(init)
}