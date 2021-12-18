package de.schmansk.day16;

import de.schmansk.day15.Day15;
import org.junit.Assert;
import org.junit.Test;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Day16Spec {

    @Test
    public void starOne() throws URISyntaxException {

        //test
        URL resource = Day16.class.getResource("/day16_input");
//        URL resource = Day16.class.getResource("/day16_real_input");

        Day16 clazz = new Day16();

        Path path = Paths.get(resource.toURI());
        long i = clazz.starOne(path);

        System.out.println(i);
        Assert.assertTrue("haha",37==i);

    }

}
