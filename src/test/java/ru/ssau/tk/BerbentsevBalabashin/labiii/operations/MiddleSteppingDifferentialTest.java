package ru.ssau.tk.BerbentsevBalabashin.labiii.operations;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.MathFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MiddleSteppingDifferentialTest {
    @Test
    public void testDiff() {
        double step = 0.1;
        MiddleSteppingDifferentialOperator middleSteppingDifferentialOperator = new MiddleSteppingDifferentialOperator(step);

        MathFunction function = x ->x*x;
        MathFunction derivative = middleSteppingDifferentialOperator.derive(function);
        assertEquals(4, derivative.apply(2.0),1e-5);
        assertEquals(8, derivative.apply(4.0),1e-5);
        assertEquals(12, derivative.apply(6.0),1e-5);
    }
}

