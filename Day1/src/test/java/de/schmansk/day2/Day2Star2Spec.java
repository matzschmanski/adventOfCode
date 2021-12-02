package de.schmansk.day2;

import org.junit.Test;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Day2Star2Spec {
    @Test
    public void testInput() throws URISyntaxException {

        //test
//        URL resource = Day2Star1.class.getResource("/day2_input");
        //input
        URL resource = Day2Star1.class.getResource("/day2_real_input");

        Day2Star1 clazz = new Day2Star1();
        Path path = Paths.get(resource.toURI());
        int i = clazz.secondStar(path);

        System.out.println(i);


    }
}
