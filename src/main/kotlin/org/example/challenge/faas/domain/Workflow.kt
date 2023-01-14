package org.example.challenge.faas.domain

data class Workflow(
    val name: String,
    val description: String,
    val definition: Collection<Step<Any, Any>>,
)