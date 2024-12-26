package ru.ssau.tk.BerbentsevBalabashin.labiii.ui.NewFunctoins;
import java.lang.Math;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.MathFunction;

public class CosFunction implements MathFunction {
    private final double constant;
    public CosFunction(){
        this.constant = 1;
    }
    @Override
    public double apply(double x) {
        return constant*Math.cos(x);
    }
}
