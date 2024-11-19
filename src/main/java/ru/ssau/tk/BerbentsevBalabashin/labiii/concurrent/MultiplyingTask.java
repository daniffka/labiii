package ru.ssau.tk.BerbentsevBalabashin.labiii.concurrent;

import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.TabulatedFunction;

public class MultiplyingTask implements Runnable{
    private final TabulatedFunction function;

    public MultiplyingTask(TabulatedFunction function){
        this.function=function;
    }

    @Override
    public void run(){
        for(int i=0;i<function.getCount();i++){
            synchronized (function){
                function.setY(i, function.getY(i)*2);
            }
        }
        System.out.printf("Stream %s finished work\n",Thread.currentThread().getName());
    }

}
