package ru.ssau.tk.BerbentsevBalabashin.labiii.concurrent;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.TabulatedFunction;

public class WriteTask implements Runnable {
    private final TabulatedFunction func;
    private final double value;
    public WriteTask(TabulatedFunction function, double value) {
        this.func = function;
        this.value = value;
    }
    @Override
    public void run() {
        for (int i = 0; i < func.getCount(); i++)
        {
            synchronized (func) {
                func.setY(i, value);
                System.out.printf("Writing for index %d complete\n", i);
            }
        }
    }
}