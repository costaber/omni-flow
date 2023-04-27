package costaber.com.github.omniflow.cloud.provider.aws.renderer

import costaber.com.github.omniflow.model.Node
import costaber.com.github.omniflow.model.Workflow
import costaber.com.github.omniflow.renderer.RenderingContext

class AmazonStateMachineRenderer(private val workflow: Workflow) : AmazonRenderer {

    override val element: Node = workflow

    override fun internalBeginRender(renderingContext: RenderingContext): String {
        TODO("Not yet implemented")
    }

    override fun internalEndRender(renderingContext: RenderingContext): String {
        TODO("Not yet implemented")
    }

}