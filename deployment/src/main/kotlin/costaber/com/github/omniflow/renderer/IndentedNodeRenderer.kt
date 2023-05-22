package costaber.com.github.omniflow.renderer

import costaber.com.github.omniflow.resource.NUM_SPACES

interface IndentedNodeRenderer: NodeRenderer<String> {

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
        return (renderingContext as IndentedRenderingContext).indentationLevel
    }

    fun getIndentationString(renderingContext: RenderingContext): String =
        String.format("%${NUM_SPACES * getIndentationLevel(renderingContext)}s", "")

    fun incIndentationLevel(renderingContext: RenderingContext) {
        val context = renderingContext as IndentedRenderingContext
        context.indentationLevel += 1
    }

    fun decIndentationLevel(renderingContext: RenderingContext) {
        val context = renderingContext as IndentedRenderingContext
        context.indentationLevel -= 1
    }

}