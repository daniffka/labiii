package ru.ssau.tk.BerbentsevBalabashin.labiii.functions.factory;
import org.junit.jupiter.api.Test;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.TabulatedFunction;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.ArrayTabulatedFunction;
import static org.junit.jupiter.api.Assertions.*;
public class ArrayTabulatedFunctionFactoryTest {
    @Test
    public void createArrayTest() {
        ArrayTabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
        double[] xValues = {1, 4, 6, 8, 10};
        double[] yValues = {1, 16, 36, 64, 100};
        TabulatedFunction function = factory.create(xValues, yValues);

        assertInstanceOf(ArrayTabulatedFunction.class, function);
    }
}
