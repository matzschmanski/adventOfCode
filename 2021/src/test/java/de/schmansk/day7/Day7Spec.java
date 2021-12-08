package de.schmansk.day7;

import de.schmansk.day6.Day6;
import org.junit.Assert;
import org.junit.Test;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Day7Spec {

    @Test
    public void starOne() throws URISyntaxException {

        //test
//        URL resource = Day7.class.getResource("/day7_input");
        URL resource = Day7.class.getResource("/day7_real_input");

        Day7 clazz = new Day7();

        Path path = Paths.get(resource.toURI());
        long i = clazz.starOne(path);

        System.out.println(i);
        Assert.assertTrue("haha",37==i);

    }

    @Test
    public void starTwo() throws URISyntaxException {

        //test
//        URL resource = Day7.class.getResource("/day7_input");
        URL resource = Day7.class.getResource("/day7_real_input");

        Day7 clazz = new Day7();

        Path path = Paths.get(resource.toURI());
        long i = clazz.starTwo(path);

        System.out.println(i);
        Assert.assertTrue("haha",168==i);

    }
}
