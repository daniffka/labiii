package ru.ssau.tk.BerbentsevBalabashin.labiii.operations;

import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.MathFunction;

public abstract class SteppingDifferentialOperator implements DifferentialOperator<MathFunction> {
    protected double step;

    public SteppingDifferentialOperator(double step){
        if(step<=0 || Double.isInfinite(step) || Double.isNaN(step)) throw new IllegalArgumentException("step must be positive value");
        this.step=step;
    }
    public double getStep() {
        return step;
    }

    public void setStep(double step) {
        this.step = step;
    }

}
