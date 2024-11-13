package ru.ssau.tk.BerbentsevBalabashin.labiii.operations;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.MathFunction;

public interface DifferentialOperator<T> extends MathFunction {
    T derive(T function);
}