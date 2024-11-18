package ru.ssau.tk.BerbentsevBalabashin.labiii.concurrent;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.TabulatedFunction;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.ConstantFunction;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.LinkedListTabulatedFunction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ReadWriteTaskExecutorTest {
    private TabulatedFunction tabulatedFunction;
    private Thread readThread;
    private Thread writeThread;
    @BeforeEach
    void setUp() {
        tabulatedFunction = new LinkedListTabulatedFunction(new ConstantFunction(-1), 1, 10, 10);
        readThread = new Thread(new ReadTask(tabulatedFunction));
        writeThread = new Thread(new WriteTask(tabulatedFunction, 0.5));
    }
    @Test
    void testReadWriteExecution() throws InterruptedException {
        writeThread.start();
        readThread.start();

        writeThread.join();
        readThread.join();
        for (int i = 0; i < tabulatedFunction.getCount(); i++) {
            assertEquals(0.5, tabulatedFunction.getY(i), 0.001);
        }
    }

    @Test
    void testReadAfterWrite() throws InterruptedException {
        writeThread.start();
        writeThread.join();

        readThread.start();
        readThread.join();
        for (int i = 0; i < tabulatedFunction.getCount(); i++) {
            assertEquals(0.5, tabulatedFunction.getY(i), 0.001);
        }
    }
    @Test
    void testConcurrentReadAndWrite() throws InterruptedException {
        readThread.start();
        writeThread.start();

        readThread.join();
        writeThread.join();
        for (int i = 0; i < tabulatedFunction.getCount(); i++) {
            double value = tabulatedFunction.getY(i);
            assertTrue(value == 0.5 || value == -1, "Непредвиденное значение: " + value);
        }
    }

}
