import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class GeneratorTest {

    @Test
    void randomNumber() {
        Generator generator = new Generator();
        File directory = new File("resources");
        directory.mkdir();
        try {
            generator.randomNumber("resources/Numbers.dat",5);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}