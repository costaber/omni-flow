package costaber.com.github.omniflow.cloud.provider.google.renderer

import costaber.com.github.omniflow.model.Node
import costaber.com.github.omniflow.model.Step
import costaber.com.github.omniflow.renderer.IndentedNodeRenderer
import costaber.com.github.omniflow.renderer.RenderingContext

class GoogleStepRenderer(private val step: Step) : IndentedNodeRenderer {

    override val element: Node = step

    override fun internalBeginRender(renderingContext: RenderingContext): String {
        val prefix = getIndentationString(renderingContext)
        return "${prefix}- ${step.name}:"
    }

    override fun internalEndRender(renderingContext: RenderingContext) = "" // nothing

}