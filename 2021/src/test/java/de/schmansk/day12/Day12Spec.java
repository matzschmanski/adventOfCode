package de.schmansk.day12;

import de.schmansk.day11.Day11;
import org.junit.Assert;
import org.junit.Test;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Day12Spec {

    @Test
    public void starOne() throws URISyntaxException {

        //test
//        URL resource = Day11.class.getResource("/day12_input");
        URL resource = Day12.class.getResource("/day12_real_input");

        Day12 clazz = new Day12();

        Path path = Paths.get(resource.toURI());
        long i = clazz.starOne(path);

        System.out.println(i);
        Assert.assertTrue("haha",37==i);

    }

}
