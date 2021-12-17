package de.schmansk.day11;

import de.schmansk.Coordinate;
import de.schmansk.FileTools;
import de.schmansk.day9.Day9;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Day11 {

    public int starOne(Path pathToInput) {
        Integer[][] octopuses = FileTools.readIntoMatrix(pathToInput, "");
        long allFlashes=0L;
        for (int day = 0; day <= 10000; day++) {


            // increase all by 1
            for (int i = 0; i < octopuses.length; i++) {
                for (int j = 0; j < octopuses[i].length; j++) {
                    octopuses[i][j] = octopuses[i][j] + 1;
                }
            }
            //all greater 9 FLASH
            Set<Coordinate> hasToFlash = new HashSet<>();
            for (int i = 0; i < octopuses.length; i++) {
                for (int j = 0; j < octopuses[i].length; j++) {
                    if (octopuses[i][j] > 9) {
                        hasToFlash.add(new Coordinate(i, j));
                    }

                }
            }
            Set<Coordinate> hasFlashed = new HashSet<>();
            if(hasToFlash.size()>0) {
                flash(octopuses, hasToFlash,hasFlashed);
            }
            allFlashes+=hasFlashed.size();
            int currDay = day+1;
            System.out.println("Day" + currDay + " " + hasFlashed.size()+ " flashes (" + allFlashes+ " total)");
            if(hasFlashed.size()== octopuses.length * octopuses[0].length){
                System.out.println("all the bois have flashed");
            }
            for (int i = 0; i < octopuses.length; i++) {
                for (int j = 0; j < octopuses[i].length; j++) {
                    if(octopuses[i][j]>9){
                        octopuses[i][j] =0;
                    }
                }
            }
        }
        return 0;
    }

    private void printMatrix(Integer[][]matrix){
        for (int i = 0; i <matrix.length ; i++) {
            for (int j = 0; j <matrix[i].length ; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println("");
        }
    }

    private Integer[][] flash(Integer[][] octopuses, Set<Coordinate> hasToFlash, Set<Coordinate> hasFlashed) {
            for (Iterator<Coordinate> it = hasToFlash.iterator(); it.hasNext();) {
                Coordinate coordFlash = it.next();
            if(!hasFlashed.contains(coordFlash)) {
                List<Coordinate> adjacentEight = Coordinate.getAdjacentEight(coordFlash, octopuses);
                for (Coordinate toInc : adjacentEight) {
                    octopuses[toInc.getI()][toInc.getJ()]++;
                }
                it.remove();
                hasFlashed.add(coordFlash);
            }
        }
        Set<Coordinate> newFlashes = new HashSet<>();
        for (int i = 0; i < octopuses.length; i++) {
            for (int j = 0; j < octopuses[i].length; j++) {
                if (octopuses[i][j] > 9) {
                    Coordinate e = new Coordinate(i, j);
                    if(!hasFlashed.contains(e)) {
                        newFlashes.add(e);
                    }
                }

            }
        }
        newFlashes.removeAll(hasFlashed);
        if(newFlashes.size()>0) {
            flash(octopuses, newFlashes, hasFlashed);
        }
        return octopuses;
    }


}
