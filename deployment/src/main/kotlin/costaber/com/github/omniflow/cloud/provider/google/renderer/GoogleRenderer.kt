package costaber.com.github.omniflow.cloud.provider.google.renderer

import costaber.com.github.omniflow.renderer.LazyNodeRenderer
import costaber.com.github.omniflow.renderer.RenderingContext
import costaber.com.github.omniflow.resource.NUM_SPACES

interface GoogleRenderer : LazyNodeRenderer<String> {

    override fun beginRender(renderingContext: RenderingContext): String {
        incIndentationLevel(renderingContext)
        return internalBeginRender(renderingContext)
    }

    override fun endRender(renderingContext: RenderingContext): String {
        val render = internalEndRender(renderingContext)
        decIndentationLevel(renderingContext)
        return render
    }

    fun internalBeginRender(renderingContext: RenderingContext): String

    fun internalEndRender(renderingContext: RenderingContext): String

    fun getIndentationLevel(renderingContext: RenderingContext): Int {
        return (renderingContext as GoogleRenderingContext).indentationLevel
    }

    fun getIndentationString(renderingContext: RenderingContext): String =
        String.format("%${NUM_SPACES * getIndentationLevel(renderingContext)}s", "")

    fun incIndentationLevel(renderingContext: RenderingContext) {
        val context = renderingContext as GoogleRenderingContext
        context.indentationLevel += 1
    }

    fun decIndentationLevel(renderingContext: RenderingContext) {
        val context = renderingContext as GoogleRenderingContext
        context.indentationLevel -= 1
    }

}