package costaber.com.github.omniflow.util;

import java.util.List;
import java.util.stream.Collectors;

public class ListUtils {

    public static String collectListString(List<String> list) {
        return list.stream()
                .filter(str -> !str.isEmpty())
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
