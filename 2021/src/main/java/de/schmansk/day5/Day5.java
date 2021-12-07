package de.schmansk.day5;

import de.schmansk.FileTools;

import java.nio.file.Path;

public class Day5 {

    public int countDemHydrothermalVentsBwonsandi(Path pathToInput) {
        int[][] base = new int[1000][1000];
        String[] strings = FileTools.readFileLineByLine(pathToInput);
        for (int i = 0; i < strings.length; i++) {
            int[][] newPoints = handleLine(strings[i]);
            if (newPoints != null) {
                base = addPointToBase(base, newPoints);
            }
        }
        int riskPoints = countPointBiggerThan(base, 2);
        return riskPoints;


    }

    private int countPointBiggerThan(int[][] base, int riskPoint) {
        int count = 0;
        for (int i = 0; i < base.length; i++) {
            for (int j = 0; j < base[i].length; j++) {
                if (base[i][j] >= riskPoint) {
                    count++;
                }
            }

        }
        return count;
    }

    private int[][] addPointToBase(int[][] base, int[][] newPoints) {
        for (int i = 0; i < newPoints.length; i++) {
            for (int j = 0; j < newPoints[i].length; j++) {
                base[i][j] = base[i][j] + newPoints[i][j];
            }
        }
        return base;
    }

    private int[][] handleLine(String line) {
        System.out.println(line);
        String[] split = line.split(" -> ");
//        start = split[0] end = split[1]
//        start = 0,0 end = 3,3
        String[] startXAndY = split[0].split(",");
        String[] endXAndY = split[1].split(",");
        int[] startPoint = new int[]{Integer.parseInt(startXAndY[0]), Integer.parseInt(startXAndY[1])};
        int[] endPoint = new int[]{Integer.parseInt(endXAndY[0]), Integer.parseInt(endXAndY[1])};


        boolean xAxisChanged = startPoint[0] != endPoint[0];
        boolean yAxisChanged = startPoint[1] != endPoint[1];
        //both true --> diagonal
        int[][] points = null;


        if (xAxisChanged && !yAxisChanged) {
            //order start and end
            if (startPoint[0] > endPoint[0]) {
                int[] temp = new int[2];
                temp = startPoint;
                startPoint = endPoint;
                endPoint = temp;

            }

            int difference = generateDifferenceBetweenPoints(startPoint[0], endPoint[0]);
            points = new int[endPoint[1] + 1][endPoint[0] + 1];
            for (int i = startPoint[0]; i <= endPoint[0]; i++) {
                points[startPoint[1]][i] = 1;
            }
        } else if (yAxisChanged && !xAxisChanged) {
//            handle y
            //order start and end
            if (startPoint[1] > endPoint[1]) {
                int[] temp = new int[2];
                temp = startPoint;
                startPoint = endPoint;
                endPoint = temp;

            }
            int difference = generateDifferenceBetweenPoints(startPoint[1], endPoint[1]);
            points = new int[endPoint[1] + 1][startPoint[0] + 1];
            for (int i = startPoint[1]; i <= endPoint[1]; i++) {
                points[i][startPoint[0]] = 1;
            }
        } else {
            int steigungX = endPoint[0] - startPoint[0];
            int steigungY = endPoint[1] - startPoint[1];
            int[] valuesX = new int[Math.abs(steigungX)+1];
            if (steigungX < 0) {
                int a=0;
                for (int i = startPoint[0]; i >=endPoint[0] ; i--) {
                    valuesX[a]=i;
                    a++;
                }
            }else{
                int a=0;
                for (int i = startPoint[0]; i <=endPoint[0] ; i++) {
                    valuesX[a]=i;
                    a++;
                }
            }

            int[] valuesY = new int[Math.abs(steigungY)+1];
            if(steigungY<0) {
                int a=0;
                for (int i = startPoint[1]; i>= endPoint[1] ; i--) {
                    valuesY[a]=i;
                    a++;
                }
            }else{
                int a=0;
                for (int i = startPoint[1]; i<= endPoint[1] ; i++) {
                    valuesY[a]=i;
                    a++;
                }
            }

            //points
            int biggerX = Math.max(startPoint[0], endPoint[0]);
            int biggerY = Math.max(startPoint[1], endPoint[1]);
            int biggest = Math.max(biggerX,biggerY);
            points= new int[biggest+1][biggest+1];
            for (int i = 0; i < valuesY.length; i++) {
                points[valuesY[i]][valuesX[i]]=1;
            }
            System.out.println(points);

        }

        return points;
    }

    //bound to change :D
    private int generateDifferenceBetweenPoints(int start, int end) {
        return start - end < 0 ? (start - end) * -1 : start - end;
    }
}
