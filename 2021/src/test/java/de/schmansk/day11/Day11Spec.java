package de.schmansk.day11;

import de.schmansk.day10.Day10;
import de.schmansk.day9.Day9;
import org.junit.Assert;
import org.junit.Test;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Day11Spec {

    @Test
    public void starOne() throws URISyntaxException {

        //test
//        URL resource = Day11.class.getResource("/day11_input");
        URL resource = Day11.class.getResource("/day11_real_input");

        Day11 clazz = new Day11();

        Path path = Paths.get(resource.toURI());
        long i = clazz.starOne(path);

        System.out.println(i);
        Assert.assertTrue("haha",37==i);

    }

}
