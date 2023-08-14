package costaber.com.github.omniflow.provider;

import costaber.com.github.omniflow.util.FileReader;

import java.io.IOException;

public class OfficialWorkflowSamplesProvider {

    public static String google() throws IOException {
        FileReader fileReader = new FileReader();
        return fileReader.readFileFromResources("samples/googleWorkflowSample.yml");
    }

    public static String amazon() throws IOException {
        FileReader fileReader = new FileReader();
        return fileReader.readFileFromResources("samples/amazonWorkflowSample.json");
    }
}
