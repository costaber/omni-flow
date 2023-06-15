package costaber.com.github.omniflow.generator

import costaber.com.github.omniflow.dsl.value
import costaber.com.github.omniflow.dsl.variable
import costaber.com.github.omniflow.model.*

class WorkflowGenerator {

    // sem dependencias de variaveis
    // com variaveis
    // com condições
    // decisão binaraia e multipla
    fun generate(stepsNumber: Int): Workflow {
        var idx = 0
        val steps = generateSequence {
            listOf(
                generateCallStep("callStep${idx++}"),
                generateAssignStep("assignStep${idx++}"),
                generateConditionalStep("conditionalStep${idx++}")
            )
        }.flatten().take(stepsNumber).toList()
        return Workflow(
            name = "testWorkflow",
            description = "Workflow Example",
            input = "input",
            steps = steps,
            result = "result"
        )
    }

    private fun generateCallStep(name: String) = Step(
        name = name,
        description = "Call Step Example",
        type = StepType.CALL,
        context = CallContext(
            method = HttpMethod.GET,
            host = "example.com",
            path = "/example",
            body = object {
                val firstName = "John";
                val lastName = "Musk"
            },
            header = mapOf("Content-Type" to value("application/json")),
            query = mapOf("number" to value(1)),
            timeoutInSeconds = 5,
            result = "varResult",
        )
    )

    private fun generateAssignStep(name: String) = Step(
        name = name,
        description = "Assign Step Example",
        type = StepType.ASSIGN,
        context = AssignContext(
            variables = listOf(
                VariableInitialization(
                    variable = variable("firstName"),
                    term = value("John")
                ),
                VariableInitialization(
                    variable = variable("lastName"),
                    term = value("Musk")
                ),
                VariableInitialization(
                    variable = variable("index"),
                    term = value(2048)
                )
            )
        )
    )

    private fun generateConditionalStep(name: String) = Step(
        name = name,
        description = "Conditional Step Example",
        type = StepType.CONDITIONAL,
        context = ConditionalContext(
            conditions = listOf(
                Condition(
                    expression = EqualToExpression(variable("example"), value("test")),
                    jump = "example"
                )
            ),
            default = "default"
        )
    )
}