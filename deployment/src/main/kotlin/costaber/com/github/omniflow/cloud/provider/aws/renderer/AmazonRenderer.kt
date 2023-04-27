package costaber.com.github.omniflow.cloud.provider.aws.renderer

import costaber.com.github.omniflow.renderer.LazyNodeRenderer
import costaber.com.github.omniflow.renderer.RenderingContext

interface AmazonRenderer : LazyNodeRenderer<String> {

    override fun beginRender(renderingContext: RenderingContext): String {
        return internalBeginRender(renderingContext)
    }

    override fun endRender(renderingContext: RenderingContext): String {
        return internalEndRender(renderingContext)
    }

    fun internalBeginRender(renderingContext: RenderingContext): String

    fun internalEndRender(renderingContext: RenderingContext): String

}