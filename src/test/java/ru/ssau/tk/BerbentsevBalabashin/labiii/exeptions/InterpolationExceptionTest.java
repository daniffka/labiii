package ru.ssau.tk.BerbentsevBalabashin.labiii.exeptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class InterpolationExceptionTest {
    @Test
    void testDefaultConstructor() {
        InterpolationException interpolationException= new InterpolationException();
        assertNull(interpolationException.getMessage(),"message must be null");
    }

    @Test
    void testConstructorMessage(){
        String message="interpolation error";
        InterpolationException interpolationException= new InterpolationException(message);
        assertEquals(message,interpolationException.getMessage(),"exception message different");
    }
}
