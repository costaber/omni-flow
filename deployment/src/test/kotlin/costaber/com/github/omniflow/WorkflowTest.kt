package costaber.com.github.omniflow

import costaber.com.github.omniflow.cloud.provider.google.deployer.GoogleCloudDeployer
import costaber.com.github.omniflow.cloud.provider.google.deployer.GoogleDeployContext
import costaber.com.github.omniflow.cloud.provider.google.service.GoogleWorkflowService
import costaber.com.github.omniflow.dsl.execution
import costaber.com.github.omniflow.dsl.step
import costaber.com.github.omniflow.dsl.workflow
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
                        method("GET")
                        url("https://us-central1-function-test-366510.cloudfunctions.net/function-1")
                        query("increment" to "::input.number::")
                        header("Content-Type" to "application/json")
                        result("increment1")
                    }
                )
            },
            step {
                name("increment2")
                description("Increment the input number by one, second time")
                context(
                    execution {
                        method("GET")
                        url("https://us-central1-function-test-366510.cloudfunctions.net/function-1")
                        query("increment" to "::increment1.body::")
                        header("Content-Type" to "application/json")
                        result("increment2")
                    }
                )
            },
            step {
                name("increment3")
                description("Increment the input number by one, third time")
                context(
                    execution {
                        method("GET")
                        url("https://us-central1-function-test-366510.cloudfunctions.net/function-1")
                        query("increment" to "::increment2.body::")
                        header("Content-Type" to "application/json")
                        result("increment3")
                    }
                )
            }
        )
        result("increment3")
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
        val deployer = GoogleCloudDeployer(DepthFirstNodeTraversor(), GoogleWorkflowService())
        deployer.deploy(workflow, GoogleDeployContext(
            projectId = "",
            zone = "",
            serviceAccount = "",
            workflowId = "",
            workflowDescription = "",
            workflowLabels = mapOf()
        ))
    }
}