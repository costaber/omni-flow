package costaber.com.github.omniflow.mappers

import costaber.com.github.omniflow.model.Workflow

class GoogleMapper : Mapper<Workflow, String> {

    override fun map(workflow: Workflow): String {
        return """
            main:
                params: [input]
                steps:
                    mapStepsHere(workflow.steps, workflow.result)
        """.trimIndent()
    }

}