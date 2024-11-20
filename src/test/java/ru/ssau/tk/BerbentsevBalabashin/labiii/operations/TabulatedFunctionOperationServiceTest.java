package ru.ssau.tk.BerbentsevBalabashin.labiii.operations;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.ArrayTabulatedFunction;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.Point;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.TabulatedFunction;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.factory.TabulatedFunctionFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TabulatedFunctionOperationServiceTest {
    @Test
    public void testAsPoint(){
        double[] xVal = {1,2,3};
        double[] yVal = {1,4,6};
        TabulatedFunction tabulatedFunction = new ArrayTabulatedFunction(xVal,yVal);

        Point[] expectedPoint = {
                new Point(1,1),
                new Point(2,4),
                new Point(3,6)
        };

        Point[] currPoint = TabulatedFunctionOperationService.asPoints(tabulatedFunction);
        for (int i = 0; i<expectedPoint.length;i++){
            assertEquals(expectedPoint[i].x,currPoint[i].x);
            assertEquals(expectedPoint[i].y,currPoint[i].y);
        }
    }

    @Test
    public void testCreateFactory(){
        TabulatedFunctionFactory tabulatedFunctionFactory = new ArrayTabulatedFunctionFactory();
        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService();
        service.setFactory(tabulatedFunctionFactory);
        assertEquals(tabulatedFunctionFactory,service.getFactory());
    }

    @Test
    public void testConstructor(){
        TabulatedFunctionOperationService tabulatedFunctionOperationService = new TabulatedFunctionOperationService();
        assertNotNull(tabulatedFunctionOperationService.getFactory());
    }

    @Test
    public void testSetFactory(){
        TabulatedFunctionFactory tabulatedFunctionFactory = new ArrayTabulatedFunctionFactory();
        TabulatedFunctionOperationService tabulatedFunctionOperationService = new TabulatedFunctionOperationService();
        tabulatedFunctionOperationService.setFactory(tabulatedFunctionFactory);
        assertEquals(tabulatedFunctionFactory,tabulatedFunctionOperationService.getFactory());
    }

    @Test
    public void testGetFactory(){
        TabulatedFunctionFactory tabulatedFunctionFactory = new ArrayTabulatedFunctionFactory();
        TabulatedFunctionOperationService tabulatedFunctionOperationService = new TabulatedFunctionOperationService(tabulatedFunctionFactory);
        assertEquals(tabulatedFunctionFactory,tabulatedFunctionOperationService.getFactory());
    }

    @Test
    public void testParametrizedConstructor(){
        TabulatedFunctionFactory tabulatedFunctionFactory = new ArrayTabulatedFunctionFactory();
        TabulatedFunctionOperationService tabulatedFunctionOperationService = new TabulatedFunctionOperationService(tabulatedFunctionFactory);
        assertEquals(tabulatedFunctionFactory,tabulatedFunctionOperationService.getFactory());
    }

    @Test
    public void testSum(){
        double[] xVal = {1,2,3};
        double[] yValA = {1,3,5};
        double[] yValB = {5,2,1};

        TabulatedFunction functionA = new ArrayTabulatedFunction(xVal,yValA);
        TabulatedFunction functionB = new ArrayTabulatedFunction(xVal,yValB);
        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService();

        double[] expectedYval= {6,5,6};
        TabulatedFunction result = service.sum(functionA,functionB);
        for (int i = 0; i<xVal.length;i++){
            assertEquals(expectedYval[i],result.getY(i));
        }
    }

    @Test
    public void testDiff(){
        double[] xVal = {1,2,3};
        double[] yValA = {1,5,7};
        double[] yValB = {3,2,1};

        TabulatedFunction functionA = new ArrayTabulatedFunction(xVal,yValA);
        TabulatedFunction functionB = new ArrayTabulatedFunction(xVal,yValB);
        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService();

        double[] expectedYval= {-2,3,6};
        TabulatedFunction result = service.diff(functionA,functionB);
        for (int i = 0; i<xVal.length;i++){
            assertEquals(expectedYval[i],result.getY(i));
        }
    }


    @Test
    public void testMult(){
        double[] xVal = {1,2,3};
        double[] yValA = {1,3,5};
        double[] yValB = {3,2,1};

        TabulatedFunction functionA = new ArrayTabulatedFunction(xVal,yValA);
        TabulatedFunction functionB = new ArrayTabulatedFunction(xVal,yValB);
        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService();

        double[] expectedYval= {3,6,5};
        TabulatedFunction result = service.mult(functionA,functionB);
        for (int i = 0; i<xVal.length;i++){
            assertEquals(expectedYval[i],result.getY(i));
        }
    }


    @Test
    public void testDiv(){
        double[] xVal = {1,2,3};
        double[] yValA = {9,8,5};
        double[] yValB = {3,4,5};

        TabulatedFunction functionA = new ArrayTabulatedFunction(xVal,yValA);
        TabulatedFunction functionB = new ArrayTabulatedFunction(xVal,yValB);
        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService();

        double[] expectedYval= {3,2,1};
        TabulatedFunction result = service.div(functionA,functionB);
        for (int i = 0; i<xVal.length;i++){
            assertEquals(expectedYval[i],result.getY(i));
        }
    }
}
