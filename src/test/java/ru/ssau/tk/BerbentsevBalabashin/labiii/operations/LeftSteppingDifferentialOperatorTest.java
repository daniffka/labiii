package ru.ssau.tk.BerbentsevBalabashin.labiii.operations;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.ArrayTabulatedFunction;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.TabulatedFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LeftSteppingDifferentialOperatorTest {
    @Test
    public void testDiff(){
        double[] xVal = {1,2,3};
        double[] yValA = {1,3,9};
        double[] yValB = {3,2,1};

        TabulatedFunction functionA = new ArrayTabulatedFunction(xVal,yValA);
        TabulatedFunction functionB = new ArrayTabulatedFunction(xVal,yValB);
        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService();

        double[] expectedYval= {-2,1,8};
        TabulatedFunction result = service.diff(functionA,functionB);
        for (int i = 0; i<xVal.length;i++){
            assertEquals(expectedYval[i],result.getY(i));
        }
    }

}
