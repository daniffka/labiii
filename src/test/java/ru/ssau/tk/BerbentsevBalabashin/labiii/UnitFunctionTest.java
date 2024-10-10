package ru.ssau.tk.BerbentsevBalabashin.labiii;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UnitFunctionTest {
    @Test
    public void validUnit(){
        UnitFunction unitFunction = new UnitFunction();
        assertEquals(1,unitFunction.apply(33));
    }
}
