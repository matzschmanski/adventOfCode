package de.schmansk.day13;

import de.schmansk.day12.Day12;
import org.junit.Assert;
import org.junit.Test;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Day13Spec {

    @Test
    public void starOne() throws URISyntaxException {

        //test
//        URL resource = Day13.class.getResource("/day13_input");
        URL resource = Day13.class.getResource("/day13_real_input");

        Day13 clazz = new Day13();

        Path path = Paths.get(resource.toURI());
        long i = clazz.starOne(path);

        System.out.println(i);
        Assert.assertTrue("haha",37==i);

    }

}
