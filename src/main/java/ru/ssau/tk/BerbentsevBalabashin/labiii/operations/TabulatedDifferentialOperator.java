package ru.ssau.tk.BerbentsevBalabashin.labiii.operations;
import ru.ssau.tk.BerbentsevBalabashin.labiii.concurrent.SynchronizedTabulatedFunction;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.Point;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.TabulatedFunction;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.factory.TabulatedFunctionFactory;
public class TabulatedDifferentialOperator implements DifferentialOperator<TabulatedFunction> {
    private TabulatedFunctionFactory factory;
    public TabulatedDifferentialOperator() {
        this.factory = new ArrayTabulatedFunctionFactory();
    }
    public TabulatedDifferentialOperator(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }
    public TabulatedFunctionFactory getFactory() {
        return factory;
    }
    public void setFactory(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }
    @Override
    public TabulatedFunction derive(TabulatedFunction function) {
        Point[] points = TabulatedFunctionOperationService.asPoints(function);

        int pointsLength = points.length;
        double[] xValues = new double[pointsLength];
        double[] yValues = new double[pointsLength];

        int index;
        for (index = 0; index < pointsLength - 1; index++) {
            xValues[index] = points[index].x;
            yValues[index] = (points[index+1].y - points[index].y)/(points[index+1].x-points[index].x);
        }
        xValues[index] = points[index].x;
        yValues[pointsLength - 1] = yValues[index - 1];

        return factory.create(xValues, yValues);
    }

    public TabulatedFunction deriveSynchronously(TabulatedFunction function) {
        SynchronizedTabulatedFunction synchronizedTabulatedFunction;

        if (function instanceof SynchronizedTabulatedFunction) {
            synchronizedTabulatedFunction = (SynchronizedTabulatedFunction) function;
        } else {
            synchronizedTabulatedFunction = new SynchronizedTabulatedFunction(function);
        }
        return synchronizedTabulatedFunction.doSynchronously(this::derive);
    }

}