package costaber.com.github.omniflow.service

import com.google.cloud.workflows.v1.*
import costaber.com.github.omniflow.resource.exception.ExternalCloudClientException

class GcpWorkflowService {

    /**
     * REQUIRED 1 ENV VARIABLE
     * - GOOGLE_APPLICATION_CREDENTIALS -> path to a json file defining the credentials
     *
     * TODO THE ARGUMENTS ALSO HAVE SOME REQUIREMENTS --- MUST BE INDICATED!!!!
     * @param projectId - bla bla bla
     */
    fun deploy(
        projectId: String,
        zone: String = "us-east1",
        serviceAccount: String,
        workflowId: String,
        workflowDescription: String,
        workflowLabels: Map<String, String> = emptyMap(),
        workflowSourceContents: String,
    ) {
        val workflowsClient = WorkflowsClient.create()
        return try {
            val projectLocation = LocationName.of(projectId, zone).toString()
            val workflow = Workflow.newBuilder()
                .setDescription(workflowDescription)
                .setSourceContents(workflowSourceContents)
                .putAllLabels(workflowLabels)
                .setServiceAccount(serviceAccount)
                .build()
            val createWorkflowRequest = CreateWorkflowRequest.newBuilder()
                .setParent(projectLocation)
                .setWorkflowId(workflowId)
                .setWorkflow(workflow)
                .build()
            val response = workflowsClient.createWorkflowAsync(createWorkflowRequest)
            val workflowResponse = response.get()
            println("Workflow: $workflowResponse")
            val responseMetadata = response.metadata.get()
            println("Metadata: $responseMetadata")
        } catch (exception: Exception) {
            throw ExternalCloudClientException(
                workflowName = workflowId,
                cloudProvider = "GCP",
                message = exception.message,
                throwable = exception
            )
        }
    }

}