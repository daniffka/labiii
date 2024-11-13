package ru.ssau.tk.BerbentsevBalabashin.labiii.functions;

import ru.ssau.tk.BerbentsevBalabashin.labiii.exeptions.ArrayIsNotSortedException;
import ru.ssau.tk.BerbentsevBalabashin.labiii.exeptions.DifferentLengthOfArraysException;

public abstract class AbstractTabulatedFunction implements TabulatedFunction {
    protected abstract int floorIndexOfX(double x);
    protected abstract double extrapolateLeft(double x);
    protected abstract double extrapolateRight(double x);
    protected abstract double interpolate(double x, int floorIndex);

    protected double interpolate(double x, double leftX, double rightX, double leftY, double rightY) {
        return leftY + ( (x - leftX) * (rightY - leftY) / (rightX - leftX) );
    }

    public static void checkLengthIsTheSame(double[] xValues, double[] yValues){
        if (xValues.length!=yValues.length){
            throw new DifferentLengthOfArraysException("different array lengths");
        }
    }
    public static void checkSorted(double[] xValues){
        for (int i = 0; i<xValues.length-1;i++){
            if (xValues[i]>xValues[i+1]) throw new ArrayIsNotSortedException("the array must be sorted in ascending order");
        }
    }

    @Override
    public double apply(double x) {
        if (x < leftBound()) {
            return extrapolateLeft(x);
        }
        else if (x > rightBound()) {
            return extrapolateRight(x);
        }
        else {
            if (indexOfX(x) != -1) {
                return getY(indexOfX(x));
            }
            else {
                return interpolate(x, floorIndexOfX(x));
            }
        }
    }
}
