package costaber.com.github.omniflow.analyzer;

import costaber.com.github.omniflow.cloud.provider.google.deployer.GoogleCloudDeployer;
import costaber.com.github.omniflow.cloud.provider.google.deployer.GoogleDeployContext;
import costaber.com.github.omniflow.generator.WorkflowGenerator;
import costaber.com.github.omniflow.provider.DeployContextProvider;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Setup;

public class BenchmarkGoogleDeployer extends WorkflowBenchmark {

    private GoogleCloudDeployer googleCloudDeployer;
    private GoogleDeployContext googleDeployContext;

    @Setup
    public void setup() {
        workflowWithIndependentSteps = WorkflowGenerator.withIndependentSteps(stepsNumber);
        workflowUsingVariables = WorkflowGenerator.usingVariables(stepsNumber);
        workflowWithBinaryConditions = WorkflowGenerator.withBinaryConditions(stepsNumber);
        workflowWithMultipleDecisions = WorkflowGenerator.withMultipleDecisions(stepsNumber);
        googleCloudDeployer = new GoogleCloudDeployer.Builder().build();
        googleDeployContext = DeployContextProvider.googleDeployContext();
    }

    @Benchmark
    @Override
    public void benchmarkWorkflowWithIndependentSteps() {
        googleCloudDeployer.deploy(workflowWithIndependentSteps, googleDeployContext);
    }

    @Benchmark
    @Override
    public void benchmarkWorkflowUsingVariables() {
        googleCloudDeployer.deploy(workflowUsingVariables, googleDeployContext);
    }

    @Benchmark
    @Override
    public void benchmarkWorkflowWithBinaryConditions() {
        googleCloudDeployer.deploy(workflowWithBinaryConditions, googleDeployContext);
    }

    @Benchmark
    @Override
    public void benchmarkWorkflowWithMultipleDecisions() {
        googleCloudDeployer.deploy(workflowWithMultipleDecisions, googleDeployContext);
    }
}
