package costaber.com.github.omniflow.resource.util

import costaber.com.github.omniflow.renderer.IndentedRenderingContext
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class IndentedRenderingContextExtensionTest {

    @Test
    fun `render has expected results`() {
        val indentedRenderingContext = IndentedRenderingContext(
            indentationLevel = 0,
            stringBuilder = StringBuilder(),
        )
        val expectedResult = "Example 1!\nExample 2!\n"

        val actualResult = render(indentedRenderingContext) {
            add("Example 1")
            append("!")
            addEmptyLine()
            addLine("Example 2!")
        }

        expectThat(actualResult).isEqualTo(expectedResult)
    }

    @Test
    fun `render returns empty string`() {
        val indentedRenderingContext = IndentedRenderingContext(
            indentationLevel = 0,
            stringBuilder = StringBuilder(),
        )
        val expectedResult = ""

        val actualResult = render(indentedRenderingContext) {}

        expectThat(actualResult).isEqualTo(expectedResult)
    }
}