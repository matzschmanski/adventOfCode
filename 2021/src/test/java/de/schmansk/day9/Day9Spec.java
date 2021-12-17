package de.schmansk.day9;

import de.schmansk.day8.Day8;
import org.junit.Assert;
import org.junit.Test;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Day9Spec {

    @Test
    public void starOne() throws URISyntaxException {

        //test
//        URL resource = Day9.class.getResource("/day9_input");
        URL resource = Day9.class.getResource("/day9_real_input");

        Day9 clazz = new Day9();

        Path path = Paths.get(resource.toURI());
        long i = clazz.starTwo(path);

        System.out.println(i);
        Assert.assertTrue("haha",37==i);

    }

}
