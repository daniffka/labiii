package ru.ssau.tk.BerbentsevBalabashin.labiii.functions;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.BerbentsevBalabashin.labiii.exeptions.ArrayIsNotSortedException;
import ru.ssau.tk.BerbentsevBalabashin.labiii.exeptions.DifferentLengthOfArraysException;
import ru.ssau.tk.BerbentsevBalabashin.labiii.exeptions.InterpolationException;
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
    public void testConstructorWithArrays_ThrowsIllegalArgumentException() {
        double[] xValues = {2};
        double[] yValues = {5};
        assertThrows(IllegalArgumentException.class, () -> new ArrayTabulatedFunction(xValues, yValues));
    }

    @Test
    public void testConstructorWithMathFunction_ReturnsCorrectCount_Values() {
        MathFunction source = x -> x * x;
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(source, 0, 2, 3);
        assertEquals(3, function.getCount());
        assertEquals(0, function.getX(0), 0.0001);
        assertEquals(0, function.getY(0), 0.0001);
        assertEquals(2, function.getX(2), 0.0001);
        assertEquals(4, function.getY(2), 0.0001);
    }

    @Test
    public void testConstructorWithMathFunction_SwappedBounds_ReturnsCorrectCount_Values() {
        MathFunction source = x -> x * x;
        double xFrom = 2;
        double xTo = 0;
        int count = 3;
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(source, xFrom, xTo, count);
        assertEquals(3, function.getCount());
        assertEquals(0, function.getX(0), 0.0001);
        assertEquals(0, function.getY(0), 0.0001);
        assertEquals(4, function.getY(2), 0.0001);
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
    public void testGetX_ThrowsIndexOutOfBoundsException() {
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);
        assertThrows(IndexOutOfBoundsException.class, () -> function.getX(3));
    }
    @Test
    public void testGetY_ThrowsIndexOutOfBoundsException() {
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);
        assertThrows(IndexOutOfBoundsException.class, () -> function.getY(3));
    }

    @Test
    public void testRemove_ThrowsIndexOutOfBoundsException() {
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);
        assertThrows(IndexOutOfBoundsException.class, () -> function.remove(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> function.remove(3));
    }

    @Test
    public void testInterpolate_ThrowsInterpolationException() {
        double[] xValues = {1, 2, 3};
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, new double[]{4, 5, 6});
        assertThrows(InterpolationException.class, () -> function.interpolate(0, 0));
        assertThrows(InterpolationException.class, () -> function.interpolate(5, 1));
    }
}