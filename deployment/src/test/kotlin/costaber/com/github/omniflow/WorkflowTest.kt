package costaber.com.github.omniflow

import costaber.com.github.omniflow.cloud.provider.aws.deployer.AmazonCloudDeployer
import costaber.com.github.omniflow.cloud.provider.aws.deployer.AmazonDeployContext
import costaber.com.github.omniflow.cloud.provider.google.deployer.GoogleCloudDeployer
import costaber.com.github.omniflow.cloud.provider.google.deployer.GoogleDeployContext
import costaber.com.github.omniflow.dsl.*
import costaber.com.github.omniflow.model.call.HttpMethod.GET
import org.junit.Test
import java.util.*

internal class WorkflowTest {

    private val googleWorkflow = workflow {
        name("myFirstWorkflow")
        description("My first Workflow")
        params("input")
        steps(
            step {
                name("assign_vars")
                description("Initialize variables")
                context(
                    assign {
                        variable("number" equal 0)
                        variable("randomNumber" equal Random().nextInt())
                    }
                )
            },
            step {
                name("increment1")
                description("Increment the input number by one")
                context(
                    call {
                        method(GET)
                        host("https://us-central1-function-test-366510.cloudfunctions.net")
                        path("/function-1")
                        query("increment" to variable("input.number"))
                        result("result1")
                    }
                )
            },
            step {
                name("increment2")
                description("Increment the input number by one, second time")
                context(
                    call {
                        method(GET)
                        host("https://us-central1-function-test-366510.cloudfunctions.net")
                        path("/function-1")
                        query("increment" to variable("result1.body"))
                        result("result2")
                    }
                )
            },
            step {
                name("increment3")
                description("Increment the input number by one, third time")
                context(
                    call {
                        method(GET)
                        host("https://us-central1-function-test-366510.cloudfunctions.net")
                        path("/function-1")
                        query("increment" to variable("result2.body"))
                        result("result3")
                    }
                )
            }
        )
        result("result3.body")
    }

    private val amazonWorkflow = workflow {
        name("myFirstWorkflow")
        description("A description of my state machine")
        steps(
            step {
                name("Variables")
                description("Initialize variables")
                context(
                    assign {
                        variable("number" equal 0)
                        variable("randomNumber" equal Random().nextInt())
                    }
                )
            },
            step {
                name("Increment1")
                description("Increment the input number by one")
                context(
                    call {
                        method(GET)
                        host("ak7u4tmof2.execute-api.us-east-1.amazonaws.com")
                        path("/default/increment")
                        query("increment" to variable("number"))
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
                    call {
                        method(GET)
                        host("ak7u4tmof2.execute-api.us-east-1.amazonaws.com")
                        path("/default/increment")
                        query("increment" to variable(""))
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
                    call {
                        method(GET)
                        host("ak7u4tmof2.execute-api.us-east-1.amazonaws.com")
                        path("/default/increment")
                        query("increment" to variable(""))
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
        result("result")
    }

    private val generalWorkflow = workflow {
        name("calculatorWorkflow")
        description("Calculator example")
        steps(
            step {
                name("Variables")
                description("Initialize variables")
                context(
                    assign {
                        variable("a" equal Random().nextInt())
                        variable("b" equal Random().nextInt())
                    }
                )
            },
            step {
                name("Sum")
                description("Sum 2 random numbers")
                context(
                    call {
                        method(GET)
                        host("https://us-central1-workflow-test-380423.cloudfunctions.net")
                        path("/calculator")
                        query(
                            "number1" to variable("a"),
                            "number2" to variable("b"),
                            "op" to value("add")
                        )
                        result("sumResult")
                    }
                )
            },
            step {
                name("DivByTwo")
                description("Divide the previous result by 2")
                context(
                    call {
                        method(GET)
                        host("https://us-central1-workflow-test-380423.cloudfunctions.net")
                        path("/calculator")
                        query(
                            "number1" to variable("sumResult"),
                            "number2" to value(2),
                            "op" to value("div")
                        )
                        result("divResult")
                    }
                )
            }
        )
        result("divResult")
    }

    @Test
    fun `test google full deployment`() {
        val deployer = GoogleCloudDeployer.Builder().build()
        val googleDeployContext = GoogleDeployContext(
            projectId = "workflow-test-380423",
            zone = "us-east1",
            serviceAccount = "projects/workflow-test-380423/serviceAccounts/" +
                    "service-account-test@workflow-test-380423.iam.gserviceaccount.com",
            workflowId = "calculator_workflow",
            workflowDescription = "Calculator Workflow for testing",
            workflowLabels = mapOf("environment" to "testing", "app" to "omni-flow"),
        )
        deployer.deploy(generalWorkflow, googleDeployContext)
    }

    @Test
    fun `test amazon full deployment`() {
        val deployer = AmazonCloudDeployer.Builder().build()
        val amazonDeployContext = AmazonDeployContext(
            roleArn = "arn:aws:iam::610299836666:role/aws-service-role/trustedadvisor.amazonaws.com/" +
                    "AWSServiceRoleForTrustedAdvisor",
            region = "us-east-1",
            tags = mapOf(
                "environment" to "testing",
                "app" to "omni-flow"
            ),
            stateMachineName = "state_machine_calculator"
        )
        deployer.deploy(generalWorkflow, amazonDeployContext)
    }
}