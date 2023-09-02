# OmniFlow

## Overview

OmniFlow is a versatile tool designed in [Kotlin][kotlin] to streamline the process of defining and deploying workflows across various cloud
providers. Developers can easily create workflows along with their corresponding steps, and metadata, using a dedicated 
[Domain-Specific Language][dsl]. The primary goal is to abstract the intricate details of workflow definition and 
deployment, enabling a seamless translation of workflows across different cloud providers, without the need to get 
familiarized with the schemas of each provider. Omniflow is responsible for translating the defined workflow to the
language, usually [JSON][json] and [YAML][yaml] formats, used by the selected cloud provider. 

## How to Build

Use maven wrapper [Maven Wrapper][maven-wrapper]:

```shell script
./mvnw clean package
```

## How to Test

```shell script
./mvnw clean test
```

## How to Benchmark

To run the benchmarks successfully it is required the following environment variables:
* AWS_ROLE_ARN - Amazon Resource Name format, for example `arn:partition:service:region:account:resource`
* AWS_REGION - Region, for example `us-east-1`
* GOOGLE_PROJECT_ID - Globally unique identifier for your project
* GOOGLE_ZONE - Zone, for example `us-west1`
* GOOGLE_SERVICE_ACCOUNT - Service account principals

```shell script
java -jar /benchmarks/target/benchmarks.jar <path-to-txt-file> 
```

## How to Use

### Workflow Definition

A workflow is composed of a name, description, input, steps, and result. The **name** is the workflow identifier, the 
**description** is useful to explain the purpose of the workflow, **input** is the variable name that will be assigned 
all the input parameters passed to the workflow when executed; **steps**, a set of steps to define the workflow's 
behavior; and lastly the **result** is the variable that corresponds to the execution output of the workflow. The name,
steps, and result are mandatory, the rest fields are optional.

```kotlin
workflow {
    name("workflow-name") //mandatory
    description("Workflow Description") //optional
    input("args") //optional
    steps() //mandatory
    result("workflow-result") //mandatory
}
```

To create the workflow behavior is required to define one or more steps within the **steps** field. A step has a name 
(identifier), description, and the context. All the fields are mandatory. The context of a step corresponds to the step 
behavior, for example, make HTTP requests, assign variables, and use conditions like if-else, or when Kotlin expressions. 
Currently, is supported by three types of contexts: **Assign**, **Call**, and **Conditional**.

```kotlin
step {
    name("call-step") //mandatory
    description("Make request to example API") //mandatory
    context() //mandatory
}
```

#### Call Step

The `Call Step` represents that the step will make an HTTP request to the specified API with the necessary parameters, 
including method, host, path, authentication, body, headers, query parameters, timeoutInSeconds, and result. The query 
field supports the usage of variables. The method, host, path, and result are mandatory fields. The result is the 
variable name, where the response of the API will be saved.

```kotlin
step {
    name("call-step")
    description("Make HTTP request to API")
    context(
        call {
            method(HttpMethod.POST) //mandatory
            host("example-api.com") //mandatory
            path("/default/v1/") //mandatory
            authentication(
                type("OAuth2")
            ) //optional
            body(
                "Hello World!"
            ) //optional
            header(
                "Content-Type" to "application/json"
            ) //optional
            query(
                "filter" to value("name")
            ) //optional
            timeoutInSeconds(5) //optional
            result("sumResult") //mandatory
        }
    )
}
```

#### Assign Step

The assignment of variables is pretty useful when it comes to workflows. The way to initialize or assign variables is 
required the `Assign Step`. It is possible to create any type of variable, where the potential of Kotlin can be used to 
process any value, for example: `Random().nextInt()`. When the assign context is used, it is required to assign at 
least one variable.


```kotlin
step {
    name("assign-step")
    description("Initialize variables")
    context(
        assign {
            variable("number" equal Random().nextInt())
            variable("text" equal "Hello World!")
            variable("isOk" equal true)
        }
    )
}
```

#### Condition Step

Controlling the execution flux via expressions is defined using the `Conditional Step`, where can be defined a
conditional expression with multiple branches, similar to the `when` statement in Kotlin language. The jump and default
fields are the step names in case of the condition is satisfied. The support binary expression in the condition are:
`equalTo`, `notEqualTo`, `greaterThan`, `greaterThanOrEqual`, `lessThan`, `lessThanOrEqual`.

```kotlin
step {
    name("condition-step")
    description("condition")
    context(
        switch {
            conditions(
                condition {
                    match(variable("c") equalTo value(0)) //mandatory
                    jump("Assign1ToC") //mandatory
                },
                condition {
                    match(variable("c") greaterThan value(0)) //mandatory
                    jump("DivWithC") //mandatory
                }
            )
            default("Assign1ToC") //optional
        }
    )
}
```

### Cloud Provider Deployment

After the workflow definition, you can deploy it in the required cloud provider. The deployment of Amazon State Machines
and Google Workflows are supported by `OmniFlow`. Each cloud provider has specific arguments, including user information 
(authentication), such as Google's service account, and Amazon's arn. The labels and tags are optional metadata.

```kotlin
val googleDeployContext = GoogleDeployContext(
    projectId = "<your-project-id>",
    zone = "<zone>",
    serviceAccount = "<your-service-account>",
    workflowId = "example_workflow",
    workflowDescription = "Workflow for testing",
    workflowLabels = mapOf("environment" to "testing", "app" to "omni-flow"),
)
GoogleCloudDeployer.Builder()
    .build()
    .deploy(
        workflow = workflow,
        deployContext = googleDeployContext,
    )
```

```kotlin
val amazonDeployContext = GoogleDeployContext(
    roleArn = "<your-role-arn>",
    region = "<zone>",
    tags = mapOf(
        "environment" to "testing",
        "app" to "omni-flow"
    ),
    stateMachineName = "example_state_machine"
)
AmazonCloudDeployer.Builder()
    .build()
    .deploy(
        workflow = workflow,
        deployContext = amazonDeployContext,
    )
```


> **_NOTE_** ⚠️
> 
> To make requests to Amazon Web Services using the AWS SDK for Kotlin, the SDK uses cryptographically-signed 
> credentials issued by AWS. The SDK attempts to load credentials from the `AWS_ACCESS_KEY_ID`, `AWS_SECRET_ACCESS_KEY`, 
> and `AWS_SESSION_TOKEN` environment variables. Application Default Credentials (ADC) is a strategy used by the Google 
> authentication libraries to automatically find credentials based on the application environment. ADC searches for 
> credentials in the following `GOOGLE_APPLICATION_CREDENTIALS` environment variable.



## Additional Documentations

Please refer to the following documents if you need more details on the tools and frameworks used by this application.

[AWS Step Functions][aws-step-functions]

[AWS Credentials Provider][aws-credentials-provider]

[Google Cloud Workflow][google-cloud-workflows]

[Google Cloud Application Default Credentials][google-cloud-adc]

[kotlin]: https://kotlinlang.org/
[dsl]: https://www.jetbrains.com/mps/concepts/domain-specific-languages/
[json]: https://www.json.org/
[yaml]: https://yaml.org/
[maven-wrapper]: https://github.com/takari/maven-wrapper
[aws-step-functions]: https://aws.amazon.com/step-functions
[aws-credentials-provider]: https://docs.aws.amazon.com/sdk-for-kotlin/latest/developer-guide/credential-providers.html
[google-cloud-workflows]: https://cloud.google.com/workflows
[google-cloud-adc]: https://cloud.google.com/docs/authentication/application-default-credentials
