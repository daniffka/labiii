package ru.ssau.tk.BerbentsevBalabashin.labiii.concurrent;

import org.junit.jupiter.api.*;

import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.ArrayTabulatedFunction;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.Point;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.TabulatedFunction;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class SynchronizedTabulatedFunctionTest {
    private SynchronizedTabulatedFunction synchronizedFunction;
    private TabulatedFunction tabulatedFunction;

    @BeforeEach
    public void setUp() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 4.0, 6.0};
        tabulatedFunction = new ArrayTabulatedFunction(xValues, yValues);
        synchronizedFunction = new SynchronizedTabulatedFunction(tabulatedFunction);
    }

    @Test
    void synchronize() {
        assertEquals(3.0, (double) synchronizedFunction.doSynchronously(SynchronizedTabulatedFunction::getCount));
        synchronizedFunction.doSynchronously(func -> {
            func.setY(1, 3);
            return null;
        });

        assertEquals(1, synchronizedFunction.getX(0));
        assertEquals(3, synchronizedFunction.getY(1));
        assertEquals(1, synchronizedFunction.doSynchronously(SynchronizedTabulatedFunction::leftBound));
        synchronizedFunction.doSynchronously(func -> {
            func.setY(2, 0);
            return null;
        });

        assertEquals(3, synchronizedFunction.doSynchronously(SynchronizedTabulatedFunction::rightBound));
        assertEquals(0, synchronizedFunction.getY(synchronizedFunction.getCount() - 1));
    }

    @Test
    public void testGetCount() {
        assertEquals(3, synchronizedFunction.getCount());
    }

    @Test
    public void testGetX() {
        assertEquals(1.0, synchronizedFunction.getX(0));
        assertEquals(2.0, synchronizedFunction.getX(1));
        assertEquals(3.0, synchronizedFunction.getX(2));
    }

    @Test
    public void testGetY() {
        assertEquals(2.0, synchronizedFunction.getY(0));
        assertEquals(4.0, synchronizedFunction.getY(1));
        assertEquals(6.0, synchronizedFunction.getY(2));
    }

    @Test
    public void testSetY() {
        synchronizedFunction.setY(1, 5.0);
        assertEquals(5.0, synchronizedFunction.getY(1));
    }

    @Test
    public void testIndexOfX() {
        assertEquals(1, synchronizedFunction.indexOfX(2.0));
        assertEquals(-1, synchronizedFunction.indexOfX(4.0));
    }

    @Test
    public void testIndexOfY() {
        assertEquals(0, synchronizedFunction.indexOfY(2.0));
        assertEquals(-1, synchronizedFunction.indexOfY(5.0));
    }

    @Test
    public void testApply() {
        assertEquals(4.0, synchronizedFunction.apply(2.0));
        assertEquals(8.0, synchronizedFunction.apply(4.0));
    }

    @Test
    public void testLeftBound() {
        assertEquals(1.0, synchronizedFunction.leftBound());
    }

    @Test
    public void testRightBound() {
        assertEquals(3.0, synchronizedFunction.rightBound());
    }

    @Test
    public void testIterator() {
        Iterator<Point> iterator = synchronizedFunction.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(1.0, iterator.next().getX());
        assertTrue(iterator.hasNext());
        assertEquals(2.0, iterator.next().getX());
        assertTrue(iterator.hasNext());
        assertEquals(3.0, iterator.next().getX());
        assertFalse(iterator.hasNext());
    }
}
