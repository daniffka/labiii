package ru.ssau.tk.BerbentsevBalabashin.labiii.concurrent;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.TabulatedFunction;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.ConstantFunction;

public class ReadWriteTaskExecutor {
    public static void main(String[] args) {
        TabulatedFunction linkedListTabulatedFunction =
                new LinkedListTabulatedFunction(new ConstantFunction(-1), 1, 1000, 1000);
        Thread readThread = new Thread(new ReadTask(linkedListTabulatedFunction));
        Thread writeThread = new Thread(new WriteTask(linkedListTabulatedFunction, 0.5));
        readThread.start();
        writeThread.start();
    }
}