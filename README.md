# OmniFlow

## Overview

OmniFlow is a versatile tool designed in [Kotlin][kotlin] to streamline the process of defining and deploying workflows
across various cloud providers. Developers can easily create workflows along with their corresponding steps, and 
metadata, using a dedicated [Domain-Specific Language][dsl]. The primary goal is to abstract the intricate details of 
workflow definition and deployment, enabling a seamless translation of workflows across different cloud providers, 
without the need to get familiarized with the schemas of each provider. Omniflow is responsible for translating the 
defined workflow to the language, usually [JSON][json] and [YAML][yaml] formats, used by the selected cloud provider. 

## How to Build

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

Maven Dependency:

```xml
<dependency>
    <groupId>costaber.com.github</groupId>
    <artifactId>omni-flow-deployment</artifactId>
    <version>${omni-flow-deployment.version}</version>
</dependency>
```

### Workflow Definition

At the core of **OmniFlow** lies the fundamental concept of function composition, referred to as a "workflow." A 
workflow is a structured representation of desired steps and their execution order, complemented by essential metadata. 
The workflow serves as an aggregator, encompassing all the behaviors outlined within its constituent steps, executing 
them precisely as defined. A comprehensive workflow definition comprises several critical components: name, which acts 
as the unique identifier for the workflow; description, a human-readable field for explaining the purpose and function 
of the workflow; input, which designates the variable name that will capture all input parameters when the workflow is 
executed; steps, dedicated to specifying the steps that encapsulate the workflow's behavior; result, is where the output
of the workflow's execution is assigned. Among these components, "name," "steps," and "result" are mandatory, ensuring 
the foundational structure of the workflow. The remaining fields, including "description" and "input," are optional, 
allowing flexibility in workflow design.

```kotlin
workflow {
    name("workflow-name") //mandatory
    description("Workflow Description") //optional
    input("args") //optional
    steps() //mandatory
    result("workflow-result") //mandatory
}
```

The basic unit in this architecture is the Step, which, as the name suggests, represents a data processing unit with a 
small objective, such as incrementing a number by one. A Step consists of metadata, such as name (identifier) and 
description, which are human-readable and informative fields. The context of a step corresponds to the step behavior and
purpose, for example, make HTTP requests, assign variables, and use conditions like if-else, or when Kotlin expressions.
All the fields are mandatory. Currently, is supported by three types of contexts: `Assign`, `Call`, and `Conditional`.

```kotlin
step {
    name("call-step") //mandatory
    description("Make request to example API") //mandatory
    context() //mandatory
}
```

#### Call Step

To initiate an HTTP request to an API or cloud function with specific requirements, the `Call Step` must be defined. 
These specifications encompass the method, host, path, authentication, body, headers, query parameters, timeoutInSeconds
, and result. It is important to note that while method, host, path, and result are mandatory fields, the other fields
are optional offering flexibility based on the use case. Currently, the utilization of variables within this step type 
is supported exclusively within query parameters. The `result` field serves as a variable where will be assigned the 
outcome of the request, whether it is a success or failure. Regarding the `body` field, it accommodates both string and
object types, offering versatility in your payload structure.


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

Assigning variables holds significant utility within workflows. To initialize or assign variables, the `Assign Step` is
the essential tool. This step empowers the variables declaration of various types, harnessing the full potential of 
Kotlin to process values dynamically. For instance, you can employ Kotlin expressions like `Random().nextInt()` to 
generate values. When working within the assign context, it is essential to remember that at least one variable must be 
assigned. This fundamental requirement ensures that the workflow operates smoothly and effectively.


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

In order to manage the execution flow through conditional expressions is accomplished using the `Conditional Step`. This
step type allows you to define conditional expressions with multiple branches, similar to the "when" statement in the
Kotlin language. Within the Conditional Step, it is possible to specify two crucial fields: `jump` and `default`. These
fields determine the step names to follow in case the condition is satisfied. The supported binary expressions available
for defining conditions include: `equalTo`, `notEqualTo`, `greaterThan`, `greaterThanOrEqual`, `lessThan`,
`lessThanOrEqual`. When defining a condition it is mandatory to compare a value with a variable. When crafting a 
condition, it's imperative to make comparisons between a variable and a predefined, hardcoded value.

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

Once the workflow is defined, the next crucial step is deploying it to the chosen cloud provider. As mentioned earlier, 
the supported cloud providers include Amazon's State Machines and Google's Cloud Workflows, each with its unique set of 
parameters and credential requirements. To successfully complete the deployment phase, is required to create a 
deployment context tailored to the cloud provider's specific needs. The deployment services necessitate two primary 
inputs: the deployment context and the predefined workflow. It is important to note that while both cloud providers have
their distinct deployment prerequisites, the workflow to be deployed remains the same - the Workflow model previously 
crafted using the DSL.

#### Google Cloud Platform

Google's deployment prerequisites encompass various aspects of Google's project, and service account to which the 
workflow will be linked to. It is required to specify essential details such as the workflow's identifier, description, 
labels, and zone, all of which are outlined in Listing\ref{lst:googleDeploy} for your reference. It is decisive to note
that running this piece of code necessitates user credentials to authenticate the user's identity and verify access to 
the specified project and service account. To provide this proof, the environment variable 
`GOOGLE_APPLICATION_CREDENTIALS` must be set, which should point to the location of a credential JSON file. This JSON 
file can take one of two forms: a credential configuration file for workload identity federation or a service account 
key. It is also worth mentioning that Google's Application Default Credentials strategy is employed by their 
authentication libraries to automatically locate the appropriate credentials based on the application environment, 
simplifying the authentication process.

```kotlin
val googleDeployContext = GoogleDeployContext(
    workflowId = "example_workflow",
    workflowDescription = "Workflow for testing",
    workflowLabels = mapOf("environment" to "testing", "app" to "omni-flow"),
    projectId = "<your-project-id>",
    serviceAccount = "<your-service-account>",
    zone = "<zone>",
)
GoogleCloudDeployer.Builder()
    .build()
    .deploy(
        workflow = workflow,
        deployContext = googleDeployContext,
    )
```

#### Amazon Web Services

Deploying to Amazon follows a similar process to Google's. It entails specifying the State Machine name, the ARN role, 
the region, and optionally, tags. Just like Google, Amazon requires authentication credentials to be defined via 
environment variables: `AWS_ACCESS_KEY_ID`, `AWS_SECRET_ACCESS_KEY`, and `AWS_SESSION_TOKEN`. During Amazon's deployment 
phase, it attempts to load the credentials provider from these environment variables. These credentials are 
cryptographically signed and issued by AWS for secure authentication.

```kotlin
val amazonDeployContext = AmazonDeployContext(
    stateMachineName = "example_state_machine",
    roleArn = "<your-role-arn>",
    tags = mapOf("environment" to "testing", "app" to "omni-flow"),
    region = "<zone>",
)
AmazonCloudDeployer.Builder()
    .build()
    .deploy(
        workflow = workflow,
        deployContext = amazonDeployContext,
    )
```

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
[aws-step-functions]: https://aws.amazon.com/step-functions
[aws-credentials-provider]: https://docs.aws.amazon.com/sdk-for-kotlin/latest/developer-guide/credential-providers.html
[google-cloud-workflows]: https://cloud.google.com/workflows
[google-cloud-adc]: https://cloud.google.com/docs/authentication/application-default-credentials
