package costaber.com.github.omniflow.renderer

interface IndentedNodeRenderer : NodeRenderer<String> {

    override fun beginRender(renderingContext: RenderingContext): String {
        val indentedRenderingContext = renderingContext as IndentedRenderingContext
        val render = internalBeginRender(indentedRenderingContext)
        indentedRenderingContext.incIndentationLevel()
        return render
    }

    override fun endRender(renderingContext: RenderingContext): String {
        val indentedRenderingContext = renderingContext as IndentedRenderingContext
        indentedRenderingContext.decIndentationLevel()
        return internalEndRender(indentedRenderingContext)
    }

    fun internalBeginRender(renderingContext: IndentedRenderingContext): String

    fun internalEndRender(renderingContext: IndentedRenderingContext): String

}