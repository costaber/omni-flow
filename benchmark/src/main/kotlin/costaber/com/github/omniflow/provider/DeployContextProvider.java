package costaber.com.github.omniflow.provider;

import costaber.com.github.omniflow.cloud.provider.amazon.deployer.AmazonDeployContext;
import costaber.com.github.omniflow.cloud.provider.google.deployer.GoogleDeployContext;

import java.util.HashMap;
import java.util.Map;

public class DeployContextProvider {

    public static GoogleDeployContext googleDeployContext() {
        Map<String, String> labels = new HashMap<>();
        labels.put("environment", "testing");
        labels.put("app", "omni-flow");
        return new GoogleDeployContext(
                "workflow-test-380423",
                "us-east1",
                "projects/workflow-test-380423/serviceAccounts/" +
                        "service-account-test@workflow-test-380423.iam.gserviceaccount.com",
                "calculator_workflow",
                "Calculator Workflow for testing",
                labels
        );
    }

    public static AmazonDeployContext amazonDeployContext() {
        Map<String, String> tags = new HashMap<>();
        tags.put("environment", "testing");
        tags.put("app", "omni-flow");
        return new AmazonDeployContext(
                "arn:aws:iam::610299836666:role/aws-service-role/trustedadvisor.amazonaws.com/" +
                        "AWSServiceRoleForTrustedAdvisor",
                "us-east-1",
                tags,
                "state_machine_calculator"
        );
    }
}
