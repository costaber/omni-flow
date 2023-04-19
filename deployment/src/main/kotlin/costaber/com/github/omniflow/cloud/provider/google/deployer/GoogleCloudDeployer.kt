package costaber.com.github.omniflow.deployer.google

import costaber.com.github.omniflow.deployer.CloudDeployer
import costaber.com.github.omniflow.mappers.google.GoogleWorkflowMapper
import costaber.com.github.omniflow.model.Workflow
import costaber.com.github.omniflow.service.GcpWorkflowService

class GoogleCloudDeployer(
    private val googleWorkflowMapper: GoogleWorkflowMapper,
    private val gcpWorkflowService: GcpWorkflowService,
) : CloudDeployer<GoogleDeployContext> {

    override fun deploy(workflow: Workflow, deployContext: GoogleDeployContext) {
        val content = googleWorkflowMapper.map(workflow)
        gcpWorkflowService.deploy(
            projectId = deployContext.projectId,
            zone = deployContext.zone,
            serviceAccount = deployContext.serviceAccount,
            workflowId = deployContext.workflowId,
            workflowDescription = deployContext.workflowDescription,
            workflowLabels = deployContext.workflowLabels,
            workflowSourceContents = content,
        )
    }
}