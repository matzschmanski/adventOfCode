package de.schmansk.day2;

import de.schmansk.FileTools;

import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicInteger;

public class Day2Star1 {
    // i already know i will regret doing it like this
    public int firstStar(Path pathToInput){
        String[] strings = FileTools.readFileLineByLineToArray(pathToInput);
        AtomicInteger y = new AtomicInteger(0);
        AtomicInteger x = new AtomicInteger(0);
        for (String string : strings) {
            String[] currentValue = string.split("\\s+");
            switch (currentValue[0]) {
                case "down" -> y.set(y.get() + Integer.parseInt(currentValue[1]));
                case "up" -> y.set(y.get() - Integer.parseInt(currentValue[1]));
                case "forward" -> x.set(x.get() + Integer.parseInt(currentValue[1]));
                default -> System.out.println(currentValue[0]);
            }
        }
        return y.get() * x.get();
    }

    public int secondStar(Path pathToInput){
        String[] strings = FileTools.readFileLineByLineToArray(pathToInput);
        AtomicInteger y = new AtomicInteger(0);
        AtomicInteger x = new AtomicInteger(0);
        AtomicInteger aim = new AtomicInteger(0);
        for (String string : strings) {
            String[] currentValue = string.split("\\s+");
            switch (currentValue[0]) {
                case "down":
                    aim.set(aim.get() + Integer.parseInt(currentValue[1]));
                    break;
                case "up":
                    aim.set(aim.get() - Integer.parseInt(currentValue[1]));
                    break;
                case "forward":
                    x.set(x.get() + Integer.parseInt(currentValue[1]));
                    y.set(y.get()+ (aim.get()*Integer.parseInt(currentValue[1])));
                    break;
                default:
                    System.out.println(currentValue[0]);
                    break;
            }
        }
        return y.get() * x.get();
    }
}
