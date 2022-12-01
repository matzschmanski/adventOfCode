package de.schmansk.day18;

import de.schmansk.day17.Day17;
import org.junit.Assert;
import org.junit.Test;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Day18Spec {

    @Test
    public void starOne() throws URISyntaxException {

        //test
//        URL resource = Day18.class.getResource("/day18_input");
        URL resource = Day18.class.getResource("/day18_real_input");

        Day18 clazz = new Day18();

        Path path = Paths.get(resource.toURI());
        long i = clazz.starOne(path);

        System.out.println(i);
        Assert.assertTrue("haha",37==i);

    }

}
