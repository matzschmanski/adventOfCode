package de.schmansk.day9;

import de.schmansk.FileTools;

import java.nio.file.Path;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Day9 {

    public int starOne(Path pathToInput) {
        Integer[][] matrix = FileTools.readIntoMatrix(pathToInput,"");
        int sum =0;
        for (int i = 0; i <matrix.length ; i++) {
            for (int j = 0; j <matrix[i].length ; j++) {
                //edge cases:
                // 0,0 end,end
                // left side, right side, top,bottom
                int currentValue = matrix[i][j];
                boolean lowPoint = isLowPoint(matrix, i, j);
                if(lowPoint) {
                    System.out.println(currentValue);
                    sum+=currentValue+1;
                }
            }
        }
        return sum;
    }

    private boolean isLowPoint(Integer[][] matrix, int currentI, int currentJ){
        int currentValue = matrix[currentI][currentJ];

        int rightSide = currentValue+1;
        int leftSide = currentValue+1;
        int topSide =currentValue+1;
        int bottomSide =currentValue+1;
        if(matrix[currentI].length>currentJ+1){
            rightSide = matrix[currentI][currentJ+1];
        }
        if(currentJ-1>=0){
            leftSide = matrix[currentI][currentJ-1];
        }
        if(currentI-1 >=0){
            topSide = matrix[currentI-1][currentJ];
        }
        if(matrix.length>currentI+1){
            bottomSide = matrix[currentI+1][currentJ];
        }
        if(bottomSide >currentValue && topSide > currentValue && rightSide > currentValue && leftSide > currentValue){
            return true;
        }
        return false;
    }


    public int starTwo(Path pathToInput) {
        Integer[][] matrix = FileTools.readIntoMatrix(pathToInput,"");
        int sum =0;
        Set<Coordinate> lowPoints = new HashSet<>();
        for (int i = 0; i <matrix.length ; i++) {
            for (int j = 0; j <matrix[i].length ; j++) {
                //edge cases:
                // 0,0 end,end
                // left side, right side, top,bottom
                int currentValue = matrix[i][j];
                boolean lowPoint = isLowPoint(matrix, i, j);
                if(lowPoint) {
                    lowPoints.add(new Coordinate(i+","+j));
                    sum+=currentValue+1;
                }
            }
        }
        Set<Coordinate> visited = new HashSet<>();
        List<Integer> counter = new ArrayList<>();
        for (Coordinate lowPoint: lowPoints){
            counter.add(walkDaBasin(lowPoint, matrix,visited));
        }

        Collections.sort(counter);
        Collections.reverse(counter);
        int sumXValues = counter.stream().mapToInt(Integer::intValue).limit(3).reduce(1, (a, b) -> a * b);
        return sumXValues;
    }

    private boolean[][] convertMatrix(Integer[][] input){
        boolean[][] converted = new boolean[input.length][input[0].length];
        return converted;
    }

    class Coordinate {
        int i;
        int j;
        public Coordinate(String startPoint){
            this(Integer.parseInt(startPoint.split(",")[0].trim()),Integer.parseInt(startPoint.split(",")[1].trim()));
        }

        public Coordinate(int i, int j){
            this.i =i;
            this.j =j;
        }

        public int getI() {
            return i;
        }

        public int getJ() {
            return j;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coordinate that = (Coordinate) o;
            return i == that.i && j == that.j;
        }

        @Override
        public int hashCode() {
            int hashCode = 0;
            hashCode = (hashCode * 397) ^ this.i;
            hashCode = (hashCode * 397) ^ this.j;
            return hashCode;
        }
        @Override
        public String toString() {
            return "Coordinate{" +
                    "i=" + i +
                    ", j=" + j +
                    '}';
        }
    }

    public Integer walkDaBasin(Coordinate startPoint, Integer[][] input, Set<Coordinate> visited){
        Integer[][] matrix = input;
        int count =visited.size();
//        Set<Coordinate> setOfPoints = new HashSet<>();
//        setOfPoints.add(startPoint);
        getAdjacentPoints(startPoint, matrix, visited);
//        while (!setOfPoints.isEmpty()){
//            for (Iterator<Coordinate> it = setOfPoints.iterator(); it.hasNext();) {
//                Coordinate currentPoint = it.next();
//                Set<Coordinate> copy = new HashSet<>(setOfPoints);
//                setOfPoints.addAll();
//                visited.addAll(copy);
////                setOfPoints.removeAll(copy);
//            }
//
//        }

        //get points of the set

        count = visited.size()-count;
        return count;
    }

    private Set<Coordinate> getAdjacentPoints(Coordinate currentPoint,Integer[][] matrix, Set<Coordinate> visited){
        Set<Coordinate> adjacentPoints = new HashSet<>();
                Coordinate rightSide = new Coordinate(currentPoint.getI(), currentPoint.j+1);
                Coordinate leftSide =new Coordinate(currentPoint.getI(), currentPoint.getJ()-1);
                Coordinate topSide = new Coordinate(currentPoint.getI()-1, currentPoint.getJ());
                Coordinate bottomSide = new Coordinate(currentPoint.getI()+1, currentPoint.getJ());
        adjacentPoints.add(rightSide);
        adjacentPoints.add(leftSide);
        adjacentPoints.add(topSide);
        adjacentPoints.add(bottomSide);

        for (Iterator<Coordinate> it = adjacentPoints.iterator(); it.hasNext();) {
            Coordinate element = it.next();
            if(element.getI()>=matrix.length || element.getJ()>= matrix[0].length || element.getI()<0 || element.getJ()<0){
                it.remove();
            }else if(matrix[element.getI()][element.getJ()]==9){
                it.remove();
            }else if(visited.contains(element)){
                it.remove();
            }
        }
        visited.add(currentPoint);
        for(Coordinate newCurr : adjacentPoints){
            getAdjacentPoints(newCurr,matrix,visited);
        }
        return adjacentPoints;
    }







}
