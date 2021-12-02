package de.schmansk.day1;

import org.junit.Test;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public class Day1Star2Spec {
    @Test
    public void testInput() throws URISyntaxException {

        //test
//        URL resource = de.schmansk.day1.Day1Star2.class.getResource("/input");
        //input
        URL resource = Day1Star2.class.getResource("/day1_real_input");

        Day1Star2 clazz = new Day1Star2();

        int i = clazz.doSomething(Paths.get(resource.toURI()));
        System.out.println(i);


    }
}
