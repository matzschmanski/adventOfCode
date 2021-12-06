package de.schmansk.day3;

import de.schmansk.FileTools;

import java.nio.file.Path;
import java.util.Arrays;

public class Day3Star1 {

    public int calculateGamma(Path pathToInput) {
        String[] strings = FileTools.readFileLineByLineToArray(pathToInput);
        // get first char of every line
        int[] onesInText = new int[strings[0].length()];
        int[] zeroesInText = new int[strings[1].length()];
        Arrays.fill(onesInText, 0);
        Arrays.fill(zeroesInText, 0);
        for (String string : strings) {
            for (int i = 0; i < string.length(); i++) {
                int currentValue = Character.getNumericValue(string.charAt(i));
                if (currentValue == 0) {
                    zeroesInText[i]++;
                } else {
                    onesInText[i]++;
                }
            }
        }
        StringBuilder gamma = new StringBuilder();
        StringBuilder epsilon = new StringBuilder();
        for (int i = 0; i < onesInText.length; i++) {
            if (onesInText[i] > zeroesInText[i]) {
                gamma.append("1");
                epsilon.append("0");
            } else {
                gamma.append("0");
                epsilon.append("1");
            }
        }
        // check if more 0 or 1
        // append 0 or 1 to array
        //get sum
        //System.out.println(new BigInteger(new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1})); // 1

        //2 array size 12
        // count for every char
        int gammaValue = Integer.parseInt(gamma.toString(), 2);
        int epsilonValue = Integer.parseInt(epsilon.toString(), 2);
        int powerConsumption = gammaValue * epsilonValue;
        return powerConsumption;

    }

    public String[] getOxygenGenCount(String[] strings) {
        String[] whatsMoreWhere = new String[12];

        int[] onesInText = new int[strings[0].length()];
        int[] zeroesInText = new int[strings[1].length()];
        Arrays.fill(onesInText, 0);
        Arrays.fill(zeroesInText, 0);
        for (String string : strings) {
            for (int i = 0; i < string.length(); i++) {
                int currentValue = Character.getNumericValue(string.charAt(i));
                if (currentValue == 0) {
                    zeroesInText[i]++;
                } else {
                    onesInText[i]++;
                }
            }
        }
        for (int i = 0; i < onesInText.length; i++) {
            if (onesInText[i] >= zeroesInText[i]) {
                whatsMoreWhere[i] = "1";
            } else {
                whatsMoreWhere[i] = "0";
            }
        }
        return whatsMoreWhere;
    }

    public int calculateOxygenGenerator(Path pathToInput) {
        final String[] strings = FileTools.readFileLineByLineToArray(pathToInput);
        String[] oxyString = strings;
        for (int i = 0; i < 12; i++) {
            final int j = i;
            if (oxyString.length > 1) {
                String[] whatsMoreWhere = getOxygenGenCount(oxyString);
                oxyString = Arrays.stream(oxyString).filter(s -> s.substring(j, j + 1).equals(whatsMoreWhere[j])).toArray(String[]::new);
            } else {
                break;
            }
        }

        String[] scrubber = strings;
        for (int i = 0; i < 12; i++) {
            final int j = i;
            if (scrubber.length > 1) {
                String[] whatsMoreWhere = getOxygenGenCount(scrubber);
                scrubber = Arrays.stream(scrubber).filter(s -> !s.substring(j, j + 1).equals(whatsMoreWhere[j])).toArray(String[]::new);

            } else {
                break;
            }
        }
        int oxygenGeneratorValue = Integer.parseInt(oxyString[0], 2);
        int scrubberValue=Integer.parseInt(scrubber[0].toString(),2);
        int finalValue = oxygenGeneratorValue*scrubberValue;
        return finalValue;
    }
}
