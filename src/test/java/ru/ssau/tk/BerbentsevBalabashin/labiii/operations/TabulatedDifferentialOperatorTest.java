package ru.ssau.tk.BerbentsevBalabashin.labiii.operations;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.ArrayTabulatedFunction;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.TabulatedFunction;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class TabulatedDifferentialOperatorTest {
    private TabulatedFunction tabulatedFunction;
    private TabulatedDifferentialOperator differentialOperator;

    private TabulatedFunction createArrayTabulatedFunction() {
        return new ArrayTabulatedFunction(new double[]{-3, 1.5, 6, 10.5, 15}, new double[]{9, 2.25, 36, 110.25, 225});
    }

    private TabulatedFunction createLinkedListTabulatedFunction() {
        return new LinkedListTabulatedFunction(new double[]{-3, 1.5, 6, 10.5, 15}, new double[]{9, 2.25, 36, 110.25, 225});
    }

    @Test
    public void testDefaultFactory() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {1.0, 4.0, 9.0};
        TabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);
        TabulatedDifferentialOperator operator = new TabulatedDifferentialOperator();
        TabulatedFunction derivative = operator.derive(function);
        assertEquals(3.0, derivative.getY(0), 0.01);
        assertEquals(5.0, derivative.getY(1), 0.01);
    }


    @Test
    void testArrayTabulatedFunction() {
        tabulatedFunction = createArrayTabulatedFunction();
        differentialOperator = new TabulatedDifferentialOperator();
        TabulatedFunction derivedFunction = differentialOperator.derive(tabulatedFunction);

        assertInstanceOf(ArrayTabulatedFunction.class, derivedFunction);
        assertEquals(16.5, derivedFunction.apply(6), 0.01);
    }
    @Test
    void testListTabulatedFunction() {
        tabulatedFunction = createLinkedListTabulatedFunction();
        differentialOperator = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());
        TabulatedFunction derivedFunction = differentialOperator.derive(tabulatedFunction);

        assertInstanceOf(LinkedListTabulatedFunction.class, derivedFunction);
        assertEquals(16.5, derivedFunction.apply(6), 0.01);
    }

    @Test
    void testGetFactory() {
        differentialOperator = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());
        assertInstanceOf(LinkedListTabulatedFunctionFactory.class, differentialOperator.getFactory());
    }
    @Test
    void testSetFactory() {
        differentialOperator = new TabulatedDifferentialOperator();
        LinkedListTabulatedFunctionFactory factory = new LinkedListTabulatedFunctionFactory();
        differentialOperator.setFactory(factory);
        assertInstanceOf(LinkedListTabulatedFunctionFactory.class, differentialOperator.getFactory());
    }
    @Test
    void testDeriveSynchronously() {
        tabulatedFunction = createLinkedListTabulatedFunction();
        differentialOperator = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());
        TabulatedFunction derivedFunction = differentialOperator.deriveSynchronously(tabulatedFunction);

        assertEquals(16.5, derivedFunction.apply(6), 0.01);
    }

}