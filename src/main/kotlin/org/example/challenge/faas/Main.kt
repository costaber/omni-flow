package org.example.challenge.faas

import org.example.challenge.faas.dsl.execution
import org.example.challenge.faas.dsl.scope
import org.example.challenge.faas.dsl.step
import org.example.challenge.faas.dsl.workflow

fun main(args: Array<String>) {

    scope {
        deployable(
            workflow {
                zone("eu-west")
                provider("GCP")
                name("myFirstWorkflow")
                description("My first Workflow")
                step(
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
                    }
                )
                result("::increment3::")
            }
        )
    }
}