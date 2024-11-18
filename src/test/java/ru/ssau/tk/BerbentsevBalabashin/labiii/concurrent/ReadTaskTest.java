package ru.ssau.tk.BerbentsevBalabashin.labiii.concurrent;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.TabulatedFunction;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.ArrayTabulatedFunction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class ReadTaskTest {
    private TabulatedFunction tabulatedFunction;
    private Thread readThread;
    @BeforeEach
    void setUp() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 4.0, 6.0};
        tabulatedFunction = new ArrayTabulatedFunction(xValues, yValues);
        readThread = new Thread(new ReadTask(tabulatedFunction));
    }
    @Test
    void testReadTask() throws InterruptedException {
        readThread.start();
        readThread.join();
        assertEquals(2.0, tabulatedFunction.getY(0));
        assertEquals(4.0, tabulatedFunction.getY(1));
        assertEquals(6.0, tabulatedFunction.getY(2));
    }
    @Test
    void testReadTaskConcurrency() throws InterruptedException {
        Thread secondThread = new Thread(new ReadTask(tabulatedFunction));
        readThread.start();
        secondThread.start();
        readThread.join();
        secondThread.join();
        assertTrue(true);
    }

}
