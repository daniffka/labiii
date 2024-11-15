package ru.ssau.tk.BerbentsevBalabashin.labiii.functions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class DeBuraAlgoritmTest {
    @Test
    void apply_1() {
        DeBuraAlgoritm obj = new DeBuraAlgoritm(new double[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, new double[]{0, 1, 4, 9, 16, 25, 36, 49, 64, 81, 100}, 2);
        Assertions.assertEquals(18.74, obj.apply(5.8), 0.0001);
        Assertions.assertEquals(26.2599, obj.apply(6.6), 0.0001);
        Assertions.assertEquals(0.9389, obj.apply(2.33), 0.0001);
        Assertions.assertEquals(42.5, obj.apply(8), 0.0001);
        Assertions.assertEquals(8.2024, obj.apply(4.32), 0.0001);
        //
        Assertions.assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                obj.apply(9);
            }
        });
    }
}