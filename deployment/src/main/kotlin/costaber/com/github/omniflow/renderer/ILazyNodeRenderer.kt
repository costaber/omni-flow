package costaber.com.github.omniflow.renderer

interface ILazyNodeRenderer {
    fun render(renderingContext: IRenderingContext): String
}