package ru.ssau.tk.BerbentsevBalabashin.labiii.exeptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class ArrayIsNotSortedExceptionTest {
    @Test
    void testDefaultConstructor(){
        ArrayIsNotSortedException arrayIsNotSortedException =new ArrayIsNotSortedException();
        assertNull(arrayIsNotSortedException.getMessage(), "message must be null");
    }

    @Test
    void testConstructorMessage() {
        String message = "array is not sorted";
        ArrayIsNotSortedException arrayIsNotSortedException = new ArrayIsNotSortedException(message);
        assertEquals(message, arrayIsNotSortedException.getMessage(), "exception message different");
    }
}
