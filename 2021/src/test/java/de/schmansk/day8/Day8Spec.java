package de.schmansk.day8;

import de.schmansk.day7.Day7;
import org.junit.Assert;
import org.junit.Test;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Day8Spec {

    @Test
    public void starOne() throws URISyntaxException {

        //test
        URL resource = Day8.class.getResource("/day8_input");
//        URL resource = Day8.class.getResource("/day8_real_input");

        Day8 clazz = new Day8();

        Path path = Paths.get(resource.toURI());
        long i = clazz.starOne(path);

        System.out.println(i);
        Assert.assertTrue("haha",37==i);

    }

}
