package ru.ssau.tk.BerbentsevBalabashin.labiii;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
class DeBuraAlgoritmTest {
    @Test
    void apply1() {
        DeBuraAlgoritm obj = new DeBuraAlgoritm(new double[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, new double[]{0, 1, 4, 9, 16, 25, 36, 49, 64, 81, 100}, 1);
        Assertions.assertEquals(25, obj.apply(6), 0.0001);
        Assertions.assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                obj.apply(10);
            }
        });
    }
}