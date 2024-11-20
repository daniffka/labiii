package ru.ssau.tk.BerbentsevBalabashin.labiii.operations;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.MathFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RightSteppingDifferentialOperatorTest {
    @Test
    void testDerive (){
        double step = 0.1;
        RightSteppingDifferentialOperator rightSteppingDifferentialOperator=new RightSteppingDifferentialOperator(step);

        MathFunction function = x->x*x;
        MathFunction derivative = rightSteppingDifferentialOperator.derive(function);
        assertEquals(4.1,derivative.apply(2.0),1e-5);
        assertEquals(8.1, derivative.apply(4.0),1e-5);
        assertEquals(12.1,derivative.apply(6.0),1e-5);

    }
}
