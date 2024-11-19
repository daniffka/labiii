package ru.ssau.tk.BerbentsevBalabashin.labiii.concurrent;

import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.TabulatedFunction;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.UnitFunction;

import java.util.ArrayList;
import java.util.List;

public class MultiplyingTaskExecutor {
    public static void main(String[] args) throws InterruptedException{
        TabulatedFunction function = new LinkedListTabulatedFunction(new UnitFunction(),1,1000,1000);

        List <Thread> threads= new ArrayList<>();

        for (int i=0;i<10;i++){
            MultiplyingTask multiplyingTask = new MultiplyingTask(function);
                threads.add(new Thread(multiplyingTask));
        }

        for (Thread thread: threads) thread.start();
        Thread.sleep(200);

        System.out.println("function finished");
        for (int i=0;i<function.getCount();i++){
            System.out.printf("x: %f, y: %f%n", function.getX(i),function.getY(i));
        }

    }
}
