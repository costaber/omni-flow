package org.example.challenge.faas

import org.example.challenge.faas.dsl.CloudProvider
import org.example.challenge.faas.dsl.execution
import org.example.challenge.faas.dsl.scope
import org.example.challenge.faas.dsl.workflow

fun main(args: Array<String>) {

    scope {
        workflow {
            zone("eu-west")
            provider(CloudProvider.GoogleCloudPlatform)
            name("myFirstWorkflow")
            description("My first Workflow")
            steps(
                execution {
                    method("GET")
                    url("https://us-central1-function-test-366510.cloudfunctions.net/function-1")
                    query("increment" to "::input.number::")
                    header("Content-Type" to "application/json")
                    result("increment1")
                },
                execution {
                    method("GET")
                    url("https://us-central1-function-test-366510.cloudfunctions.net/function-1")
                    query("increment" to "::increment1.body::")
                    header("Content-Type" to "application/json")
                    result("increment2")
                },
                execution {
                    method("GET")
                    url("https://us-central1-function-test-366510.cloudfunctions.net/function-1")
                    query("increment" to "::increment2.body::")
                    header("Content-Type" to "application/json")
                    result("increment3")
                },
            )
            result("::increment3::")
        }
    }
}