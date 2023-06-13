package costaber.com.github.omniflow.cloud.provider.aws.renderer

import costaber.com.github.omniflow.cloud.provider.aws.*
import costaber.com.github.omniflow.model.AssignContext
import costaber.com.github.omniflow.model.Node
import costaber.com.github.omniflow.renderer.IndentedNodeRenderer
import costaber.com.github.omniflow.renderer.IndentedRenderingContext
import costaber.com.github.omniflow.resource.util.render

class AmazonPassRenderer(private val assignContext: AssignContext) : IndentedNodeRenderer {

    override val element: Node = assignContext

    override fun internalBeginRender(renderingContext: IndentedRenderingContext): String {
        val amazonContext = renderingContext as AmazonRenderingContext
        amazonContext.setVariables(assignContext.variables)
        return render(renderingContext) {
            addLine(AMAZON_PASS_TYPE)
            add(AMAZON_START_RESULT)
        }
    }

    override fun internalEndRender(renderingContext: IndentedRenderingContext): String {
        val amazonContext = renderingContext as AmazonRenderingContext
        val nextStepName = amazonContext.getNextStepName()
        return render(renderingContext) {
            addLine(AMAZON_CLOSE_OBJECT)
            if (nextStepName == null) {
                add(AMAZON_END)
            } else {
                add("$AMAZON_NEXT\"${nextStepName}\"")
            }
        }
    }

}