package costaber.com.github.omniflow.analyzer;

import costaber.com.github.omniflow.cloud.provider.amazon.deployer.AmazonCloudDeployer;
import costaber.com.github.omniflow.cloud.provider.amazon.deployer.AmazonDeployContext;
import costaber.com.github.omniflow.generator.WorkflowGenerator;
import costaber.com.github.omniflow.provider.DeployContextProvider;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Setup;

public class BenchmarkAmazonDeployer extends WorkflowBenchmark {

    private AmazonCloudDeployer amazonCloudDeployer;
    private AmazonDeployContext amazonDeployContext;

    @Setup
    public void setup() {
        workflowWithIndependentSteps = WorkflowGenerator.withIndependentSteps(stepsNumber);
        workflowUsingVariables = WorkflowGenerator.usingVariables(stepsNumber);
        workflowWithBinaryConditions = WorkflowGenerator.withBinaryConditions(stepsNumber);
        workflowWithMultipleDecisions = WorkflowGenerator.withMultipleDecisions(stepsNumber);
        amazonCloudDeployer = new AmazonCloudDeployer.Builder().build();
        amazonDeployContext = DeployContextProvider.amazonDeployContext();
    }

    @Benchmark
    @Override
    public void benchmarkWorkflowWithIndependentSteps() {
        amazonCloudDeployer.deploy(workflowWithIndependentSteps, amazonDeployContext);
    }

    @Benchmark
    @Override
    public void benchmarkWorkflowUsingVariables() {
        amazonCloudDeployer.deploy(workflowUsingVariables, amazonDeployContext);
    }

    @Benchmark
    @Override
    public void benchmarkWorkflowWithBinaryConditions() {
        amazonCloudDeployer.deploy(workflowWithBinaryConditions, amazonDeployContext);
    }

    @Benchmark
    @Override
    public void benchmarkWorkflowWithMultipleDecisions() {
        amazonCloudDeployer.deploy(workflowWithMultipleDecisions, amazonDeployContext);
    }
}
