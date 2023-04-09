package costaber.com.github.omniflow

import costaber.com.github.omniflow.dsl.execution
import costaber.com.github.omniflow.dsl.step
import costaber.com.github.omniflow.dsl.workflow
import org.junit.Test

internal class WorkflowTest {

    @Test
    fun test() {
        workflow {
            zone("eu-west")
            provider("GCP")
            name("myFirstWorkflow")
            description("My first Workflow")
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
        }.build()
    }
}