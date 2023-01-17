package org.example.challenge.faas.domain.execution

import org.example.challenge.faas.domain.Context

data class ExecutionContext(
    val url: String,
    val method: String,
    val header: Map<String, String> = emptyMap(),
    val query: Map<String, String> = emptyMap(),
    val body: Map<String, String> = emptyMap(),
    val authentication: Authentication? = null,
    val timeoutInSeconds: Long? = null,
    val result: String
) : Context