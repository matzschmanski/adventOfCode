package de.schmansk.day15;

import de.schmansk.day14.Day14;
import org.junit.Assert;
import org.junit.Test;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Day15Spec {

    @Test
    public void starOne() throws URISyntaxException {

        //test
        URL resource = Day15.class.getResource("/day15_input");
//        URL resource = Day15.class.getResource("/day15_real_input");

        Day15 clazz = new Day15();

        Path path = Paths.get(resource.toURI());
        long i = clazz.starOne(path);

        System.out.println(i);
        Assert.assertTrue("haha",37==i);

    }

}
