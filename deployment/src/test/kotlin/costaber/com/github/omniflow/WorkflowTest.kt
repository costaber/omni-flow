package costaber.com.github.omniflow

import costaber.com.github.omniflow.cloud.provider.google.deployer.GoogleCloudDeployer
import costaber.com.github.omniflow.cloud.provider.google.deployer.GoogleDeployContext
import costaber.com.github.omniflow.cloud.provider.google.service.GoogleWorkflowService
import costaber.com.github.omniflow.dsl.execution
import costaber.com.github.omniflow.dsl.step
import costaber.com.github.omniflow.dsl.workflow
import costaber.com.github.omniflow.model.execution.HttpMethod.*
import costaber.com.github.omniflow.traversor.DepthFirstNodeTraversor
import org.junit.Test

internal class WorkflowTest {

    val workflow = workflow {
        name("myFirstWorkflow")
        description("My first Workflow")
        params("input")
        steps(
            step {
                name("increment1")
                description("Increment the input number by one")
                context(
                    execution {
                        method(GET)
                        url("https://us-central1-function-test-366510.cloudfunctions.net/function-1")
                        query("increment" to "\${input.number}")
                        result("result1")
                    }
                )
            },
            step {
                name("increment2")
                description("Increment the input number by one, second time")
                context(
                    execution {
                        method(GET)
                        url("https://us-central1-function-test-366510.cloudfunctions.net/function-1")
                        query("increment" to "\${result1.body}")
                        result("result2")
                    }
                )
            },
            step {
                name("increment3")
                description("Increment the input number by one, third time")
                context(
                    execution {
                        method(GET)
                        url("https://us-central1-function-test-366510.cloudfunctions.net/function-1")
                        query("increment" to "\${result2.body}")
                        result("result3")
                    }
                )
            }
        )
        result("\${result3.body}")
    }

    @Test
    fun test() {


        // version 1
//        val mapper = GoogleMapper()
//        val gcpWorkflowService = GcpWorkflowService()
//        val context = GoogleDeployContext(
//            projectId = "",
//            zone = "",
//            serviceAccount = "",
//            workflowId = "",
//            workflowDescription = "",
//            workflowLabels = mapOf()
//        )
//        GoogleCloudDeployer(mapper, gcpWorkflowService).deploy(workflow, context)

        // version 2
//        AwsStateMachineService.Builder()
//            .stateMachineName("")
//            .arnRole("")
//            .zone("")
//            .build()
//            .deploy(workflow)


    }


    @Test
    fun `test visitor`() {
        val nodeTraversor = DepthFirstNodeTraversor()
        val googleWorkflowService = GoogleWorkflowService()
        val deployer = GoogleCloudDeployer(nodeTraversor, googleWorkflowService)

        val googleDeployContext = GoogleDeployContext(
            projectId = "workflow-test-380423",
            zone = "us-east1",
            serviceAccount = "projects/workflow-test-380423/serviceAccounts/" +
                    "service-account-test@workflow-test-380423.iam.gserviceaccount.com",
            workflowId = "testing_client_library_3",
            workflowDescription = "Testing the creation of a workflow using Client Library",
            workflowLabels = mapOf("environment" to "testing", "app" to "omni-flow"),
        )
        deployer.deploy(workflow, googleDeployContext)
    }
}