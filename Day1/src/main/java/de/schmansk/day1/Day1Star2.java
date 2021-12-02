package de.schmansk.day1;

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
        List<Integer> numbers = new ArrayList<>();
        int[] allLinesAsInt = new int[0];
        try (Stream<String> lines = Files.lines(inputFilePath, Charset.defaultCharset())) {
            allLinesAsInt = lines.map(Integer::parseInt).mapToInt(x -> x).toArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int[] windows = buildWindows(allLinesAsInt);

        Day1Star1 firstStar = new Day1Star1();
        int count = firstStar.countIt(windows);

        return count;
    }

    public int[] buildWindows(int[] numbers){
    List<Integer> values = new ArrayList<>();
//            i , i+1 ,i+2 == A
//            i+1, i+2, i+3 == B
//            i+2, i+3, i+4 == C
//            i+4, i+5, i+6 == D
//            199  A
//            200  A B
//            208  A B C
//            210    B C D
//            200  E   C D
//            207  E F   D
//            240  E F G
//            269    F G H
//            260      G H
//            263        H

//            199  A
//            200  A  B
//            208  A  B  C
//            210     B  C  D
//            200  A1    C  D
//            207  A1 B1    D
//            240  A1 B1 C1
//            269     B1 C1 D1
//            260        C1 D1
//            263           D1
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
