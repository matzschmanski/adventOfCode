package de.schmansk;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
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
}
