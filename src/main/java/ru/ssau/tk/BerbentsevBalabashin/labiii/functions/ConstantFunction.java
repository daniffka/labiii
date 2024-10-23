package ru.ssau.tk.BerbentsevBalabashin.labiii.functions;

public class ConstantFunction implements MathFunction {

    @Override
    public double apply(double x) {

        return constant;
    }

    public ConstantFunction(double constant){

        this.constant=constant;
    }

    private final double constant;
    public double GetConstant(){
        return constant;
    }
}

