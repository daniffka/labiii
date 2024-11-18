package ru.ssau.tk.BerbentsevBalabashin.labiii.concurrent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.TabulatedFunction;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.ArrayTabulatedFunction;
import static org.junit.jupiter.api.Assertions.*;

class WriteTaskTest {
    private TabulatedFunction tabulatedFunction;
    private Thread writeThread;
    @BeforeEach
    void setUp() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 4.0, 6.0};
        tabulatedFunction = new ArrayTabulatedFunction(xValues, yValues);
        writeThread = new Thread(new WriteTask(tabulatedFunction, 10.0));
    }
    @Test
    void testWriteTask() throws InterruptedException {
        writeThread.start();
        writeThread.join();
        assertEquals(10.0, tabulatedFunction.getY(0));
        assertEquals(10.0, tabulatedFunction.getY(1));
        assertEquals(10.0, tabulatedFunction.getY(2));
    }
    @Test
    void testWriteTaskConcurrency() throws InterruptedException {
        Thread writeThread2 = new Thread(new WriteTask(tabulatedFunction, 20.0));
        writeThread.start();
        writeThread2.start();
        writeThread.join();
        writeThread2.join();
        assertTrue(tabulatedFunction.getY(0) == 10.0 || tabulatedFunction.getY(0) == 20.0);
        assertTrue(tabulatedFunction.getY(1) == 10.0 || tabulatedFunction.getY(1) == 20.0);
        assertTrue(tabulatedFunction.getY(2) == 10.0 || tabulatedFunction.getY(2) == 20.0);
    }
}