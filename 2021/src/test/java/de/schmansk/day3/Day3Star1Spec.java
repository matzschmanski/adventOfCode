package de.schmansk.day3;

import de.schmansk.day2.Day2Star1;
import org.junit.Assert;
import org.junit.Test;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Day3Star1Spec {
    @Test
    public void testInput() throws URISyntaxException {

        //test
        URL resource = Day3Star1.class.getResource("/day3_input");
        //input
//        URL resource = Day3Star1.class.getResource("/day3_real_input");

        Day3Star1 clazz = new Day3Star1();
//        URL resource = clazz.getClass().getResource("/day3_real_input");
        Path path = Paths.get(resource.toURI());
        int i = clazz.calculateGamma(path);

        System.out.println(i);
        Assert.assertTrue("haha",198==i);

    }
}
