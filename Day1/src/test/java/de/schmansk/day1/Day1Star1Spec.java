package de.schmansk.day1;

import org.junit.Test;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public class Day1Star1Spec {
    @Test
    public void testInput() throws URISyntaxException {

        //test
//        URL resource = de.schmansk.day1.Day1Star1.class.getResource("/input");
        //input
        URL resource = Day1Star1.class.getResource("/day1_real_input");

        Day1Star1 clazz = new Day1Star1();
        int[] lines = clazz.buildList(Paths.get(resource.toURI()));
        int i = clazz.countIt(lines);
        System.out.println(i);


    }
}
