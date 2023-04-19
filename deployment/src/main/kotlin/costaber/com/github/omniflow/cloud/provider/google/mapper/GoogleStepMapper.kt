package costaber.com.github.omniflow.mappers.google

import costaber.com.github.omniflow.mappers.Mapper
import costaber.com.github.omniflow.model.Step
import costaber.com.github.omniflow.model.execution.ExecutionContext
import java.util.*

class GoogleStepMapper : Mapper<Collection<Step>, String> {

    override fun map(steps: Collection<Step>): String {
        return steps.map { mapStep(it) }.joinToString { "\n" }
    }

    private fun mapStep(step: Step): String {
        val executionContext = step.context as ExecutionContext
        return """
            - ${step.name}:
                call: http.${executionContext.method.lowercase(Locale.getDefault())}
                args:
                    url: ${executionContext.url}
                    query: 
                        ${executionContext.query}    
                result: ${executionContext.result}
        """.trimIndent()
    }
}