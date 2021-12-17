package de.schmansk;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileTools {
    public FileTools() {
    }

    public static String[] readFileLineByLine(Path inputFilePath) {
        Charset charset = Charset.defaultCharset();
        List<String> stringList = null;
        try {
            stringList = Files.readAllLines(inputFilePath, charset);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringList.toArray(new String[]{});
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

    public static String[] readFileLineByLineToArrayStartWithLine(Path inputFilePath, int startLine) {
        String[] lineByLine = readFileLineByLineToArray(inputFilePath);
        String[] copied = Arrays.copyOfRange(lineByLine, startLine, lineByLine.length);
        return copied;


    }

    public static Integer[][] readIntoMatrix(Path inputFilePath, String charToSplit){
        String splitter = charToSplit;
        if("".equals(splitter)){
            splitter="(?<=[0-9])";
        }
        String[] lineByLine= readFileLineByLineToArray(inputFilePath);
        Integer[][] array = new Integer[lineByLine.length][lineByLine[0].split(splitter).length];
        for (int i = 0; i <lineByLine.length ; i++) {
            array[i] = Arrays.stream(lineByLine[i].split(splitter)).map(String::trim).mapToInt(Integer::valueOf).boxed().toArray(Integer[]::new);
        }
        return array;
    }
}
