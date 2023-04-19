package costaber.com.github.omniflow.cloud.provider.google.deployer

import costaber.com.github.omniflow.deployer.CloudDeployer
import costaber.com.github.omniflow.cloud.provider.google.mapper.GoogleWorkflowMapper
import costaber.com.github.omniflow.model.Workflow
import costaber.com.github.omniflow.cloud.provider.google.service.GcpWorkflowService

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