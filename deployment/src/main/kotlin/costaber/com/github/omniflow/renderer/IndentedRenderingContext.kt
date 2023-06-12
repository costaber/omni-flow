package costaber.com.github.omniflow.renderer

import costaber.com.github.omniflow.resource.TAB

open class IndentedRenderingContext(
    private var indentationLevel: Int = 0,
    private val stringBuilder: StringBuilder = StringBuilder(),
) : RenderingContext {

    fun getIndentationString(): String =
        TAB.repeat(indentationLevel)

    fun incIndentationLevel() {
        indentationLevel += 1
    }

    fun decIndentationLevel() {
        indentationLevel -= 1
    }

    fun addLine(value: String) {
        stringBuilder.appendLine("${getIndentationString()}$value")
    }

    fun addEmptyLine() {
        stringBuilder.appendLine(getIndentationString())
    }

    fun add(value: String) {
        stringBuilder.append("${getIndentationString()}$value")
    }

    fun tab(block: IndentedRenderingContext.() -> Unit) {
        incIndentationLevel()
        block()
        decIndentationLevel()
    }

    fun getString() = stringBuilder.toString().also {
        stringBuilder.clear()
    }

}
