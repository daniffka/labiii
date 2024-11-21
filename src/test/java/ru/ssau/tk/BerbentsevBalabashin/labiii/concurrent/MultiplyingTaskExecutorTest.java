package ru.ssau.tk.BerbentsevBalabashin.labiii.concurrent;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.TabulatedFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MultiplyingTaskExecutorTest {
    private MultiplyingTaskExecutor multiplyingTaskExecutor;
    private TabulatedFunction tabulatedFunction;

    @Test
    public void testMultiplyingTaskExecutor() throws InterruptedException{

        double multiplier = 2;
        tabulatedFunction = new LinkedListTabulatedFunction(new double[] {1,2,3,4,5},new double[]{1,2,3,4,5});
        multiplyingTaskExecutor = new MultiplyingTaskExecutor();

        for(int i =0; i<tabulatedFunction.getCount();i++){
            tabulatedFunction.setY(i,tabulatedFunction.getY(i)*multiplier);
        }
        multiplyingTaskExecutor.main(new String[] {});

        for (int i = 0; i < tabulatedFunction.getCount(); i++){
            assertEquals(tabulatedFunction.getY(i),
                    tabulatedFunction.getX(i)*2);
        }
    }
}
