package de.schmansk.day4;

import de.schmansk.FileTools;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

public class Day4Star2 {

    public int doDaBingo(Path pathToInput) {
        String[] strings = FileTools.readFileLineByLineToArray(pathToInput);
        String[] bingoSheetsAsStrings = Arrays.copyOfRange(strings, 1, strings.length);
        String[] numbers = strings[0].split(",");

        Integer[] bingoNumbersInOrder =
                Arrays.stream(numbers).map(String::trim).mapToInt(Integer::valueOf).boxed().collect(Collectors.toList()).toArray(new Integer[0]);

        List<int[][]> listOfSheets = new ArrayList<>();

        for (int i = 0; i < bingoSheetsAsStrings.length; i += 6) {
            //throw away first line
            int[][] sheet = new int[5][5];

            for (int j = 0; j < 5; j++) {
                int[] bingoLine = generateBingoLine(bingoSheetsAsStrings[i + 1 + j]);
                sheet[j] = bingoLine;
            }
            listOfSheets.add(sheet);


        }
        // mirror sheets
//        List<Integer[][]> listOfMirroredSheets = listOfSheets.stream().map(this::mirrorSheet).collect(Collectors.toList());
        // go threw all numbers remove all numbers from all arrays, find empty array :S
        //check if array contains all numbers, if yes the sheet is a winner, for every number
        doIt(listOfSheets, bingoNumbersInOrder);
        return 0;
    }

    private int[][] integerToInt(Integer[][] sheet) {
        int[][] converted = new int[5][5];
        for (int i = 0; i < sheet.length; i++) {
            for (int j = 0; j < sheet[i].length; j++) {
                converted[i][j] = sheet[i][j];
            }
        }
        return converted;

    }

    private Integer[][] intToInteger(int[][] sheet) {
        Integer[][] converted = new Integer[5][5];
        for (int i = 0; i < sheet.length; i++) {
            for (int j = 0; j < sheet[i].length; j++) {
                converted[i][j] = sheet[i][j];
            }
        }
        return converted;

    }

    private int doIt(List<int[][]> sheetsHorizontal, Integer[] bingoNumbersInOrder) {

        Integer[][] lastBingoSheet = null;
        int lastNumber = -1;
        int lastNumberIndex = -1;


        for (int i = 0; i < bingoNumbersInOrder.length; i++) {
            List<Integer> integers = Arrays.stream(Arrays.copyOfRange(bingoNumbersInOrder, 0, i)).toList();
            ListIterator<int[][]> iter = sheetsHorizontal.listIterator();
            while (iter.hasNext()) {
                int[][] next = iter.next();
                Integer[][] convertedInt = intToInteger(next);
                if (findBingo(convertedInt, integers)) {
                    lastBingoSheet = convertedInt;
                    lastNumber = integers.get(integers.size() - 1);
                    lastNumberIndex = integers.size();
                    iter.remove();

                }
            }
        }


        //remove existing from bingo sheet
        for (int i = 0; i < lastNumberIndex; i++) {
            Integer current = bingoNumbersInOrder[i];
            for (int j = 0; j < lastBingoSheet.length; j++) {
                for (int k = 0; k < lastBingoSheet[j].length; k++) {
                    if (lastBingoSheet[j][k].equals(current)) {
                        lastBingoSheet[j][k] = 0;
                    }
                }

            }
        }
        // get sum
        int sum = 0;
        for (int i = 0; i < lastBingoSheet.length; i++) {
            sum += Arrays.stream(lastBingoSheet[i]).toList().stream()
                    .reduce(0, Integer::sum);

        }
        return bingoNumbersInOrder[lastNumberIndex-1] * sum;



//
//        int lastNumberIndex = -1;
//        for (int i = 0; i < bingoNumbersInOrder.length; i++) {
//            List<Integer> integers = Arrays.stream(Arrays.copyOfRange(bingoNumbersInOrder, 0, i)).toList();
//            boolean bingo = false;
//
//
//            // for all sheets
//            for (int l = 0; l < sheetsHorizontal.size(); l++) {
//                //check all lines
//                for (int j = 0; j < 5; j++) {
//                    Integer[][] currentColumn = sheetsHorizontal.get(l);
//                    Integer[][] currentRow = sheetsVertical.get(l);
//                    for (int k = 0; k < currentColumn.length; k++) {
//                        bingo = integers.containsAll(Arrays.asList(currentColumn[k]));
//                        if (!bingo) {
//                            bingo = integers.containsAll(Arrays.asList(currentRow[k]));
//                        }
//                        if (bingo) {
//                            sheetsHorizontal.remove(l);
//                            break;
//                        }
//                    }
//
//                    if (bingo) {
//                        lastNumberIndex = i - 1;
//                        System.out.println("bingo after" + lastNumberIndex + "numbers");
//                    }
//
//                }
//
//                if (bingo) {
//                    lastBingoSheet = sheetsHorizontal.get(l);
//                    bingo = false;
//
//                }
//
//            }
//        }
//        //remove existing from bingo sheet
//        for (int i = 0; i <= lastNumberIndex; i++) {
//            Integer current = bingoNumbersInOrder[i];
//            for (int j = 0; j < lastBingoSheet.length; j++) {
//                for (int k = 0; k < lastBingoSheet[j].length; k++) {
//                    if (lastBingoSheet[j][k].equals(current)) {
//                        lastBingoSheet[j][k] = 0;
//                    }
//                }
//
//            }
//        }
//        // get sum
//        int sum = 0;
//        for (int i = 0; i < lastBingoSheet.length; i++) {
//            sum += Arrays.stream(lastBingoSheet[i]).toList().stream()
//                    .reduce(0, Integer::sum);
//
//        }
//        return bingoNumbersInOrder[lastNumberIndex] * sum;
//        return 0;
    }

    private List<Integer[]> generateRowAndColumn(Integer[][] currentSheet, int indicator) {
        List<Integer[]> rowAndColumn = new ArrayList<>();
        Integer[] row = new Integer[5];
        Integer[] column = new Integer[5];
        for (int i = 0; i < 5; i++) {
            column[i] = currentSheet[i][indicator];
            row[i] = currentSheet[indicator][i];
        }
        rowAndColumn.add(row);
        rowAndColumn.add(column);
        return rowAndColumn;

    }

    private boolean findBingo(Integer[][] sheet, List<Integer> numbers) {
        for (int i = 0; i < sheet.length; i++) {
            List<Integer[]> rowAndColumn = generateRowAndColumn(sheet, i);
            for (Integer[] rowOrColumn : rowAndColumn) {
                boolean b = numbers.containsAll(Arrays.asList(rowOrColumn));
                if (b) {
                    return true;
                }
            }
        }
        return false;
    }


    private Integer[][] mirrorSheet(Integer[][] sheet) {
        Integer[][] mirroredSheet = new Integer[5][5];
        for (int i = 0; i < sheet.length; i++) {
            for (int j = 0; j < sheet[i].length; j++) {
                mirroredSheet[j][i] = sheet[i][j];
            }
        }
        return mirroredSheet;
    }

    private int[] generateBingoLine(String line) {
        line = line.replaceAll("\\\s+", " ");
        line = line.trim();
        String[] s = line.split(" ");
//        Integer[] bingoLine = Arrays.stream(s).map(String::trim).mapToInt(Integer::valueOf).boxed().collect(Collectors.toList()).toArray(new Integer[0]);
        int[] bingoLine = Arrays.stream(s).map(String::trim).mapToInt(Integer::parseInt).toArray();
        return bingoLine;

    }
}
