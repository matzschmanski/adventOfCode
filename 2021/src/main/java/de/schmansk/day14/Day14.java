package de.schmansk.day14;

import de.schmansk.FileTools;

import java.nio.file.Path;
import java.util.*;

public class Day14 {


    public int starOne(Path pathToInput) {
        String[] puzzleInput = FileTools.readFileLineByLine(pathToInput);
        String polymerTemplate = puzzleInput[0].trim();
        Map<String, String> pairs = new HashMap<>();
        for (int i = 2; i < puzzleInput.length; i++) {
            String[] split = puzzleInput[i].split(" -> ");
            pairs.put(split[0].trim(), split[1].trim());

        }

        Map<String, Long> letterCounter = polymerStuffTwo(polymerTemplate, pairs, 40);

        String maxLetter ="";
        Long maxCount=0L;
        String minLetter ="";
        Long minCount=0L;
        for (var entry : letterCounter.entrySet()) {
            if(entry.getValue()>maxCount){
                maxCount = entry.getValue();
                maxLetter = entry.getKey();
            }
            if(minCount ==0 || entry.getValue()<minCount){
                minCount = entry.getValue();
                minLetter = entry.getKey();
            }
        }
        Long returnValue = maxCount-minCount;
        System.out.println(returnValue);




        return 0;
    }
    private Map<String, Long>  polymerStuffTwo(String polymerTemplate, Map<String,String> polymers, int steps) {

        Map<String, Long> currentPairs = new HashMap<>();
        String newPolymerTemplate = "";
        //first template into map
        for (int i = 0; i < polymerTemplate.length(); i++) {
            if (i + 2 <= polymerTemplate.length()) {
                String currentPair = polymerTemplate.substring(i, i + 2);
                currentPairs.put(currentPair, currentPairs.getOrDefault(currentPair, 0L) + 1);
            } else if (i + 1 <= polymerTemplate.length()) {
                String currentFillter = polymerTemplate.substring(i);
                currentPairs.put(currentFillter, currentPairs.getOrDefault(currentFillter, 0L) + 1);
            }
        }
        //build new pairs out of existing pairs
        for (int i = 1; i <= steps; i++) {


            Map<String, Long> newPairs = new HashMap<>();
            for (var pair : currentPairs.entrySet()) {
                String polymer = polymers.get(pair.getKey());
                if (null == polymer) {
                    polymer = "";
                }

                //build new pairs
                String newPair1 = pair.getKey().charAt(0) + polymer;
                String newPair2 = polymer + pair.getKey().substring(1);


        if (!"".equals(newPair1) && !"B".equals(newPair1)) {
                    newPairs.put(newPair1, newPairs.getOrDefault(newPair1, 0L) + pair.getValue());
                } if("B".equals(newPair1)){
                    newPairs.put("B",1L);
                }
                if (!"".equals(newPair2) && !"B".equals(newPair2)) {
                    newPairs.put(newPair2, newPairs.getOrDefault(newPair2, 0L) + pair.getValue());
                }if("B".equals(newPair2)){
                    newPairs.put("B",1L);
                }

            }
            System.out.println("Step: "+i);
            currentPairs = newPairs;
        }

        Map<String, Long> letterCounts = new HashMap<>();
        for (var pair : currentPairs.entrySet()) {
            String letter1 = pair.getKey().substring(0,1);
            Long letter1Value = pair.getValue();

            if(!"".equals(letter1)) {
                letterCounts.put(letter1, letterCounts.getOrDefault(letter1, 0L) + letter1Value);
            }
//            if(!"".equals(letter2)) {
//                letterCounts.put(letter2, letterCounts.getOrDefault(letter1, 0L) + letter2Value);
//            }


        }
        return letterCounts;
    }

//    private String polymerStuff(String polymerTemplate, Map<String,String> pairs){
//        List<String> newLetters = new ArrayList<>();
//        String newPolymerTemplate ="";
//        for (int i = 0; i <polymerTemplate.length() ; i++) {
//            if(i+2<=polymerTemplate.length()) {
//
//                String currentPair = polymerTemplate.substring(i, i + 2);
//                newLetters.add(pairs.get(currentPair));
//                newPolymerTemplate = newPolymerTemplate+currentPair.charAt(0) + pairs.get(currentPair);
//            }
//        }
//        newPolymerTemplate = newPolymerTemplate+polymerTemplate.charAt(polymerTemplate.length()-1);
//        return newPolymerTemplate;
//
//
//    }



}
