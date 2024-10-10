package ru.ssau.tk.BerbentsevBalabashin.labiii;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CompositeFunctionTest {
    @Test
    void testApply(){
        MathFunction Func1 = new IdentityFunction();
        MathFunction Func2 = new SqrFunction();
        CompositeFunction compositeFunction1 = new CompositeFunction(Func1, Func2);
        CompositeFunction compositeFunction2 = new CompositeFunction(Func2, Func2);
        CompositeFunction doubleCompositeFunction = new CompositeFunction(compositeFunction1, Func2);
        Assertions.assertEquals(20736, doubleCompositeFunction.apply(12));
        Assertions.assertEquals(16, compositeFunction2.apply(2));
        Assertions.assertEquals(256, doubleCompositeFunction.apply(4));

    }
}