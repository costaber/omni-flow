package costaber.com.github.omniflow.analyzer;

import costaber.com.github.omniflow.cloud.provider.aws.renderer.AmazonRenderingContext;
import costaber.com.github.omniflow.cloud.provider.google.strategy.*;
import costaber.com.github.omniflow.cloud.provider.aws.strategy.*;
import costaber.com.github.omniflow.factory.DefaultNodeRendererStrategyDecider;
import costaber.com.github.omniflow.factory.NodeRendererStrategyDecider;
import costaber.com.github.omniflow.model.Workflow;
import costaber.com.github.omniflow.renderer.IndentedRenderingContext;
import costaber.com.github.omniflow.traversor.DepthFirstNodeVisitorTraversor;
import costaber.com.github.omniflow.visitor.NodeContextVisitor;
import org.openjdk.jmh.annotations.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(5)
@State(Scope.Benchmark)
public class WorkflowBenchmark {

    @Param({"1", "2", "5", "10", "50", "100", "1000", "10000", "100000"})
    int stepsNumber = 0;
    Workflow workflow;
    DepthFirstNodeVisitorTraversor traversor;
    NodeContextVisitor googleContextVisitor;
    IndentedRenderingContext googleRenderingContext;
    NodeContextVisitor amazonContextVisitor;
    AmazonRenderingContext amazonRenderingContext;

    @Setup(Level.Invocation)
    public void setup() {
        workflow = new Workflow("", "", "", new ArrayList<>(), "");
        traversor = new DepthFirstNodeVisitorTraversor();
        googleContextVisitor = new NodeContextVisitor(createNodeRendererStrategyDecider2());
        amazonContextVisitor = new NodeContextVisitor(createNodeRendererStrategyDecider());
        amazonRenderingContext = new AmazonRenderingContext();
        googleRenderingContext = new IndentedRenderingContext();
    }

    NodeRendererStrategyDecider createNodeRendererStrategyDecider2() {
        return new DefaultNodeRendererStrategyDecider.Builder()
                .addRendererStrategy(new GoogleAssignStrategyFactory())
                .addRendererStrategy(new GoogleCallStrategyFactory())
                .addRendererStrategy(new GoogleConditionStrategyFactory())
                .addRendererStrategy(new GoogleEqualToExpressionStrategyFactory())
                .addRendererStrategy(new GoogleGreaterThanExpressionStrategyFactory())
                .addRendererStrategy(new GoogleGreaterThanOrEqualExpressionStrategyFactory())
                .addRendererStrategy(new GoogleLessThanExpressionStrategyFactory())
                .addRendererStrategy(new GoogleLessThanOrEqualExpressionStrategyFactory())
                .addRendererStrategy(new GoogleNotEqualToExpressionStrategyFactory())
                .addRendererStrategy(new GoogleStepStrategyFactory())
                .addRendererStrategy(new GoogleSwitchStrategyFactory())
                .addRendererStrategy(new GoogleVariableStrategyFactory())
                .addRendererStrategy(new GoogleWorkflowStrategyFactory())
                .build();
    }

    NodeRendererStrategyDecider createNodeRendererStrategyDecider() {
        return new DefaultNodeRendererStrategyDecider.Builder()
                .addRendererStrategy(new AmazonChoiceStrategyFactory())
                .addRendererStrategy(new AmazonConditionStrategyFactory())
                .addRendererStrategy(new AmazonEqualToExpressionStrategyFactory())
                .addRendererStrategy(new AmazonGreaterThanExpressionStrategyFactory())
                .addRendererStrategy(new AmazonGreaterThanOrEqualExpressionStrategyFactory())
                .addRendererStrategy(new AmazonLessThanExpressionStrategyFactory())
                .addRendererStrategy(new AmazonLessThanOrEqualExpressionStrategyFactory())
                .addRendererStrategy(new AmazonPassStrategyFactory())
                .addRendererStrategy(new AmazonStateMachineStrategyFactory())
                .addRendererStrategy(new AmazonStateStrategyFactory())
                .addRendererStrategy(new AmazonTaskStrategyFactory())
                .addRendererStrategy(new AmazonVariableStrategyFactory())
                .build();
    }

    @Benchmark
    public void benchmarkWorkflowRenderingForGoogle() {
        traversor.traverse(googleContextVisitor, workflow, amazonRenderingContext);
    }

    @Benchmark
    public void benchmarkWorkflowRenderingForAmazon() {
        traversor.traverse(amazonContextVisitor, workflow, amazonRenderingContext);
    }

}
