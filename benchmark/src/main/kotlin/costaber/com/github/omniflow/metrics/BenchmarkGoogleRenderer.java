package costaber.com.github.omniflow.metrics;

import costaber.com.github.omniflow.cloud.provider.google.renderer.GoogleTermContext;
import costaber.com.github.omniflow.generator.WorkflowGenerator;
import costaber.com.github.omniflow.provider.StrategyDeciderProvider;
import costaber.com.github.omniflow.renderer.IndentedRenderingContext;
import costaber.com.github.omniflow.traversor.DepthFirstNodeVisitorTraversor;
import costaber.com.github.omniflow.visitor.NodeContextVisitor;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Setup;

public class BenchmarkGoogleRenderer extends BenchmarkWorkflowRenderer {

    private DepthFirstNodeVisitorTraversor traversor;
    private NodeContextVisitor googleContextVisitor;
    private IndentedRenderingContext googleRenderingContext;

    @Setup
    public void setup() {
        workflowWithIndependentSteps = WorkflowGenerator.withIndependentSteps(numberOfSteps);
        workflowUsingVariables = WorkflowGenerator.usingVariables(numberOfSteps);
        workflowWithBinaryConditions = WorkflowGenerator.withBinaryConditions(numberOfSteps);
        workflowWithMultipleDecisions = WorkflowGenerator.withMultipleDecisions(numberOfSteps);
        traversor = new DepthFirstNodeVisitorTraversor();
        googleContextVisitor = new NodeContextVisitor(StrategyDeciderProvider.googleNodeRendererStrategyDecider());
        googleRenderingContext = new IndentedRenderingContext(0, new StringBuilder(), new GoogleTermContext());
    }

    @Benchmark
    @Override
    public void benchmarkWorkflowWithIndependentSteps() {
        traversor.traverse(googleContextVisitor, workflowWithIndependentSteps, googleRenderingContext);
    }

    @Benchmark
    @Override
    public void benchmarkWorkflowUsingVariables() {
        traversor.traverse(googleContextVisitor, workflowUsingVariables, googleRenderingContext);
    }

    @Benchmark
    @Override
    public void benchmarkWorkflowWithBinaryConditions() {
        traversor.traverse(googleContextVisitor, workflowWithBinaryConditions, googleRenderingContext);
    }

    @Benchmark
    @Override
    public void benchmarkWorkflowWithMultipleDecisions() {
        traversor.traverse(googleContextVisitor, workflowWithMultipleDecisions, googleRenderingContext);
    }
}