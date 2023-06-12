package costaber.com.github.omniflow.cloud.provider.google.renderer

import costaber.com.github.omniflow.model.Node
import costaber.com.github.omniflow.model.SwitchContext
import costaber.com.github.omniflow.renderer.IndentedNodeRenderer
import costaber.com.github.omniflow.renderer.IndentedRenderingContext
import costaber.com.github.omniflow.resource.util.render

class GoogleSwitchRenderer(private val switchContext: SwitchContext) : IndentedNodeRenderer {

    override val element: Node = switchContext

    override fun internalBeginRender(renderingContext: IndentedRenderingContext): String =
        render(renderingContext) {
            add("switch:")
        }


    override fun internalEndRender(renderingContext: IndentedRenderingContext): String {
        return switchContext.default?.let {
            render(renderingContext) {
                add("next: $it")
            }
        }.orEmpty()
    }
}