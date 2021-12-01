import org.junit.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;

public class Day1Star1Spec {
    @Test
    public void testInput() throws URISyntaxException {

        //test
//        URL resource = Day1Star1.class.getResource("/input");
        //input
        URL resource = Day1Star1.class.getResource("/real_input");

        Day1Star1 clazz = new Day1Star1();
        int[] lines = clazz.buildList(Paths.get(resource.toURI()));
        int i = clazz.countIt(lines);
        System.out.println(i);


    }
}
