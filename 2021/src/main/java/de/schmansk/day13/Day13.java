package de.schmansk.day13;

import de.schmansk.FileTools;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day13 {

    private static final String ON ="x";
    private static final String OFF =" ";
    public int starOne(Path pathToInput) {
        String[] puzzleInput = FileTools.readFileLineByLine(pathToInput);
        //find line with only line break
        List<String> inputPaper = new ArrayList<>();
        List<String> inputFolding = new ArrayList<>();
        int maxX=0;
        int maxY=0;
        boolean found = false;
        for (String s : puzzleInput) {
            if (!found) {
                if (!s.equals("")) {
                    String[] split = s.trim().split(",");
                    if(Integer.parseInt(split[0])>maxX){
                        maxX =Integer.parseInt(split[0]);
                    }
                    if(Integer.parseInt(split[1])>maxY){
                        maxY =Integer.parseInt(split[1]);
                    }
                    inputPaper.add(s);
                } else {
                    found = true;
                }
            } else {
                inputFolding.add(s);
            }
        }
        String[][] paperSheet = new String[maxY+1][maxX+1];
        for (int i = 0; i <paperSheet.length; i++) {
            Arrays.fill(paperSheet[i], OFF);
        }

        for (String s : inputPaper) {
            String[] split = s.trim().split(",");
            int x = Integer.parseInt(split[0].trim());
            int y = Integer.parseInt(split[1].trim());

            paperSheet[y][x] =ON;
        }
        for(String s : inputFolding){
            String[] split = s.split("=");
            String foldDirection = split[0].trim().substring(split[0].length()-1);
            String foldPosition = split[1].trim();
//            System.out.println("folding at [" + foldDirection + "] at position [" + foldPosition+"]");
            if("y".equals(foldDirection)){
                paperSheet=foldY(paperSheet,Integer.parseInt(foldPosition));
            }else{
                paperSheet=foldX(paperSheet,Integer.parseInt(foldPosition));
            }
//            System.out.println("length is now: "+paperSheet.length);

        }



        //count dots
        printPaper(paperSheet);
        int dots = countDots(paperSheet);
        return dots;
    }

    private int countDots(String [][] paperSheet){
        int count =0;
        for (int i = 0; i < paperSheet.length; i++) {
            for (int j = 0; j < paperSheet[i].length; j++) {
                if(".".equals(paperSheet[i][j])){
                    count++;
                }
            }
        }
        return count;
    }

    private String[][] foldY(String[][] paperSheet, int yPosition){
        for (int y = yPosition+1; y < paperSheet.length; y++) {
            //get current array and "copy" to position 0
            for (int x = 0; x < paperSheet[y].length; x++) {
                int yToCopyTo = paperSheet.length-y-1;
                if(OFF.equals(paperSheet[yToCopyTo][x]) && ON.equals(paperSheet[y][x])) {
                    paperSheet[yToCopyTo][x] = paperSheet[y][x];
                }else if(OFF.equals(paperSheet[yToCopyTo][x]) && OFF.equals(paperSheet[y][x])){
                    // do nothing
                }else if(ON.equals(paperSheet[yToCopyTo][x]) && OFF.equals(paperSheet[y][x])){
                    // do nothing
                }
            }
        }
        String[][] newSheet = new String[paperSheet.length/2][paperSheet[0].length];
        for (int i = 0; i < newSheet.length; i++) {
            for (int j = 0; j <newSheet[i].length ; j++) {
                newSheet[i][j]=paperSheet[i][j];
            }
        }
        return newSheet;
    }

    private String[][] foldX(String[][] paperSheet, int xPosition){
        for (int y = 0; y < paperSheet.length; y++) {
            //get current array and "copy" to position 0
            for (int x = xPosition+1; x < paperSheet[y].length; x++) {
                // y to copy to == y - paperSheet.length / 2
                int xToCopyTo = paperSheet[y].length-x-1;
                if(OFF.equals(paperSheet[y][xToCopyTo]) && ON.equals(paperSheet[y][x])) {
                    paperSheet[y][xToCopyTo] = paperSheet[y][x];
                }else if(OFF.equals(paperSheet[y][xToCopyTo]) && OFF.equals(paperSheet[y][x])){
                    paperSheet[y][xToCopyTo] = paperSheet[y][x];
                }else if(ON.equals(paperSheet[y][xToCopyTo]) && OFF.equals(paperSheet[y][x])){
                    // do nothing
                }
            }
        }
        String[][] newSheet = new String[paperSheet.length][paperSheet[0].length/2];
        for (int i = 0; i < newSheet.length; i++) {
            for (int j = 0; j <newSheet[i].length ; j++) {
                newSheet[i][j]=paperSheet[i][j];
            }
        }
        return newSheet;
    }

    private void printPaper(String[][] paperSheet){
        for (int i = 0; i < paperSheet.length; i++) {
            for (int j = 0; j < paperSheet[i].length; j++) {
                System.out.print(paperSheet[i][j]+" ");
            }
            System.out.println("");

        }
    }


}
