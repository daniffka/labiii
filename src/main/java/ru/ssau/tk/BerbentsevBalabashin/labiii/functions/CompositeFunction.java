package ru.ssau.tk.BerbentsevBalabashin.labiii.functions;

public class CompositeFunction implements MathFunction {
    private final MathFunction firstFunc;
    private final MathFunction secondFunc;

    public CompositeFunction(MathFunction firstFunc, MathFunction secondFunc){
        this.firstFunc = firstFunc;
        this.secondFunc = secondFunc;
    }
    @Override
    public double apply(double x) {
        return secondFunc.apply(firstFunc.apply(x));
    }
}
