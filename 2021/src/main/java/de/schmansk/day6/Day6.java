package de.schmansk.day6;

import de.schmansk.FileTools;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Day6 {


    public long produceLanterFish(Path pathToInput, int days) {
        String[] strings = FileTools.readFileLineByLine(pathToInput);
        Integer[] fish = Arrays.stream(strings[0].split(",")).map(Integer::valueOf).mapToInt(x -> x).boxed().toArray(Integer[]::new);
        for (int i = 1; i <= days; i++) {
            //let 1 day pass
            fish = Arrays.stream(fish).map(value -> value - 1).toArray(Integer[]::new);

            //set all 0 to 6
            List<Integer> temporaryFishes  = Arrays.stream(fish).map(operand -> {
                if (operand < 0) {
                    operand = 6;
                }
                return operand;
            }).collect(Collectors.toList());
            //check 0, for every 0 add one 8 and set 0 to 6
            long amountOfZeroes = Arrays.stream(fish).filter(value -> value == -1).count();
            for (int j = 0; j < amountOfZeroes; j++) {
                temporaryFishes.add(8);
            }
            fish = temporaryFishes.toArray(Integer[]::new);
            System.out.println();
        }
        return Arrays.stream(fish).count();
    }


    public long produceMoreLanterFish(Path pathToInput, int days) {


        String[] strings = FileTools.readFileLineByLine(pathToInput);
        Integer[] fish = Arrays.stream(strings[0].split(",")).map(Integer::valueOf).mapToInt(x -> x).boxed().toArray(Integer[]::new);
        HashMap<Integer, Long> fishMap= new HashMap<>();
        for (int i = 0; i < 9; i++) {
            final int fishAge =i;
            long amoutOfI = Arrays.stream(fish).filter(value -> value == fishAge).count();
            fishMap.put(i,amoutOfI);
        }


        for (int i = 1; i <= days; i++) {
            //let 1 day pass
            Long temp=null;
            for (int j = 8; j >=-1; j--) {
                if(j>=0) {
                    Long amoutFish = fishMap.get(j);
                    if (temp != null) {
                        amoutFish = temp;
                    }
                    temp = fishMap.get(j - 1);
                    fishMap.put(j - 1, amoutFish);
                }
                if(j==-1){
                    fishMap.put(8,fishMap.get(j));
                    fishMap.put(6,fishMap.get(6)+fishMap.get(j));
                    fishMap.put(j, 0L);
                }


            }
        }
        long sum =0;
        for (var entry : fishMap.entrySet()) {
            sum+=entry.getValue();
        }
        return sum;
    }

}
