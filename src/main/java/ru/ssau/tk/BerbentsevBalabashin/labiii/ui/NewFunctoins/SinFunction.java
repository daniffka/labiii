package ru.ssau.tk.BerbentsevBalabashin.labiii.ui.NewFunctoins;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.MathFunction;
public class SinFunction implements MathFunction {
    private final double constant;
    public SinFunction(){
        this.constant = 1;
    }
    @Override
    public double apply(double x) {
        return constant*Math.sin(x);
    }
}
