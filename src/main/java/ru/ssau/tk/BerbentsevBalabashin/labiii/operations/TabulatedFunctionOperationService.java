package ru.ssau.tk.BerbentsevBalabashin.labiii.operations;

import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.Point;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.TabulatedFunction;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.factory.TabulatedFunctionFactory;

public class TabulatedFunctionOperationService {

    private TabulatedFunctionFactory factory;

    public TabulatedFunctionOperationService(TabulatedFunctionFactory factory) {

        this.factory = factory;
    }

    public TabulatedFunctionOperationService() {

        this.factory = new ArrayTabulatedFunctionFactory();
    }

    public TabulatedFunctionFactory getFactory() {
        return factory;
    }

    public void setFactory(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public static Point[] asPoints(TabulatedFunction tabulatedFunction) {

        Point[] points = new Point[tabulatedFunction.getCount()];
        int i = 0;
        for (Point point : tabulatedFunction) {
            points[i] = point;
            i++;
        }
        return points;
    }
}
