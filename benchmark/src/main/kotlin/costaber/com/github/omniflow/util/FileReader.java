package costaber.com.github.omniflow.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReader {

    public String readFileFromResources(String fileName) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resourceFile = classLoader.getResource(fileName);
        if (resourceFile == null) {
            throw new FileNotFoundException(fileName + " not found!");
        }
        String filePath = resourceFile.getPath();
        byte[] fileContent = Files.readAllBytes(Paths.get(filePath));
        return new String(fileContent, StandardCharsets.UTF_8);
    }
}
