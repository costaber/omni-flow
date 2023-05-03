package costaber.com.github.omniflow.cloud.provider.aws.service

import costaber.com.github.omniflow.resource.exception.ExternalCloudClientException
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.sfn.SfnClient
import software.amazon.awssdk.services.sfn.model.CreateStateMachineRequest
import software.amazon.awssdk.services.sfn.model.CreateStateMachineResponse
import software.amazon.awssdk.services.sfn.model.Tag

class AmazonStateMachineService {

    /**
     * TODO REQUIRED 2 ENV VARIABLES:
     * - AWS_ACCESS_KEY_ID -> default credentials
     * - AWS_SECRET_ACCESS_KEY -> default credentials
     * - AWS_REGION -> default Region
     */
    fun createStateMachine(
        roleArn: String,
        region: String,
        tags: Map<String, String>,
        stateMachineName: String,
        stateMachineDefinition: String,
    ): CreateStateMachineResponse {
        val profile = EnvironmentVariableCredentialsProvider.create()
        val awsTags = mapTags(tags)
        val sfnClient = SfnClient.builder()
            .region(Region.of(region))
            .credentialsProvider(profile)
            .build()
        return try {
            val machineRequest = CreateStateMachineRequest.builder()
                .definition(stateMachineDefinition)
                .name(stateMachineName)
                .roleArn(roleArn)
                .tags(awsTags)
                .build()
            sfnClient.createStateMachine(machineRequest)
        } catch (ex: Exception) {
            throw ExternalCloudClientException(
                workflowName = stateMachineName,
                cloudProvider = "AWS",
                message = ex.message,
                throwable = ex
            )
        }
    }

    private fun mapTags(tags: Map<String, String>): Collection<Tag> =
        tags.entries.map {
            Tag.builder()
                .key(it.key)
                .value(it.value)
                .build()
        }
}