package de.schmansk.day6;

import de.schmansk.day5.Day5;
import org.junit.Assert;
import org.junit.Test;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Day6Spec {

    @Test
    public void starOne() throws URISyntaxException {

        //test
//        URL resource = Day6.class.getResource("/day6_input");
        URL resource = Day6.class.getResource("/day6_real_input");

        Day6 clazz = new Day6();

        Path path = Paths.get(resource.toURI());
        long i = clazz.produceLanterFish(path,256);

        System.out.println(i);
        Assert.assertTrue("haha",4512==i);

    }

    @Test
    public void starTwo() throws URISyntaxException {

        //test
//        URL resource = Day6.class.getResource("/day6_input");
        URL resource = Day6.class.getResource("/day6_real_input");

        Day6 clazz = new Day6();

        Path path = Paths.get(resource.toURI());
        long i = clazz.produceMoreLanterFish(path,256);

        System.out.println(i);
        Assert.assertTrue("haha",5934==i);

    }
}
