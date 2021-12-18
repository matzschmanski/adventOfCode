package de.schmansk.day14;

import de.schmansk.day13.Day13;
import org.junit.Assert;
import org.junit.Test;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Day14Spec {

    @Test
    public void starOne() throws URISyntaxException {

        //test
//        URL resource = Day14.class.getResource("/day14_input");
        URL resource = Day14.class.getResource("/day14_real_input");

        Day14 clazz = new Day14();

        Path path = Paths.get(resource.toURI());
        long i = clazz.starOne(path);

        System.out.println(i);
        Assert.assertTrue("haha",37==i);

    }

}
