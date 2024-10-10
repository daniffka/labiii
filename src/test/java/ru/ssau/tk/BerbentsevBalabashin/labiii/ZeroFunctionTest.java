package ru.ssau.tk.BerbentsevBalabashin.labiii;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ZeroFunctionTest {

    @Test
    public void validZero(){
        ZeroFunction ZeroFunction = new ZeroFunction();
        assertEquals(0,ZeroFunction.apply(28));
    }
}
