package ru.ssau.tk.BerbentsevBalabashin.labiii.operations;

import ru.ssau.tk.BerbentsevBalabashin.labiii.exeptions.InconsistentFunctionsException;
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

    private interface BiOperation{
        double apply(double u, double v);

    }

    private TabulatedFunction doOperation(TabulatedFunction a, TabulatedFunction b, BiOperation operation){
        if (a.getCount()!=b.getCount()) throw new InconsistentFunctionsException("arrays must have identical length");

        Point[] pointA =asPoints(a);
        Point[] pointB =asPoints(b);
        double[] xValues=new double[pointA.length];
        double[] yValues=new double[pointB.length];

        for(int i=0; i<pointA.length; i++) {
            if (pointA[i].getX() != pointB[i].getX())
                throw new InconsistentFunctionsException("values must be the identical");

            xValues[i] = pointA[i].getX();
            yValues[i] = operation.apply(pointA[i].getY(),pointB[i].getY());
        }
    return factory.create(xValues,yValues);
    }

    public TabulatedFunction sum(TabulatedFunction a, TabulatedFunction b) throws InconsistentFunctionsException{
        return doOperation(a,b,((u,v)-> u+v));

    }

    public TabulatedFunction diff(TabulatedFunction a, TabulatedFunction b) throws InconsistentFunctionsException{
        return doOperation(a,b,((u,v)-> u-v));

    }

    public TabulatedFunction mult(TabulatedFunction a, TabulatedFunction b) throws InconsistentFunctionsException{
        return doOperation(a,b,((u, v) -> u * v));
    }

    public TabulatedFunction div(TabulatedFunction a, TabulatedFunction b) throws InconsistentFunctionsException{
        return doOperation(a,b,((u, v) -> u / v));
    }

}
