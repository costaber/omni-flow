package costaber.com.github.omniflow;

import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class BenchmarkApplication {

    public static void main(String[] args) throws RunnerException {
        if (args.length == 0) {
            throw new IllegalArgumentException("Argument corresponding to the file path is missing");
        }

        String fileName = args[0];
        if (!fileName.endsWith(".csv")) {
            throw new IllegalArgumentException("The file format must be CSV");
        }

        Options opt = new OptionsBuilder()
                .shouldDoGC(true)
                .resultFormat(ResultFormatType.CSV)
                .result(fileName)
                .build();

        new Runner(opt).run();
    }
}