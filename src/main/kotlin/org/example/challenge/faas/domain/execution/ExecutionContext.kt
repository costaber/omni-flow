package org.example.challenge.faas.domain.execution

import org.example.challenge.faas.domain.Context

data class ExecutionContext(
    val method: String,
    val result: String,
    val url: String,
    val authentication: Authentication? = null,
    val body: Map<String, String> = emptyMap(),
    val header: Map<String, String> = emptyMap(),
    val query: Map<String, String> = emptyMap(),
    val timeoutInSeconds: Long? = null,
) : Context