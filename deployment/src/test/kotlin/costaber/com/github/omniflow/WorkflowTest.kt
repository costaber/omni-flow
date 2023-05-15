package costaber.com.github.omniflow

import costaber.com.github.omniflow.cloud.provider.aws.deployer.AmazonCloudDeployer
import costaber.com.github.omniflow.cloud.provider.aws.deployer.AmazonDeployContext
import costaber.com.github.omniflow.cloud.provider.google.deployer.GoogleCloudDeployer
import costaber.com.github.omniflow.cloud.provider.google.deployer.GoogleDeployContext
import costaber.com.github.omniflow.dsl.*
import costaber.com.github.omniflow.model.execution.HttpMethod.GET
import org.junit.Test
import java.util.*

internal class WorkflowTest {

    private val workflow = workflow {
        name("myFirstWorkflow")
        description("My first Workflow")
        variables(
            variable {
                name("number")
                value(0)
            },
            variable {
                name("randomNumber")
                value(Random().nextInt())
            },
        )
        steps(
            step {
                name("increment1")
                description("Increment the input number by one")
                context(
                    execution {
                        method(GET)
                        host("https://us-central1-function-test-366510.cloudfunctions.net")
                        path("/function-1")
                        query("increment" to variable("input.number"))
                        header("Content-Type" to "application/json")
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
                        host("https://us-central1-function-test-366510.cloudfunctions.net")
                        path("/function-1")
                        query("increment" to "\${result1.body}")
                        header("Content-Type" to "application/json")
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
                        host("https://us-central1-function-test-366510.cloudfunctions.net")
                        path("/function-1")
                        query("increment" to "\${result2.body}")
                        header("Content-Type" to "application/json")
                        result("result3")
                    }
                )
            }
        )
        result("\${result3.body}")
    }

    private val workflow2 = workflow {
        name("myFirstWorkflow")
        description("A description of my state machine")
        params("input")
        steps(
            step {
                name("Increment1")
                description("Increment the input number by one")
                context(
                    execution {
                        method(GET)
                        host("ak7u4tmof2.execute-api.us-east-1.amazonaws.com")
                        path("/default/increment")
                        query("increment.$" to "States.Array(States.Format('{}', \$.number))")
                        authentication(
                            authentication {
                                type("IAM_ROLE")
                            }
                        )
                        result("number")
                    }
                )
            },
            step {
                name("Increment2")
                description("Increment the input number by one, second time")
                context(
                    execution {
                        method(GET)
                        host("ak7u4tmof2.execute-api.us-east-1.amazonaws.com")
                        path("/default/increment")
                        query("increment.\$" to "States.Array(States.Format('{}', \$.number))")
                        authentication(
                            authentication {
                                type("IAM_ROLE")
                            }
                        )
                        result("number")
                    }
                )
            },
            step {
                name("Increment3")
                description("Increment the input number by one, third time")
                context(
                    execution {
                        method(GET)
                        host("ak7u4tmof2.execute-api.us-east-1.amazonaws.com")
                        path("/default/increment")
                        query("increment.\$" to "States.Array(States.Format('{}', \$.number))")
                        authentication(
                            authentication {
                                type("IAM_ROLE")
                            }
                        )
                        result("number")
                    }
                )
            }
        )
        result("\${result3.body}")
    }

// Version 1
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

// Version 2
//        AwsStateMachineService.Builder()
//            .stateMachineName("")
//            .arnRole("")
//            .zone("")
//            .build()
//            .deploy(workflow)

    @Test
    fun `test google full deployment`() {
        val deployer = GoogleCloudDeployer.Builder().build()
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

    @Test
    fun `test aws full deployment`() {
        val deployer = AmazonCloudDeployer.Builder().build()
        val amazonDeployContext = AmazonDeployContext(
            roleArn = "arn:aws:iam::610299836666:role/aws-service-role/trustedadvisor.amazonaws.com/" +
                    "AWSServiceRoleForTrustedAdvisor",
            region = "us-east-1",
            tags = mapOf(
                "environment" to "testing",
                "app" to "omni-flow"
            ),
            stateMachineName = "state_machine_test_deployment_1"
        )
        deployer.deploy(workflow2, amazonDeployContext)
    }
}