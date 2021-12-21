package de.schmansk.day17;

import de.schmansk.day15.Day15;
import org.junit.Assert;
import org.junit.Test;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Day17Spec {

    @Test
    public void starOne() throws URISyntaxException {

        //test
//        URL resource = Day17.class.getResource("/day17_input");
        URL resource = Day17.class.getResource("/day17_real_input");

        Day17 clazz = new Day17();

        Path path = Paths.get(resource.toURI());
        long i = clazz.starOne(path);

        System.out.println(i);
        Assert.assertTrue("haha",37==i);

    }

}
