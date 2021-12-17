package de.schmansk.day10;

import de.schmansk.FileTools;

import java.nio.file.Path;
import java.util.*;

public class Day10 {

    public Long starOne(Path pathToInput) {
        String[] lines = FileTools.readFileLineByLine(pathToInput);
        int sum =0;
        List<String> incompleteLines = new ArrayList<>();
        for(String line : lines){
            int val = handleLine(line);
            if(val==0){
                incompleteLines.add(line);
            }
        }
        List<Long> lineValues = new ArrayList<>();
        for(String line : incompleteLines){
            lineValues.add(handleIncompleteLine(line));
        }
        Collections.sort(lineValues);
        Long value = lineValues.get(lineValues.size()/2);
        return value;
    }

    private int getCharValue(String characterValued){
        int value=0;
        switch (characterValued){
            case "a":
                value=1;
                break;
            default:
                break;
        }
        return value;
    }



    private String getChunkCloser(String chunkStarter){
//        ()[]{}<>
        String chunkCloser = switch (chunkStarter) {
            case "(" -> ")";
            case "[" -> "]";
            case "{" -> "}";
            case "<" -> ">";
//            default -> throw new RuntimeException("unknown chunk starter:\"" +chunkStarter+"\"");
            default -> "";
        };
        return chunkCloser;

    }
    private List<String> openers =null;
    private List<String> getOpeners(){
        if(openers==null){
            openers = new ArrayList<>();
            openers.add("(");
            openers.add("[");
            openers.add("{");
            openers.add("<");
        }
        return openers;
    }
    private List<String> closers =null;
    private List<String> getClosers(){
        if(closers==null){
            closers = new ArrayList<>();
            closers.add(")");
            closers.add("]");
            closers.add("}");
            closers.add(">");
        }
        return closers;
    }

    private boolean isOpener(String chara){
        return getOpeners().contains(chara);
    }
    private boolean isCloser(String chara){
        return getClosers().contains(chara);
    }

    private Long handleIncompleteLine(String line){
        String[] everyChar = line.split("(?<=[\\S])");
        String currentOpener="";
        Long lineCount =0L;
        List<String> openers = new ArrayList<>();
        for (int i = 0; i < everyChar.length; i++) {
            currentOpener = everyChar[i];

            if(!isOpener(everyChar[i])){
                String lastOpener = openers.get(openers.size() - 1);
                String expectedCloser = getChunkCloser(lastOpener);
                if(!everyChar[i].equals(getChunkCloser(lastOpener))){
                    throw new RuntimeException("this was not planned");
                }else{
                    openers.remove(openers.size()-1);
                }
            }else {
                openers.add(currentOpener);
            }
        }
        Collections.reverse(openers);
        for(String chara: openers){
        lineCount = (lineCount * 5) + completerScore(getChunkCloser(chara));
        }
        return lineCount;
    }

    private int handleLine(String line){
        String[] everyChar = line.split("(?<=[\\S])");
        String currentOpener="";

        List<String> openers = new ArrayList<>();
        for (int i = 0; i < everyChar.length; i++) {
            currentOpener = everyChar[i];

            if(!isOpener(everyChar[i])){
                String lastOpener = openers.get(openers.size() - 1);
                String expectedCloser = getChunkCloser(lastOpener);
                if(!everyChar[i].equals(getChunkCloser(lastOpener))){
                    return penalty(everyChar[i]);
                }else{
                    openers.remove(openers.size()-1);
                }
            }else {
                openers.add(currentOpener);
            }
        }
        return 0;
    }

    private int completerScore(String chara){
        int completerScore = switch (chara) {
            case ")" -> 1;
            case "]" -> 2;
            case "}" -> 3;
            case ">" -> 4;
            default -> throw new RuntimeException("unknown closer for completerScore:\"" +chara+"\"");
//            default -> 0;
        };
        return completerScore;
    }

    private int penalty(String chara){
        int penalty = switch (chara) {
            case ")" -> 3;
            case "]" -> 57;
            case "}" -> 1197;
            case ">" -> 25137;
            default -> throw new RuntimeException("unknown closer for penalty:\"" +chara+"\"");
//            default -> 0;
        };
        return penalty;
    }







}
