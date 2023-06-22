package costaber.com.github.omniflow.analyzer;

import costaber.com.github.omniflow.cloud.provider.amazon.renderer.AmazonRenderingContext;
import costaber.com.github.omniflow.generator.WorkflowGenerator;
import costaber.com.github.omniflow.provider.StrategyDeciderProvider;
import costaber.com.github.omniflow.renderer.IndentedRenderingContext;
import costaber.com.github.omniflow.traversor.DepthFirstNodeVisitorTraversor;
import costaber.com.github.omniflow.visitor.NodeContextVisitor;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Setup;

public class BenchmarkAmazonRenderer extends WorkflowBenchmark {

    private DepthFirstNodeVisitorTraversor traversor;
    private NodeContextVisitor amazonContextVisitor;
    private IndentedRenderingContext amazonRenderingContext;

    @Setup
    public void setup() {
        workflowWithIndependentSteps = WorkflowGenerator.withIndependentSteps(stepsNumber);
        workflowUsingVariables = WorkflowGenerator.usingVariables(stepsNumber);
        workflowWithBinaryConditions = WorkflowGenerator.withBinaryConditions(stepsNumber);
        workflowWithMultipleDecisions = WorkflowGenerator.withMultipleDecisions(stepsNumber);
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
