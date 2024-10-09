package ru.ssau.tk.BerbentsevBalabashin.labiii;

public class SqrFunction implements MathFunction {
    @Override
    public double apply(double x) {
        return Math.pow(x,2);
    }
}
