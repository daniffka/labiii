package ru.ssau.tk.BerbentsevBalabashin.labiii.functions;
import ru.ssau.tk.BerbentsevBalabashin.labiii.exeptions.InterpolationException;
import ru.ssau.tk.BerbentsevBalabashin.labiii.exeptions.DifferentLengthOfArraysException;
import ru.ssau.tk.BerbentsevBalabashin.labiii.exeptions.ArrayIsNotSortedException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
public class ArrayTabulatedFunction extends AbstractTabulatedFunction implements Insertable,Removable{

    protected double[] xValues;
    protected double[] yValues;
    protected int count;

    public ArrayTabulatedFunction(double[] xValues,double[] yValues){
        AbstractTabulatedFunction.checkLengthIsTheSame(xValues,yValues);
        AbstractTabulatedFunction.checkSorted(xValues);
        if (xValues.length < 2)
            throw new IllegalArgumentException("list must contain at least two elements");
        this.xValues= Arrays.copyOf(xValues,xValues.length);
        this.yValues= Arrays.copyOf(yValues,yValues.length);
        this.count=xValues.length;
    }

    public ArrayTabulatedFunction(MathFunction source, double xFrom, double xTo, int count){
        if (count < 2)
            throw new IllegalArgumentException("array must contain at least two elements");
        this.count=count;
        this.xValues=new double[count];
        this.yValues=new double[count];

        if (xFrom > xTo) {
            double tmp = xFrom;
            xFrom = xTo;
            xTo = tmp;
        }

        if (xTo == xFrom) {
            double yValue = source.apply(xFrom);
            for (int i = 0; i < count; ++i) {
                xValues[i] = xFrom;
                yValues[i] = yValue;
            }
        }
        else {
            double step = (xTo - xFrom) / (count - 1);
            for (int i = 0; i < count; ++i) {
                xValues[i] = xFrom + i * step;
                yValues[i] = source.apply(xValues[i]);
            }
        }
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public double getX(int index) {
        return xValues[index];
    }

    @Override
    public double getY(int index) {
        return xValues[index];
    }

    @Override
    public void setY(int index, double value) {
        yValues[index]=value;
    }

    @Override
    public double leftBound() {
        return xValues[0];
    }

    @Override
    public double rightBound() {
        return xValues[count-1];
    }

    @Override
    public int indexOfX(double x) {
        int index = Arrays.binarySearch(xValues, x);
        return index >= 0 ? index : -1;
    }

    @Override
    public int indexOfY(double y) {
        int index = Arrays.binarySearch(yValues, y);
        return index >= 0 ? index : -1;
    }

    @Override
     protected int floorIndexOfX(double x) {
        if (x < xValues[0]) {
            throw new IllegalArgumentException("x is less than left bound");
        }
        for (int i = 1; i < count; i++) {
            if (x < xValues[i]) {
                return i - 1;
            }
        }
        return count - 1;
    }

    @Override
    protected double extrapolateLeft(double x) {
        if (count == 1) {
            return yValues[0];
        }
        return interpolate(x, xValues[0], xValues[1], yValues[0], yValues[1]);
    }

    @Override
    protected double extrapolateRight(double x) {
        if (count == 1) {
            return yValues[0];
        }
        return interpolate(x, xValues[count - 2], xValues[count - 1], yValues[count - 2], yValues[count - 1]);
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        if (x>xValues[floorIndex+1] || x<xValues[floorIndex]){
            throw new InterpolationException("x is out of interpolation range");
        }
        return interpolate(x, xValues[floorIndex], xValues[floorIndex + 1], yValues[floorIndex], yValues[floorIndex + 1]);
    }

    @Override
    public void insert(double x, double y) {
        if (indexOfX(x) != -1) {
            setY(indexOfX(x), y);
        } else {
            double[] newXValues = new double[count + 1];
            double[] newYValues = new double[count + 1];

            if (x < leftBound()) {
                newXValues[0] = x;
                newYValues[0] = y;

                System.arraycopy(xValues, 0, newXValues, 1, count);
                System.arraycopy(yValues, 0, newYValues, 1, count);
            } else {
                int index = floorIndexOfX(x);
                System.arraycopy(xValues, 0, newXValues, 0, index + 1);
                System.arraycopy(yValues, 0, newYValues, 0, index + 1);

                newXValues[index + 1] = x;
                newYValues[index + 1] = y;

                System.arraycopy(xValues, index + 1, newXValues, index + 2, count - index - 1);
                System.arraycopy(yValues, index + 1, newYValues, index + 2, count - index - 1);
            }
            count++;
            xValues = newXValues;
            yValues = newYValues;

        }
    }

    public void remove(int index) {
        double[] newXValues = new double[xValues.length - 1];
        double[] newYValues = new double[yValues.length - 1];
        System.arraycopy(xValues, 0, newXValues, 0, index);
        System.arraycopy(yValues, 0, newYValues, 0, index);
        System.arraycopy(xValues, index + 1, newXValues, index, xValues.length - index - 1);
        System.arraycopy(yValues, index + 1, newYValues, index, yValues.length - index - 1);
        xValues = newXValues;
        yValues = newYValues;
        count--;
    }
    @Override
    public Iterator<Point> iterator() {
        return new Iterator<Point>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < count;
            }

            @Override
            public Point next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Point point = new Point(xValues[index], yValues[index]);
                index++;
                return point;
            }
        };
    }



}
