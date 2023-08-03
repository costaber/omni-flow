package costaber.com.github.omniflow.metrics;

import costaber.com.github.omniflow.cloud.provider.amazon.renderer.AmazonRenderingContext;
import costaber.com.github.omniflow.generator.WorkflowGenerator;
import costaber.com.github.omniflow.provider.StrategyDeciderProvider;
import costaber.com.github.omniflow.renderer.IndentedRenderingContext;
import costaber.com.github.omniflow.traversor.DepthFirstNodeVisitorTraversor;
import costaber.com.github.omniflow.visitor.NodeContextVisitor;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Setup;

public class BenchmarkAmazonRenderer extends BenchmarkWorkflowRenderer {

    private DepthFirstNodeVisitorTraversor traversor;
    private NodeContextVisitor amazonContextVisitor;
    private IndentedRenderingContext amazonRenderingContext;

    @Setup
    public void setup() {
        workflowWithIndependentSteps = WorkflowGenerator.withIndependentSteps(numberOfSteps);
        workflowUsingVariables = WorkflowGenerator.usingVariables(numberOfSteps);
        workflowWithBinaryConditions = WorkflowGenerator.withBinaryConditions(numberOfSteps);
        workflowWithMultipleDecisions = WorkflowGenerator.withMultipleDecisions(numberOfSteps);
        traversor = new DepthFirstNodeVisitorTraversor();
        amazonContextVisitor = new NodeContextVisitor(StrategyDeciderProvider.amazonNodeRendererStrategyDecider());
        amazonRenderingContext = new AmazonRenderingContext();
    }

    @Benchmark
    @Override
    public void benchmarkWorkflowWithIndependentSteps() {
        traversor.traverse(amazonContextVisitor, workflowWithIndependentSteps, amazonRenderingContext);
    }

    @Benchmark
    @Override
    public void benchmarkWorkflowUsingVariables() {
        traversor.traverse(amazonContextVisitor, workflowUsingVariables, amazonRenderingContext);
    }

    @Benchmark
    @Override
    public void benchmarkWorkflowWithBinaryConditions() {
        traversor.traverse(amazonContextVisitor, workflowWithBinaryConditions, amazonRenderingContext);
    }

    @Benchmark
    @Override
    public void benchmarkWorkflowWithMultipleDecisions() {
        traversor.traverse(amazonContextVisitor, workflowWithMultipleDecisions, amazonRenderingContext);
    }
}