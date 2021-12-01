import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class Day1Star1 {
    public int doSomething(Path inputFilePath) {
        //read file
        AtomicInteger amountOfIncrease=new AtomicInteger(0);
        AtomicInteger lastNumber = new AtomicInteger(-1);
        try (Stream<String> lines = Files.lines(inputFilePath, Charset.defaultCharset())) {

            lines.forEachOrdered(s ->{
                int currentLineAsInt = Integer.parseInt(s);
                if(lastNumber.get() < currentLineAsInt){
                    if(lastNumber.get() >=0) {
                        amountOfIncrease.getAndIncrement();
                    }else{
                        lastNumber.set(currentLineAsInt);
                    }

            }
                lastNumber.set(currentLineAsInt);}
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return amountOfIncrease.get();
    }

}
