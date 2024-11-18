package ru.ssau.tk.BerbentsevBalabashin.labiii.functions;
import java.util.Iterator;
import java.util.NoSuchElementException;
import ru.ssau.tk.BerbentsevBalabashin.labiii.exeptions.DifferentLengthOfArraysException;
import ru.ssau.tk.BerbentsevBalabashin.labiii.exeptions.ArrayIsNotSortedException;
import ru.ssau.tk.BerbentsevBalabashin.labiii.exeptions.InterpolationException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LinkedListTabulatedFunctionTest {

    @Test
    public void ConstructorWithArrays_test() {
        double[] xValues = {1, 2, 3, 4, 5};
        double[] yValues = {1, 4, 9, 16, 25};
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);
        assertDoesNotThrow(()->{
            new LinkedListTabulatedFunction(new double[]{1.0, 2.0, 3.0}, new double[]{2.0, 4.0, 6.0});
        });
        assertThrows(DifferentLengthOfArraysException.class, ()->{
            new LinkedListTabulatedFunction(new double[]{1.0, 2.0, 3.0}, new double[]{2.0, 4.0});
        });
        assertThrows(DifferentLengthOfArraysException.class, ()->{
            new LinkedListTabulatedFunction(new double[]{1.0, 2.0}, new double[]{2.0, 4.0, 6.0});
        });

        assertThrows(ArrayIsNotSortedException.class, ()->{
            new LinkedListTabulatedFunction(new double[]{2.0, 1.0, 3.0}, new double[]{2.0, 4.0, 6.0});
        });

        assertEquals(5, function.getCount());
        assertEquals(4, function.getX(3));
        assertEquals(9, function.getY(2));
    }

    @Test
    public void ConstructorWithMathFunction_test() {
        MathFunction sqrFunction = new SqrFunction();
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(sqrFunction, 1, 5, 5);

        assertEquals(5, function.getCount());
        assertEquals(2, function.getX(1));
        assertEquals(16, function.getY(3));
        assertThrows(IllegalArgumentException.class, () -> new LinkedListTabulatedFunction(sqrFunction, 0, 5, 1));
    }

    @Test
    public void getX_test() {
        double[] xValues = {1, 2, 4, 7, 8};
        double[] yValues = {6, 7, 8, 11 ,16};
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);
        assertEquals(7, function.getX(3));
        assertThrows(IllegalArgumentException.class, () -> function.getX(-1));
    }

    @Test
    public void getY_test() {
        double[] xValues = {1, 2, 3, 4, 5};
        double[] yValues = {67, 34, 1, 16, 58};
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);

        assertEquals(34, function.getY(1));
        assertThrows(IllegalArgumentException.class, () -> function.getX(-1));
    }
    public void SetY_test() {
        double[] xValues = {1, 2, 3, 4, 5};
        double[] yValues = {1, 4, 9, 16, 25};
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);

        function.setY(3, 8);
        assertEquals(8, function.getY(3));
        assertThrows(IllegalArgumentException.class, () -> function.getX(-1));
    }

    @Test
    public void testIndexOfX() {
        double[] xValues = {1, 2, 3, 4, 5};
        double[] yValues = {1, 4, 9, 16, 25};
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);

        assertEquals(2, function.indexOfX(3));
        assertEquals(-1, function.indexOfX(6));
    }
    @Test
    public void testIndexOfY() {
        double[] xValues = {1, 3, 5, 7, 9};
        double[] yValues = {11, 22, 33, 44, 55};
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);

        assertEquals(-1, function.indexOfY(9));
        assertEquals(3, function.indexOfY(44));
    }

    @Test
    public void testFloorIndexOfX() {
        double[] xValues = {2, 4, 6, 11, 12};
        double[] yValues = {13, 15, 17, 18, 25};
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);
        assertEquals(1, function.floorIndexOfX(5));
    }
    @Test
    public void testInterpolate() {
        double[] xValues = {1, 3, 6, 7, 9};
        double[] yValues = {11, 45, 46, 48, 50};
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);

        assertEquals(6.5, function.interpolate(2.5, 2, 3, 4, 9), 0.00001);
    }

    @Test
    public void testExtrapolateLeft() {
        double[] xValues = {1, 2, 3, 4, 5};
        double[] yValues = {1, 4, 9, 16, 25};
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);

        assertEquals(-2, function.extrapolateLeft(0), 0.00001);
    }

    @Test
    public void testExtrapolateRight() {
        double[] xValues = {1, 2, 3, 4, 5};
        double[] yValues = {6, 7, 8, 9, 10};
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);

        assertEquals(11, function.extrapolateRight(6), 0.00001);
    }
    @Test
    public void Apply_test() {
        double[] xValues = {1, 2, 3, 4, 5};
        double[] yValues = {6, 7, 8, 9, 10};
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);
        assertEquals(7.5, function.apply(2.5), 0.00001);
        assertEquals(11, function.apply(6), 0.00001);
    }

    @Test
    public void remove_test() {
        double[] xValues = {1, 2, 3, 4, 5};
        double[] yValues = {1, 4, 9, 16, 25};
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);

        function.remove(2);
        assertEquals(4, function.getCount());
        assertEquals(4, function.getX(2), 0.00001);
        assertThrows(IllegalArgumentException.class, () -> function.remove(-1));

        function.remove(0);
        assertEquals(3, function.getCount());
        assertEquals(2, function.getX(0), 0.00001);

        function.remove(2);
        assertEquals(2, function.getCount());
        assertEquals(4, function.getX(1), 0.00001);
        function.remove(1);
        function.remove(0);
        assertThrows(IllegalArgumentException.class, () -> function.remove(0));
    }

    @Test
    public void testInsertionBetween() {
        double[] xValues = {1, 2, 3, 4, 5};
        double[] yValues = {1, 4, 9, 16, 25};
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);

        function.insert(3.5, 12.25);
        assertEquals(3.5, function.getX(3), 1e-6);
        assertEquals(12.25, function.getY(3), 1e-6);
        assertEquals(6, function.getCount());
    }

    @Test
    public void testInsertionFirst() {
        double[] xValues = {1, 2, 3, 4, 5};
        double[] yValues = {1, 4, 9, 16, 25};
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);

        function.insert(0, 0);
        assertEquals(0, function.getX(0), 1e-6);
        assertEquals(0, function.getY(0), 1e-6);
        assertEquals(6, function.getCount());
    }

    @Test
    public void testInsertionLast() {
        double[] xValues = {1, 2, 3, 4, 5};
        double[] yValues = {1, 4, 9, 16, 25};
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);


        function.insert(6, 36);
        assertEquals(6, function.getX(function.getCount() - 1), 1e-6);
        assertEquals(36, function.getY(function.getCount() - 1), 1e-6);
        assertEquals(6, function.getCount());
    }

    @Test
    public void iteratorForEachTest() {
        double[] xValues = {2, 4, 6, 8, 10};
        double[] yValues = {4, 16, 36, 64, 100};
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);
        int i = 0;
        for (Point point : function) {
            assertEquals(xValues[i], point.x, 1e-6);
            assertEquals(yValues[i], point.y, 1e-6);
            i++;
        }
    }

    @Test
    public void iteratorWhileTest() {
        double[] xValues = {2, 4, 6, 8, 10};
        double[] yValues = {4, 16, 36, 64, 100};
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);
        Iterator<Point> iterator = function.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            Point point = iterator.next();
            assertEquals(xValues[i], point.x, 1e-6);
            assertEquals(yValues[i], point.y, 1e-6);
            i++;
        }
        assertThrows(NoSuchElementException.class, iterator::next);
    }

}