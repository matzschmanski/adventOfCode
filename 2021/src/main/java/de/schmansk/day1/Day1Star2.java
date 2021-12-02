package de.schmansk.day1;

import de.schmansk.FileTools;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Day1Star2 {
    public int doSomething(Path inputFilePath) {
        //read file
        int[] allLinesAsInt = FileTools.readIntsFileLineByLineToArray(inputFilePath);
        int[] windows = buildWindows(allLinesAsInt);

        Day1Star1 firstStar = new Day1Star1();
        int count = firstStar.countIt(windows);

        return count;
    }

    public int[] buildWindows(int[] numbers){
    List<Integer> values = new ArrayList<>();
        int handledLines =0;
        for (int i = 0; i < numbers.length-4; i=i+4) {
            int a = numbers[i]+numbers[i+1]+numbers[i+2];
            int b = numbers[i+1]+numbers[i+2]+numbers[i+3];
            int c = numbers[i+2]+numbers[i+3]+numbers[i+4];
            int d = numbers[i+3]+numbers[i+4]+numbers[i+5];
            System.out.println(a);
            System.out.println(b);
            System.out.println(c);
            System.out.println(d);
            values.add(a);
            values.add(b);
            values.add(c);
            values.add(d);

            handledLines=i;
        }
        // der rest
        handledLines +=4;

        int lastA = numbers[handledLines]+numbers[handledLines+1]+numbers[handledLines+2];
        System.out.println(lastA);
        int lastB = numbers[handledLines+1]+numbers[handledLines+2]+numbers[handledLines+3];
        System.out.println(lastB);
        int lastC = numbers[handledLines+2]+numbers[handledLines+3];
        System.out.println(lastC);
        int lastD = numbers[handledLines+3];
        System.out.println(lastD);
        values.add(lastA);
        values.add(lastB);
        values.add(lastC);
        values.add(lastD);
        int[] array = values.stream().mapToInt(i->i).toArray();
        return array;
    }
}
