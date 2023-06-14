package costaber.com.github.omniflow.cloud.provider.google.renderer

import costaber.com.github.omniflow.model.Variable
import costaber.com.github.omniflow.renderer.TermResolver

object GoogleTermResolver : TermResolver {

    override fun resolveVariable(variable: Variable): String =
        "\${${variable.name}}"
}