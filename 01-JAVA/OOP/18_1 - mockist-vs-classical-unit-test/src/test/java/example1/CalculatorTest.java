package example1;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {
    @Test
    public void addTest() {
        int a = 5;
        int b = 10;
        Calculator calculator = new Calculator();

        int result = calculator.add(a, b);

        assertEquals(15, result);
    }
}
