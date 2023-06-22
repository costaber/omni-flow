package costaber.com.github.omniflow;

import costaber.com.github.omniflow.analyzer.WorkflowBenchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class BenchmarkApplication {

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(WorkflowBenchmark.class.getSimpleName())
                // .includeWarmup(...) <-- this may include other benchmarks into warmup
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}
