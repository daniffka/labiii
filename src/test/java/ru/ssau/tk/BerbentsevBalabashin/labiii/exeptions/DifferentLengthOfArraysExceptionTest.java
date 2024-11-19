package ru.ssau.tk.BerbentsevBalabashin.labiii.exeptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DifferentLengthOfArraysExceptionTest {
    @Test
    void testDefaultConstructor(){
        DifferentLengthOfArraysException differentLengthOfArraysException=new DifferentLengthOfArraysException();
        assertNull(differentLengthOfArraysException.getMessage(),"message must be null");
    }

    void testConstructorMessage(){
        String message="different arrays lengths";
        DifferentLengthOfArraysException differentLengthOfArraysException=new DifferentLengthOfArraysException(message);
        assertEquals(message, differentLengthOfArraysException.getMessage(),"exception message different");
    }
}
