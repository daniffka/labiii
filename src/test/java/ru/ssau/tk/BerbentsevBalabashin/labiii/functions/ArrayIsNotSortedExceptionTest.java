package ru.ssau.tk.BerbentsevBalabashin.labiii.functions;
import org.junit.jupiter.api.Test;
import ru.ssau.tk.BerbentsevBalabashin.labiii.exeptions.ArrayIsNotSortedException;

import static org.junit.jupiter.api.Assertions.*;
public class ArrayIsNotSortedExceptionTest {
    @Test
    public void testDefaultConst(){
        ArrayIsNotSortedException exception=new ArrayIsNotSortedException();
        assertNull(exception.getMessage(), "messange must by null");
    }
    @Test
    public void testMessange(){
        String messange = "Массив не отсортирован";
        ArrayIsNotSortedException exception=new ArrayIsNotSortedException(messange);
        assertEquals(messange,exception.getMessage(), "messange is different from expected");
    }
}
