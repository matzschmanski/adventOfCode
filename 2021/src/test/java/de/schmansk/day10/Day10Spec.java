package de.schmansk.day10;

import de.schmansk.day9.Day9;
import org.junit.Assert;
import org.junit.Test;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Day10Spec {

    @Test
    public void starOne() throws URISyntaxException {

        //test
//        URL resource = Day9.class.getResource("/day10_input");
        URL resource = Day9.class.getResource("/day10_real_input");

        Day10 clazz = new Day10();

        Path path = Paths.get(resource.toURI());
        long i = clazz.starOne(path);

        System.out.println(i);
        Assert.assertTrue("haha",37==i);

    }

}
