package costaber.com.github.omniflow.model.execution

data class Authentication(
    val type: String,
    val scope: String? = null,
    val scopes: String? = null,
    val audience: String? = null,
)