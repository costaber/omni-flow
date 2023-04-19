package costaber.com.github.omniflow.model.execution

import costaber.com.github.omniflow.model.Node

data class Authentication(
    val type: String,
    val scope: String? = null,
    val scopes: String? = null,
    val audience: String? = null,
) : Node