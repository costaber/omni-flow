package org.example.challenge.faas.domain

data class Workflow(
    val name: String,
    val zone: String,
    val provider: CloudProvider,
    val description: String? = null,
    val definition: Collection<Step> = emptyList(),
    val result: String,
)