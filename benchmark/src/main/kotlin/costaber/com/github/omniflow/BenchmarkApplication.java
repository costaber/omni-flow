package costaber.com.github.omniflow;

import costaber.com.github.omniflow.analyzer.BenchmarkAmazonRenderer;
import costaber.com.github.omniflow.analyzer.BenchmarkGoogleRenderer;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class BenchmarkApplication {

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(BenchmarkGoogleRenderer.class.getSimpleName())
                .include(BenchmarkAmazonRenderer.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }
}
