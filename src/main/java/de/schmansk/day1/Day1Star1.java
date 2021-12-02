package de.schmansk.day1;

import de.schmansk.FileTools;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class Day1Star1 {

    private final FileTools fileTools = new FileTools();

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
}
