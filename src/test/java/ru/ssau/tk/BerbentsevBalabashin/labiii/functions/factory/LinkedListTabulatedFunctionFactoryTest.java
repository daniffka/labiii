package ru.ssau.tk.BerbentsevBalabashin.labiii.functions.factory;
import org.junit.jupiter.api.Test;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.TabulatedFunction;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.LinkedListTabulatedFunction;
import static org.junit.jupiter.api.Assertions.*;
public class LinkedListTabulatedFunctionFactoryTest {
    @Test
    public void testCreate() {
        LinkedListTabulatedFunctionFactory factory = new LinkedListTabulatedFunctionFactory();
        double[] xValues = {1, 4, 6 , 8, 10};
        double[] yValues = {1, 16, 36, 4, 100};
        TabulatedFunction function = factory.create(xValues, yValues);
        assertInstanceOf(LinkedListTabulatedFunction.class, function);
    }
}
