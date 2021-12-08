package de.schmansk.day8;

import de.schmansk.FileTools;
import org.h2.store.fs.FileUtils;

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day8 {

    public int starOne(Path pathToInput) {
        String[] strings = FileTools.readFileLineByLine(pathToInput);
        HashMap<Integer, Integer> values = new HashMap<>();
        values.put(1, 0);
        values.put(4,0);
        values.put(7,0);
        values.put(8,0);
        Integer countAll=0;
        for (String string : strings) {
            Integer numberFromString = getNumberFromStringStarOne(string);
//            values.put(numberFromString, values.get(numberFromString) + 1);
            countAll = countAll + numberFromString;

        }
        return countAll;
    }
    private final String REGEX_NINE ="^(?=.{6}$)(?=.*c)(?=.*e)(?=.*f)(?=.*a)(?=.*b)(?=.*d).*$";
    private final String REGEX_EIGHT = "^(?=.{7}$)(?=.*a)(?=.*c)(?=.*e)(?=.*d)(?=.*g)(?=.*f)(?=.*b).*$";
    private final String REGEX_SEVEN="^(?=.{3}$)(?=.*d)(?=.*a)(?=.*b).*$";
    private final String REGEX_SIX="^(?=.{6}$)(?=.*c)(?=.*d)(?=.*f)(?=.*g)(?=.*e)(?=.*b).*$";
    private final String REGEX_FIVE="^(?=.{5}$)(?=.*c)(?=.*d)(?=.*f)(?=.*b)(?=.*e).*$";
    private final String REGEX_FOUR="^(?=.{4}$)(?=.*e)(?=.*a)(?=.*f)(?=.*b).*$";
    private final String REGEX_THREE="^(?=.{5}$)(?=.*f)(?=.*b)(?=.*c)(?=.*a)(?=.*d).*$";
    private final String REGEX_TWO="^(?=.{5}$)(?=.*g)(?=.*c)(?=.*d)(?=.*f)(?=.*a).*$";
    private final String REGEX_ONE="^(?=.{2}$)(?=.*a)(?=.*b).*$";
    private final String REGEX_ZERO="^(?=.{6}$)(?=.*c)(?=.*a)(?=.*g)(?=.*e)(?=.*d)(?=.*b).*$";

    private Integer getNumberFromStringStarTwo(String word){
        if(word.matches(REGEX_NINE)){
            return 9;
        }else if(word.matches(REGEX_EIGHT)){
            return 8;
        }else if(word.matches(REGEX_SEVEN)){
            return 7;
        }else if(word.matches(REGEX_SIX)){
            return 6;
        }else if(word.matches(REGEX_FIVE)){
            return 5;
        }else if(word.matches(REGEX_FOUR)){
            return 4;
        }else if(word.matches(REGEX_THREE)){
            return 3;
        }else if(word.matches(REGEX_TWO)){
            return 2;
        }else if(word.matches(REGEX_ONE)){
            return 1;
        }else if(word.matches(REGEX_ZERO)){
            return 0;
        }else{
            System.err.println("WTF?!");
            return -999999999;
        }

    }

    private Integer getNumberFromStringStarOne(String line){
        String []words = line.split("\\|")[1].split("\\s");
        String number = "";
        Arrays.stream(words).map(String::trim).filter(Predicate.not(String::isEmpty)).map(this::getNumberFromStringStarTwo).map(Object::toString).collect(Collectors.joining(","));
        return Integer.valueOf(number);
    }


}
