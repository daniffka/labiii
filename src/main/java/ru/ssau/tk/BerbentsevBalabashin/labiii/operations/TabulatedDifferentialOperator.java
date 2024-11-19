package ru.ssau.tk.BerbentsevBalabashin.labiii.operations;
import ru.ssau.tk.BerbentsevBalabashin.labiii.concurrent.SynchronizedTabulatedFunction;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.Point;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.TabulatedFunction;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.factory.TabulatedFunctionFactory;
public class TabulatedDifferentialOperator implements DifferentialOperator<TabulatedFunction> {
    private TabulatedFunctionFactory factory;

    public TabulatedDifferentialOperator(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }
    public TabulatedDifferentialOperator(){
        this.factory = new ArrayTabulatedFunctionFactory();
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

        int n = points.length;
        double[] xValues = new double[n];
        double[] yValues = new double[n];

        for (int k = 0; k < n - 1; k++) {
            xValues[k] = points[k].x;
            yValues[k] = (points[k + 1].y - points[k].y) / (points[k + 1].x - points[k].x);
        }
        xValues[n - 1] = points[n - 1].x;
        yValues[n - 1] = yValues[n - 2];

        return factory.create(xValues, yValues);
    }

    public TabulatedFunction deriveSynchronously(TabulatedFunction function){
        SynchronizedTabulatedFunction synchronizedTabulatedFunction;
        if (function instanceof SynchronizedTabulatedFunction){
            synchronizedTabulatedFunction=(SynchronizedTabulatedFunction) function;
        }
        else { synchronizedTabulatedFunction = new SynchronizedTabulatedFunction(function);
        }
        return synchronizedTabulatedFunction.doSynchronously(this::derive);
    }

}