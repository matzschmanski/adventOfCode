package de.schmansk;

import de.schmansk.day9.Day9;

import java.util.*;

public class Coordinate {
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

    public static List<Coordinate> getAdjacentFour(Coordinate coordinate, Integer[][] matrix){
        List<Coordinate> adjacentPoints = new ArrayList<>();
        Coordinate rightSide = new Coordinate(coordinate.getI(), coordinate.getJ()+1);
        Coordinate leftSide =new Coordinate(coordinate.getI(), coordinate.getJ()-1);
        Coordinate topSide = new Coordinate(coordinate.getI()-1, coordinate.getJ());
        Coordinate bottomSide = new Coordinate(coordinate.getI()+1, coordinate.getJ());

        adjacentPoints.add(rightSide);
        adjacentPoints.add(leftSide);
        adjacentPoints.add(topSide);
        adjacentPoints.add(bottomSide);
        adjacentPoints.removeIf(element -> element.getI() >= matrix.length || element.getJ() >= matrix[0].length || element.getI() < 0 || element.getJ() < 0);
        return adjacentPoints;

    }
    public static List<Coordinate> getAdjacentEight(Coordinate coordinate, Integer[][] matrix){
        List<Coordinate> adjacentPoints = new ArrayList<>();
        adjacentPoints.addAll(getAdjacentFour(coordinate,matrix));
        Coordinate topRightSide = new Coordinate(coordinate.getI()-1, coordinate.getJ()+1);
        Coordinate topLeftSide =new Coordinate(coordinate.getI()-1, coordinate.getJ()-1);
        Coordinate bottomLeftSide = new Coordinate(coordinate.getI()+1, coordinate.getJ()-1);
        Coordinate bottomRightSide = new Coordinate(coordinate.getI()+1, coordinate.getJ()+1);

        adjacentPoints.add(topRightSide);
        adjacentPoints.add(topLeftSide);
        adjacentPoints.add(bottomLeftSide);
        adjacentPoints.add(bottomRightSide);


        adjacentPoints.removeIf(element -> element.getI() >= matrix.length || element.getJ() >= matrix[0].length || element.getI() < 0 || element.getJ() < 0);

        return adjacentPoints;

    }

}
