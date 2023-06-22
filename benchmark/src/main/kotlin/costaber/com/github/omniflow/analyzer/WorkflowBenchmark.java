package costaber.com.github.omniflow.analyzer;

import costaber.com.github.omniflow.model.Workflow;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 1, time = 1)
@Measurement(iterations = 1, time = 1)
@State(Scope.Benchmark)
public abstract class WorkflowBenchmark {

    @Param({"1", "2", "5", "10", "50", "100", "1000", "10000", "100000"})
    int stepsNumber = 0;
    protected Workflow workflowWithIndependentSteps;
    protected Workflow workflowUsingVariables;
    protected Workflow workflowWithBinaryConditions;
    protected Workflow workflowWithMultipleDecisions;

    public abstract void benchmarkWorkflowWithIndependentSteps();

    public abstract void benchmarkWorkflowUsingVariables();

    public abstract void benchmarkWorkflowWithBinaryConditions();

    public abstract void benchmarkWorkflowWithMultipleDecisions();
}
