package org.example.challenge.faas.domain

open class Step(
    val name: String,
    val description: String,
    val type: StepType = StepType.EXECUTION,
    val context: Context,
) {

    override fun toString() = "${Step::class.simpleName}(" +
            "${Step::name.name}=$name, " +
            "${Step::description.name}=$description, " +
            "${Step::type.name}=$type, " +
            "${Step::context.name}=$context )"
}