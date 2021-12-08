package de.schmansk.day7;

import de.schmansk.FileTools;

import java.nio.file.Path;
import java.util.Arrays;

public class Day7 {

    public int starOne(Path pathToInput) {
        String[] strings = FileTools.readFileLineByLine(pathToInput);
        int[] crabs = Arrays.stream(strings[0].split(",")).map(Integer::parseInt).mapToInt(x->x).toArray();
        int highest = Arrays.stream(crabs).max().getAsInt();
        int lowest = Arrays.stream(crabs).min().getAsInt();

        int leastFuel =-1;
        for (int i = lowest; i <highest ; i++) {
            int currentFuel=0;
            for (int j = 0; j <crabs.length ; j++) {
                currentFuel+= calculateFuelToReachPosition(crabs[j], i);
            }
            if(leastFuel==-1){
                leastFuel=currentFuel;
            }
            else if(currentFuel<leastFuel){
                leastFuel=currentFuel;
            }
        }
        return leastFuel;
    }

    public int starTwo(Path pathToInput) {
        String[] strings = FileTools.readFileLineByLine(pathToInput);
        int[] crabs = Arrays.stream(strings[0].split(",")).map(Integer::parseInt).mapToInt(x->x).toArray();
        int highest = Arrays.stream(crabs).max().getAsInt();
        int lowest = Arrays.stream(crabs).min().getAsInt();

        int leastFuel =-1;
        for (int i = lowest; i <highest ; i++) {
            int currentFuel=0;
            for (int j = 0; j <crabs.length ; j++) {
                currentFuel+= calculateFuelToReachPositionCrabWay(crabs[j], i);
            }
            if(leastFuel==-1){
                leastFuel=currentFuel;
            }
            else if(currentFuel<leastFuel){
                leastFuel=currentFuel;
            }
        }
        return leastFuel;
    }

    private int calculateFuelToReachPosition(int start, int goal){
        return Math.abs(start-goal);

    }

    private int calculateFuelToReachPositionCrabWay(int start, int goal){
        int fuel=0;
        int steps = 0;
        if(start<goal) {
            steps = goal-start;
        }else{
            steps = start-goal;
        }

        for (int i = 0; i < steps; i++) {
            fuel = fuel + i + 1;
        }
        return fuel;

    }
}
