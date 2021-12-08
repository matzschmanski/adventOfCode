package de.schmansk.day5;

import de.schmansk.FileTools;
import de.schmansk.day4.Day4;
import de.schmansk.day4.Day4Star2;
import org.junit.Assert;
import org.junit.Test;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Day5Spec {
//    @Test
//    public void testInput() throws URISyntaxException {
//
//        //test
////        URL resource = Day5.class.getResource("/day4_input");
//        URL resource = Day5.class.getResource("/day4_real_input");
//
//        Day5 clazz = new Day5();
//
//        Path path = Paths.get(resource.toURI());
//        int i = clazz.doDaBingo(path);
//
//        System.out.println(i);
//        Assert.assertTrue("haha",4512==i);
//
//    }

    @Test
    public void starOne() throws URISyntaxException {

        //test
        URL resource = Day5.class.getResource("/day5_input");
//        URL resource = Day5.class.getResource("/day5_real_input");

        Day5 clazz = new Day5();

        Path path = Paths.get(resource.toURI());
        int i = clazz.countDemHydrothermalVentsBwonsandi(path);

        System.out.println(i);
        Assert.assertTrue("haha",4512==i);

    }
}
