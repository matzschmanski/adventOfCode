package de.schmansk.day4;

import de.schmansk.FileTools;
import de.schmansk.day3.Day3Star1;
import org.h2.store.fs.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Day4Spec {
    @Test
    public void testInput() throws URISyntaxException {

        //test
//        URL resource = Day4.class.getResource("/day4_input");
        URL resource = Day4.class.getResource("/day4_real_input");

        Day4 clazz = new Day4();

        Path path = Paths.get(resource.toURI());
        int i = clazz.doDaBingo(path);

        System.out.println(i);
        Assert.assertTrue("haha",4512==i);

    }

    @Test
    public void starTwo() throws URISyntaxException {

        //test
//        URL resource = Day4Star2.class.getResource("/day4_input");
        URL resource = Day4Star2.class.getResource("/day4_real_input");

        Day4Star2 clazz = new Day4Star2();

        Path path = Paths.get(resource.toURI());
        int i = clazz.doDaBingo(path);

        System.out.println(i);
        Assert.assertTrue("haha",4512==i);

    }

    @Test
    public void testCopyOf() throws URISyntaxException {

        //test
        URL resource = Day4.class.getResource("/day4_input");
        //input
//        URL resource = Day3Star1.class.getResource("/day4_real_input");

        Day4 clazz = new Day4();
//        URL resource = clazz.getClass().getResource("/day4_real_input");
        FileTools.readFileLineByLineToArrayStartWithLine(Paths.get(resource.toURI()),2);

//        System.out.println(i);
//        Assert.assertTrue("haha",4512==i);

    }
}
