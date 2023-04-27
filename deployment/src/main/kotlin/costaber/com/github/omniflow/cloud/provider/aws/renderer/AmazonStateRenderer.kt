package costaber.com.github.omniflow.cloud.provider.aws.renderer

import costaber.com.github.omniflow.model.Node
import costaber.com.github.omniflow.model.Step
import costaber.com.github.omniflow.model.execution.ExecutionContext
import costaber.com.github.omniflow.renderer.RenderingContext
import costaber.com.github.omniflow.resource.TAB

class AmazonStateRenderer(private val step: Step) : AmazonRenderer{

    override val element: Node = step

    override fun internalBeginRender(renderingContext: RenderingContext): String {
        TODO("Not yet implemented")
    }

    override fun internalEndRender(renderingContext: RenderingContext): String {
        TODO("Not yet implemented")
    }

}