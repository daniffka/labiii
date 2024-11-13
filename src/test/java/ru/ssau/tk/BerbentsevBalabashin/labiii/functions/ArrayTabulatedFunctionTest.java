package ru.ssau.tk.BerbentsevBalabashin.labiii.functions;

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


}