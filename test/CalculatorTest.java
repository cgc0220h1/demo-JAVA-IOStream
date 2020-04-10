import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void calculateFromFile() {
        Calculator calculator = new Calculator();
        try {
            int result = calculator.calculateFromFile("resources/Numbers.dat");
            int expected = 31;
            assertEquals(expected, result);
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }
    }
}