package de.schmansk.day17;

import de.schmansk.FileTools;

import java.nio.file.Path;
import java.util.*;

public class Day17 {
    // Driver code


    public int starOne(Path pathToInput) {
        String[] strings = FileTools.readFileLineByLineToArray(pathToInput);
        //target area: x=20..30, y=-10..-5
        String[] parts = strings[0].replace("target area: x=","").replace(" y=","").split(",");
        int minX = Integer.parseInt(parts[0].split("\\.\\.")[0]);
        int maxX = Integer.parseInt(parts[0].split("\\.\\.")[1]);
        int minY = Integer.parseInt(parts[1].split("\\.\\.")[0]);
        int maxY = Integer.parseInt(parts[1].split("\\.\\.")[1]);
        Map<Integer,Set<Integer>> values = new HashMap<>();
        for (int velX = -500; velX <= 500; velX++) {
            for (int velY = -500; velY <= 500; velY++) {
                Map<Integer, Integer> integerIntegerMap = shootProbe(velX, velY, 500, minX, maxX, minY, maxY);
                for (var entry : integerIntegerMap.entrySet()) {
                    Set<Integer> valueSet = values.getOrDefault(entry.getKey(), new HashSet<>());
                    valueSet.add(entry.getValue());
                    values.put(entry.getKey(), valueSet);
                }
            }
        }
        int maxKey=Integer.MAX_VALUE;
        for (var entry : values.entrySet()) {
            if(entry.getKey()<maxKey){
                maxKey= entry.getKey();
            }
        }
        int iniC=0;
        for (var entry : values.entrySet()) {
            for(Integer distinct : entry.getValue()){
                iniC++;
            }
        }
        int i = values.get(maxKey).stream().mapToInt(v -> v)
                .max().orElseThrow(NoSuchElementException::new);

        return 0;

    }

    private Map<Integer,Integer> shootProbe(int velX, int velY, int steps, int minX, int maxX, int minY, int maxY){
        int startVelX = velX;
        int startVelY = velY;
        int currentX=0;
        int currentY=0;
        int peakY = Integer.MIN_VALUE;

        Map<Integer,Integer> value = new HashMap<>();
        while(steps>0){
            if(velX>0) {
                currentX += velX;
                velX--;
            } else if(velX<0){
                currentX += velX;
                velX++;
            }


                currentY+=velY;

            velY--;
            steps--;
//            System.out.println("new Pos["+currentX+":"+currentY+"]");
            if(currentY>peakY){
                peakY =currentY;
            }
            if(isInRange(currentX,currentY,minX,maxX,minY,maxY)){
                System.out.println("FOUND with " + startVelX +","+startVelY + " !");
                value.put(startVelX,peakY);
                return value;
            }

        }
        return value;
    }

    private boolean isInRange(int currentX, int currentY, int minX ,int maxX,int minY,int maxY){
        if(currentX>=minX && currentX<=maxX && currentY>=minY && currentY <=maxY){
            return true;
        }
        return false;
    }

}




