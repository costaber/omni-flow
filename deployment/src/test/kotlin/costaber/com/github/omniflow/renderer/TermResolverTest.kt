package costaber.com.github.omniflow.renderer

import costaber.com.github.omniflow.model.Term
import costaber.com.github.omniflow.model.Variable
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class TermResolverTest {

    @ParameterizedTest
    @MethodSource("costaber.com.github.omniflow.util.data.TermDataProvider#termResolveDataProvider")
    fun `resolver has expected results`(term: Term<*>, expectedResult: String) {
        val actualResult = object : TermResolver {
            override fun resolveVariable(variable: Variable): String =
                variable.name
        }.resolve(term)
        expectThat(actualResult).isEqualTo(expectedResult)
    }
}