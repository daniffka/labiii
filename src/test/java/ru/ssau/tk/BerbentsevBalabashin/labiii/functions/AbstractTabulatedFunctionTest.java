package ru.ssau.tk.BerbentsevBalabashin.labiii.functions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class AbstractTabulatedFunctionTest {

    @Test
    public void testToString() {
        double[] xValues = {0.0, 0.5, 1.0};
        double[] yValues = {0.0, 0.25, 1.0};
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);
        String expectedString = "LinkedListTabulatedFunction size = 3\n[0.0; 0.0]\n[0.5; 0.25]\n[1.0; 1.0]\n";
        assertEquals(expectedString, function.toString());
    }
}