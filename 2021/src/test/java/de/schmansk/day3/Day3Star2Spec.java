package de.schmansk.day3;

import org.junit.Test;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Day3Star2Spec {
    @Test
    public void testInput() throws URISyntaxException {

//        URL resource = Day3Star1.class.getResource("/day3_input");
        URL resource = Day3Star1.class.getResource("/day3_real_input");

        Day3Star1 clazz = new Day3Star1();
        Path path = Paths.get(resource.toURI());
        int i = clazz.calculateOxygenGenerator(path);

        System.out.println(i);


    }
}
