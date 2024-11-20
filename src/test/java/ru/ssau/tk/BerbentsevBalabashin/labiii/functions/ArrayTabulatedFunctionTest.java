package ru.ssau.tk.BerbentsevBalabashin.labiii.functions;
import ru.ssau.tk.BerbentsevBalabashin.labiii.exeptions.InterpolationException;
import org.junit.jupiter.api.Test;
import ru.ssau.tk.BerbentsevBalabashin.labiii.exeptions.ArrayIsNotSortedException;
import ru.ssau.tk.BerbentsevBalabashin.labiii.exeptions.DifferentLengthOfArraysException;

import static org.junit.jupiter.api.Assertions.*;

class ArrayTabulatedFunctionTest {

    @Test
    void floorIndexOfX() {
        double[] xVal = {1.,2.,3.};
        double[] yVal = {4.,5.,6.};
        ArrayTabulatedFunction arrayTabulatedFunction = new ArrayTabulatedFunction(xVal,yVal);

        assertEquals(1,arrayTabulatedFunction.floorIndexOfX(2.5));
        assertEquals(2,arrayTabulatedFunction.floorIndexOfX(4));
    }

    @Test
    void extrapolateLeft() {
        double[] xVal = {1.,2.,3.};
        double[] yVal = {3.,4.,5.};
        ArrayTabulatedFunction arrayTabulatedFunction = new ArrayTabulatedFunction(xVal,yVal);

        assertEquals(7,arrayTabulatedFunction.extrapolateRight(5));
    }

    @Test
    void extrapolateRight() {
        double[] xVal = {1.,2.,3.};
        double[] yVal = {3.,4.,5.};
        ArrayTabulatedFunction arrayTabulatedFunction = new ArrayTabulatedFunction(xVal,yVal);

        assertEquals(5,arrayTabulatedFunction.extrapolateRight(3));
    }

    @Test
    void interpolate() {
        double[] xVal = {1.,2.,3.};
        double[] yVal = {3.,4.,5.};
        ArrayTabulatedFunction arrayTabulatedFunction = new ArrayTabulatedFunction(xVal,yVal);

        assertEquals(4,arrayTabulatedFunction.interpolate(2, arrayTabulatedFunction.floorIndexOfX(2)));
    }

    @Test
    void getCount() {
        double[] xVal = {1.,2.,3.};
        double[] yVal = {3.,4.,5.};
        ArrayTabulatedFunction arrayTabulatedFunction = new ArrayTabulatedFunction(xVal,yVal);

        assertEquals(3,arrayTabulatedFunction.getCount());
    }

    @Test
    void getX() {
        double[] xVal = {1.,2.,3.};
        double[] yVal = {3.,4.,5.};
        ArrayTabulatedFunction arrayTabulatedFunction = new ArrayTabulatedFunction(xVal,yVal);

        assertEquals(2,arrayTabulatedFunction.getX(1));

    }

    @Test
    void getY() {
        double[] xVal = {1.,2.,3.};
        double[] yVal = {3.,4.,5.};
        ArrayTabulatedFunction arrayTabulatedFunction = new ArrayTabulatedFunction(xVal,yVal);

        assertEquals(4,arrayTabulatedFunction.getY(1));
    }

    @Test
    void setY_Test() {
        double[] xVal = {1.,2.,3.};
        double[] yVal = {3.,4.,5.};
        ArrayTabulatedFunction arrayTabulatedFunction = new ArrayTabulatedFunction(xVal,yVal);
        arrayTabulatedFunction.setY(2,3);
        assertEquals(3,arrayTabulatedFunction.getY(2));
    }

    @Test
    public void testConstructors() {
        double[] xValues = {1., 2., 3.};
        double[] yValues = {2., 4., 6.};
        ArrayTabulatedFunction arrayTabulatedFunction = new ArrayTabulatedFunction(xValues, yValues);

        assertDoesNotThrow(()->{
            new ArrayTabulatedFunction(new double[]{1.0, 2.0, 3.0}, new double[]{2.0, 4.0, 6.0});
        });
        assertThrows(DifferentLengthOfArraysException.class, ()->{
            new ArrayTabulatedFunction(new double[]{1.0, 2.0, 3.0}, new double[]{2.0, 4.0});
        });
        assertThrows(DifferentLengthOfArraysException.class, ()->{
            new ArrayTabulatedFunction(new double[]{1.0, 2.0}, new double[]{2.0, 4.0, 6.0});
        });

        assertThrows(ArrayIsNotSortedException.class, ()->{
            new ArrayTabulatedFunction(new double[]{2.0, 1.0, 3.0}, new double[]{2.0, 4.0, 6.0});
        });

        assertEquals(3, arrayTabulatedFunction.getCount());
        assertEquals(1., arrayTabulatedFunction.getX(0));
        assertEquals(2., arrayTabulatedFunction.getY(0));
        assertEquals(3., arrayTabulatedFunction.getX(2));
        assertEquals(6., arrayTabulatedFunction.getY(2));
        assertThrows(IllegalArgumentException.class, () -> new ArrayTabulatedFunction(new double[]{1}, new double[]{1}));

        MathFunction func = x -> x * 2;
        ArrayTabulatedFunction arrayTabulatedFunctionMath = new ArrayTabulatedFunction(func, 0, 4, 5);

        assertEquals(5, arrayTabulatedFunctionMath.getCount());
        assertEquals(0., arrayTabulatedFunctionMath.getX(0));
        assertEquals(8., arrayTabulatedFunctionMath.getY(4));
        assertThrows(IllegalArgumentException.class, () -> new ArrayTabulatedFunction(func, 0, 5, 1));
    }
    @Test
    void indexOfX() {
        double[] xVal = {1.,2.,3.};
        double[] yVal = {3.,4.,5.};
        ArrayTabulatedFunction arrayTabulatedFunction = new ArrayTabulatedFunction(xVal,yVal);

        assertEquals(1,arrayTabulatedFunction.indexOfX(2));
    }

    @Test
    void indexOfY() {
        double[] xVal = {1.,2.,3.};
        double[] yVal = {3.,4.,5.};
        ArrayTabulatedFunction arrayTabulatedFunction = new ArrayTabulatedFunction(xVal,yVal);

        assertEquals(2,arrayTabulatedFunction.indexOfY(5));
    }

    @Test
    void leftBound() {
        double[] xVal = {1.,2.,3.};
        double[] yVal = {3.,4.,5.};
        ArrayTabulatedFunction arrayTabulatedFunction = new ArrayTabulatedFunction(xVal,yVal);

        assertEquals(1,arrayTabulatedFunction.leftBound());
    }

    @Test
    void rightBound() {
        double[] xVal = {1.,2.,3.};
        double[] yVal = {3.,4.,5.};
        ArrayTabulatedFunction arrayTabulatedFunction = new ArrayTabulatedFunction(xVal,yVal);

        assertEquals(3,arrayTabulatedFunction.rightBound());
    }

    @Test
    void RemoveTest(){
        double[] xVal = {1.,2.,3.};
        double[] yVal = {3.,4.,5.};
        ArrayTabulatedFunction arrayTabulatedFunction = new ArrayTabulatedFunction(xVal,yVal);
        arrayTabulatedFunction.remove(1);

        assertEquals(3,arrayTabulatedFunction.getX(arrayTabulatedFunction.xValues.length-1));
    }
    @Test
    void Insert_Test(){
        double[] xVal = {1.,2.,3.};
        double[] yVal = {2.,7.,9.};
        ArrayTabulatedFunction arrayTabulatedFunction = new ArrayTabulatedFunction(xVal,yVal);
        arrayTabulatedFunction.insert(1.5,4.);

        assertEquals(1.0,arrayTabulatedFunction.getX(0));
    }

    @Test
    void TestDefConstructorSortedArr(){
        ArrayIsNotSortedException arrayIsNotSortedException= new ArrayIsNotSortedException();
        assertNull(arrayIsNotSortedException.getMessage());
    }

    @Test
    void TestDefConstructorDifferentLength() {
        DifferentLengthOfArraysException differentLengthOfArraysException= new DifferentLengthOfArraysException();
        assertNull(differentLengthOfArraysException.getMessage());
    }

    @Test
    void TestMessageConstructorDifferentLength() {
        String message = "Arrays length isn't the same";
        DifferentLengthOfArraysException differentLengthOfArraysException = new DifferentLengthOfArraysException(message);
        assertEquals(message,differentLengthOfArraysException.getMessage());
    }
    @Test
    public void testGetY_ThrowsIndexOutOfBoundsException() {
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);
        assertThrows(IndexOutOfBoundsException.class, () -> function.getY(3));
    }

    @Test
    public void testSetY_ThrowsIndexOutOfBoundsException() {
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);
        assertThrows(IndexOutOfBoundsException.class, () -> function.setY(3, 10));
    }
    @Test
    public void testInterpolate_ThrowsInterpolationException() {
        double[] xValues = {1, 2, 3};
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, new double[]{4, 5, 6});
        assertThrows(InterpolationException.class, () -> function.interpolate(0, 0));
        assertThrows(InterpolationException.class, () -> function.interpolate(5, 1));
    }



}