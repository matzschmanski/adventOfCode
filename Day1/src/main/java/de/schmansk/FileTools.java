package de.schmansk;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class FileTools {
    public FileTools() {
    }

    public static int[] readIntsFileLineByLineToArray(Path inputFilePath) {

        int[] result = new int[0];
        try (Stream<String> lines = Files.lines(inputFilePath, Charset.defaultCharset())) {
            result = lines.map(Integer::parseInt).mapToInt(x -> x).toArray();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static String[] readFileLineByLineToArray(Path inputFilePath) {
        List<String> strings = Collections.emptyList();
        try {
            strings = Files.readAllLines(inputFilePath, Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strings.toArray(new String[0]);
    }
}
