package costaber.com.github.omniflow.mappers.google

import costaber.com.github.omniflow.mappers.Mapper
import costaber.com.github.omniflow.model.Workflow

class GoogleWorkflowMapper(
    private val googleStepMapper: GoogleStepMapper,
) : Mapper<Workflow, String> {

    override fun map(workflow: Workflow): String {
        return """
            main:
                params: [input]
                steps:
                    ${googleStepMapper.map(workflow.steps)}
        """.trimIndent()
    }

}