package costaber.com.github.omniflow.service

import costaber.com.github.omniflow.cloud.provider.aws.service.AmazonStateMachineService
import costaber.com.github.omniflow.util.getResourceContent
import org.junit.Test

internal class AmazonStateMachineServiceTest {

    @Test
    fun `aws deploy test happy path`() {
        val stateMachineDefinition = this::class.java.classLoader
            .getResourceContent("workflows/AwsStateMachine.json")
        val asd = AmazonStateMachineService()
        asd.createStateMachine(
            roleArn = "arn:aws:iam::610299836666:role/aws-service-role/trustedadvisor.amazonaws.com/AWSServiceRoleForTrustedAdvisor",
            region = "us-east-1",
            tags = mapOf(
                "environment" to "testing",
                "app" to "omni-flow"
            ),
            stateMachineName = "state_machine_test_deployment_1",
            stateMachineDefinition = stateMachineDefinition
        )
    }

}