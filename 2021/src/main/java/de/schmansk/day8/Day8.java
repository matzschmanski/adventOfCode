package de.schmansk.day8;

import de.schmansk.FileTools;
import org.h2.store.fs.FileUtils;

import java.io.File;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    private final String REGEX_ONE="^(?=.{2}$)(?=[a-z]).*$";
    private final String REGEX_FOUR="^(?=.{4}$)(?=[a-z]).*$";
    private final String REGEX_SEVEN="^(?=.{3}$)(?=[a-z]).*$";
    private final String REGEX_EIGHT = "^(?=.{7}$)(?=[a-z]).*$";

    private final String REGEX_NINE ="^(?=.{6}$)(?=.*c)(?=.*e)(?=.*f)(?=.*a)(?=.*b)(?=.*d).*$";
    private final String REGEX_SIX="^(?=.{6}$)(?=.*c)(?=.*d)(?=.*f)(?=.*g)(?=.*e)(?=.*b).*$";
    private final String REGEX_FIVE="^(?=.{5}$)(?=.*c)(?=.*d)(?=.*f)(?=.*b)(?=.*e).*$";
    private final String REGEX_THREE="^(?=.{5}$)(?=.*f)(?=.*b)(?=.*c)(?=.*a)(?=.*d).*$";
    private final String REGEX_TWO="^(?=.{5}$)(?=.*g)(?=.*c)(?=.*d)(?=.*f)(?=.*a).*$";
    private final String REGEX_ZERO="^(?=.{6}$)(?=.*c)(?=.*a)(?=.*g)(?=.*e)(?=.*d)(?=.*b).*$";

    private HashMap<Integer,String> getNumberFromStringStarTwo(String word, HashMap<Integer,String> currentValues){
        if(word.matches(REGEX_EIGHT)){
            currentValues.put(8,word);
        }else
            if(word.matches(REGEX_SEVEN)){
            currentValues.put(7,word);
        }else if(word.matches(REGEX_FOUR)){
            currentValues.put(4,word);
        }else if(word.matches(REGEX_ONE)){
            currentValues.put(1,word);
        }else{
            if(null==currentValues.get(-1)){
            currentValues.put(-1,word);
            }else{
                currentValues.put(-1,currentValues.get(-1)+","+word);
            }

        }


        return currentValues;

    }

    private HashMap<Integer,String> addWord(String word, Integer key, HashMap<Integer,String> currentValues){
        currentValues.merge(key, word, (a, b) -> a + "," + b);
        return currentValues;
    }



    public String joinWords(String words){
        return Arrays.stream(words.split(""))
                .distinct()
                .collect(Collectors.joining());
    }

    public String removeWords(String wordToRemoveFrom, String words){
        String joinedWords = joinWords(words);
        String removedWord ="";
        for(String character : wordToRemoveFrom.split("")){
            if(!joinedWords.contains(character)){
                removedWord = removedWord+character;
            }
        }
        return removedWord;
    }

    public static Set<Character> stringToCharacterSet(String s) {
        Set<Character> set = new HashSet<>();
        for (char c : s.toCharArray()) {
            set.add(c);
        }
        return set;
    }

    public static boolean containsAllChars
            (String container, String containee) {
        return stringToCharacterSet(container).containsAll
                (stringToCharacterSet(containee));
    }

//    private HashMap<Integer,String> findNine(String suspectedNine, HashMap<Integer,String> currentValues){
//        String[] split = currentValues.get(-1).split(",");
//        currentValues.put(-1,"");
//        for(String possibleNine : split){
//            if(containsAllChars(possibleNine,suspectedNine) && possibleNine.length()==suspectedNine.length()){
//                currentValues.putIfAbsent(9,possibleNine);
//            }else{
//                if(null==currentValues.get(-1)){
//                    currentValues.put(-1,possibleNine);
//                }else{
//                    currentValues.put(-1,currentValues.get(-1)+","+possibleNine);
//                }
//            }
//        }
//        return currentValues;
//
//    }
private HashMap<Integer,String> findNine(HashMap<Integer,String> currentValues){
    // 9 ist the only signal with length 6 containing all of 4
    List<String> strings = Arrays.stream(currentValues.get(-1).split(",")).map(String::trim).filter(s -> s.length() == 6).toList();
    String differer = removeWords(currentValues.get(4),currentValues.get(1));
    for (String string : strings){
        if(containsAllChars(string,currentValues.get(4))){
            currentValues.put(9,string);
        }
    }
    String[] split = currentValues.get(-1).split(",");
    currentValues.remove(-1);
    for (String s : split) {
        if (!s.equals(currentValues.get(9)) && s.length()>0) {
            currentValues = addWord(s, -1, currentValues);
        }
    }

    return currentValues;
}
private HashMap<Integer,String> findSix(HashMap<Integer,String> currentValues){
        //six has length 6 AND contains the differer we used for 5 but does NOT contain all of 1
    List<String> strings = Arrays.stream(currentValues.get(-1).split(",")).map(String::trim).filter(s -> s.length() == 6).toList();
    String differer = removeWords(currentValues.get(4),currentValues.get(1));
    for (String string : strings){
        if(containsAllChars(string,differer)&& !containsAllChars(string,currentValues.get(1))){
            currentValues.put(6,string);
        }
    }
    String[] split = currentValues.get(-1).split(",");
    currentValues.remove(-1);
    for (String s : split) {
        if (!s.equals(currentValues.get(6)) && s.length()>0) {
            currentValues = addWord(s, -1, currentValues);
        }
    }

    return currentValues;
}
private HashMap<Integer,String> findTwo(HashMap<Integer,String> currentValues){
    List<String> strings = Arrays.stream(currentValues.get(-1).split(",")).map(String::trim).filter(s -> s.length() == 5).toList();
    for (String string : strings){
        if(string.length()==5){
            currentValues.put(2,string);
        }
    }
    String[] split = currentValues.get(-1).split(",");
    currentValues.remove(-1);
    for (String s : split) {
        if (!s.equals(currentValues.get(2)) && s.length()>0) {
            currentValues = addWord(s, -1, currentValues);
        }
    }

    return currentValues;
}

private HashMap<Integer,String> findFive(HashMap<Integer,String> currentValues){
//        4-1 is indicating whats missing to distinguis the 5 from 2 and 3
    List<String> strings = Arrays.stream(currentValues.get(-1).split(",")).map(String::trim).filter(s -> s.length() == 5).toList();
    String differer = removeWords(currentValues.get(4),currentValues.get(1));
    for (String string : strings){
        if(containsAllChars(string,differer)){
            currentValues.put(5,string);
        }
    }
    String[] split = currentValues.get(-1).split(",");
    currentValues.remove(-1);
    for (String s : split) {
        if (!s.equals(currentValues.get(5)) && s.length()>0) {
            currentValues = addWord(s, -1, currentValues);
        }
    }

    return currentValues;
}
    private HashMap<Integer,String> findThree(HashMap<Integer,String> currentValues){
        // 3 has length 5
        // as do 2 and 5
        // BUT 3 contains ALL 1
        List<String> strings = Arrays.stream(currentValues.get(-1).split(",")).map(String::trim).filter(s -> s.length() == 5).toList();
        for (String string : strings){
            if(containsAllChars(string,currentValues.get(1))){
                currentValues.put(3,string);
            }
        }
        String[] split = currentValues.get(-1).split(",");
        currentValues.remove(-1);
        for (String s : split) {
            if (!s.equals(currentValues.get(3))&& s.length()>0) {
                currentValues = addWord(s, -1, currentValues);
            }
        }

        return currentValues;
    }

    private HashMap<Integer,String> findNine(HashMap<Integer,String> currentValues, String suspectedNines){
//        9-4 = länge 2
            //0-4 = länge 3
        String s = currentValues.get(-1);
        String[] split = s.split(",");
        String[] split1 = suspectedNines.split("");
        for (int i = 0; i < split.length; i++) {
            String wordToRemoveFrom = split[i];

            if(wordToRemoveFrom.contains(split1[0]) || wordToRemoveFrom.contains(split1[1])) {
                String s1 = removeWords(wordToRemoveFrom, currentValues.get(4));
                System.out.println(s1);
            }

        }
        return currentValues;
    }

    private String sortString(String chaos){
        return chaos.chars()
                .sorted()
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }


    private Integer getNumberFromStringStarOne(String line){
        String []words = line.split("\\|")[0].split("\\s");
        words = Arrays.stream(words).map(String::trim).filter(Predicate.not(String::isEmpty)).toArray(String[]::new);
        String number = "0";
        HashMap<Integer, String> numbers = new HashMap<>();


        for (int i = 0; i < words.length; i++) {
            numbers = getNumberFromStringStarTwo(words[i],numbers);
        }
        numbers = findThree(numbers);
        numbers = findFive(numbers);
        //now the last with length 5 is number TWO
        numbers = findTwo(numbers);
        numbers = findSix(numbers);
        numbers = findNine(numbers);

        numbers.putIfAbsent(0,numbers.get(-1));
        numbers.remove(-1);
        //sort numbers
        HashMap<String, Integer> switchedValues = new HashMap<>();
        for (var entry : numbers.entrySet()) {
            switchedValues.put(sortString(entry.getValue()), entry.getKey());
        }
        //now we sum the right side
        String[] signalsToSumUp = line.split("\\|")[1].trim().split("\\s");
        String finalNumber="";

        for(String signal : signalsToSumUp){
            finalNumber = finalNumber + switchedValues.get(sortString(signal));
        }

        return Integer.parseInt(finalNumber);
    }

//    private List<String> allKnownSignals(char[] one, char[] four, char[] seven, char[] eight){
//        List<String> resultList = new ArrayList<>(one.length + four.length+ seven.length+ seven.eight);
//        Collections.addAll()
//    }


}
