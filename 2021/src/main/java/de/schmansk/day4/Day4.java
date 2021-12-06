package de.schmansk.day4;

import de.schmansk.FileTools;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day4 {

    public int doDaBingo(Path pathToInput){
        String[] strings = FileTools.readFileLineByLineToArray(pathToInput);
        String[] bingoSheetsAsStrings = Arrays.copyOfRange(strings, 1, strings.length);
        String[] numbers = strings[0].split(",");

        Integer[] bingoNumbersInOrder =
                Arrays.stream(numbers).map(String::trim).mapToInt(Integer::valueOf).boxed().collect(Collectors.toList()).toArray(new Integer[0]);

        List<Integer[][]> listOfSheets = new ArrayList<>();

        for (int i = 0; i <bingoSheetsAsStrings.length ; i+=6) {
            //throw away first line
            Integer[][] sheet = new Integer[5][5];

            for (int j = 0; j < 5; j++) {
                Integer[] bingoLine = generateBingoLine(bingoSheetsAsStrings[i+1+j]);
                sheet[j] =bingoLine;
            }
            listOfSheets.add(sheet);


        }
        // mirror sheets
        List<Integer[][]> listOfMirroredSheets = listOfSheets.stream().map(this::mirrorSheet).collect(Collectors.toList());
        // go threw all numbers remove all numbers from all arrays, find empty array :S
        //check if array contains all numbers, if yes the sheet is a winner, for every number
        doIt(listOfSheets,listOfMirroredSheets,bingoNumbersInOrder);
        return 0;
    }

    private int doIt(List<Integer[][]> sheetsHorizontal, List<Integer[][]> sheetsVertical, Integer[] bingoNumbersInOrder){

        Integer[][] bingoSheet = null;
        boolean bingo = false;
        int lastNumberIndex = -1;
        for (int i = 0; i < bingoNumbersInOrder.length; i++) {
            List<Integer> integers = Arrays.stream(Arrays.copyOfRange(bingoNumbersInOrder, 0, i)).toList();

            // for all sheets
            for (Integer[][] sheet : sheetsHorizontal){
                //check all lines
                for (int j = 0; j <5 ; j++) {
                    List<Integer> currentRowOrColumn = Arrays.asList(sheet[j]);
                    bingo = integers.containsAll(currentRowOrColumn);
                    if(bingo) {
                        lastNumberIndex = i-1;
                        System.out.println("bingo after" + lastNumberIndex + "numbers");
                        break;
                    }

                }

                if(bingo) {
                    bingoSheet = sheet;
                    break;
                }

            }
            if(bingo) {
                break;
            }
        }
        //remove existing from bingo sheet
        for (int i = 0; i <= lastNumberIndex; i++) {
            Integer current = bingoNumbersInOrder[i];
            for (int j = 0; j < bingoSheet.length; j++) {
                for (int k = 0; k < bingoSheet[j].length; k++) {
                    if(bingoSheet[j][k].equals(current)){
                        bingoSheet[j][k]=0;
                    }
                }

            }
        }
        // get sum
        int sum =0;
        for (int i = 0; i < bingoSheet.length; i++) {
            sum+=Arrays.stream(bingoSheet[i]).toList().stream()
                    .reduce(0, Integer::sum);

        }
       return bingoNumbersInOrder[lastNumberIndex]*sum;
    }



    private Integer[][] mirrorSheet(Integer[][] sheet){
        Integer[][] mirroredSheet = new Integer[5][5];
        for (int i = 0; i < sheet.length; i++) {
            for (int j = 0; j < sheet[i].length; j++) {
                mirroredSheet[j][i]=sheet[i][j];
            }
        }
        return mirroredSheet;
    }

    private Integer[] generateBingoLine(String line){
        line = line.replaceAll("\\\s+", " ");
        line = line.trim();
        String[] s = line.split(" ");
        Integer[] bingoLine = Arrays.stream(s).map(String::trim).mapToInt(Integer::valueOf).boxed().collect(Collectors.toList()).toArray(new Integer[0]);
        return bingoLine;

    }
}
