package ru.ssau.tk.BerbentsevBalabashin.labiii.concurrent;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.TabulatedFunction;
public class ReadTask implements Runnable {
    private final TabulatedFunction func;
    public ReadTask(TabulatedFunction function) {
        this.func = function;
    }
    public void run() {
        for(int i = 0; i < func.getCount(); ++i){
            System.out.printf("After read: i = %d, x = %f, y = %f\n", i, func.getX(i), func.getY(i));
        }
    }
}