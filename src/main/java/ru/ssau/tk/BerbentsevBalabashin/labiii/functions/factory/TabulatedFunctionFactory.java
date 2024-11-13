package ru.ssau.tk.BerbentsevBalabashin.labiii.functions.factory;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.TabulatedFunction;

public interface TabulatedFunctionFactory {

    TabulatedFunction create(double[] xValues, double[] yValues);
}