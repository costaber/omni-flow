package costaber.com.github.omniflow.metrics;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 0, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 1, timeUnit = TimeUnit.MILLISECONDS)
@Fork(1)
@State(Scope.Benchmark)
public abstract class BenchmarkWorkflowDeployment {

    protected String generatedWorkflow;
    protected String exampleWorkflow;

    public abstract void benchmarkGeneratedWorkflowDeployment();

    public abstract void benchmarkExampleWorkflowDeployment();
}
