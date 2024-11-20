package ru.ssau.tk.BerbentsevBalabashin.labiii.concurrent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.ArrayTabulatedFunction;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.TabulatedFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MultiplyingTaskTest {
    private TabulatedFunction function;
    @BeforeEach
    public void setUp(){
        double[] xVal={0.0,1.0,2.0,3.0};
        double[] yVal={0.0,1.0,2.0,3.0};
        function=new ArrayTabulatedFunction(xVal,yVal);
    }

    @Test
    public void testMultiplyingTask() throws  InterruptedException{
        MultiplyingTask task = new MultiplyingTask(function);
        Thread thread = new Thread(task);
        thread.start();
        thread.join();

        for(int i=0; i<function.getCount();i++){
            assertEquals(i*2, function.getY(i),0.00001);
        }
    }

}
