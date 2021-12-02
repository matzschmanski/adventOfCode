package de.schmansk.day1;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day1Star1 {

    public int countIt(int[] lines){
        //read file
        AtomicInteger amountOfIncrease=new AtomicInteger(0);
        AtomicInteger lastNumber = new AtomicInteger(-1);
        Arrays.stream(lines).forEach(currentLine ->{

                if(lastNumber.get() < currentLine){
                    if(lastNumber.get() >=0) {
                        amountOfIncrease.getAndIncrement();
                    }else{
                        lastNumber.set(currentLine);
                    }

                }
                lastNumber.set(currentLine);}
            );
        return amountOfIncrease.get();
    }
    public int[] buildList(Path inputFilePath) {

        int[] result = new int[0];
        try (Stream<String> lines = Files.lines(inputFilePath, Charset.defaultCharset())) {
            result = lines.map(Integer::parseInt).mapToInt(x -> x).toArray();

        }catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
