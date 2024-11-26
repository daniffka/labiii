package ru.ssau.tk.BerbentsevBalabashin.labiii.dto;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MathFunctionDTOTest {

    @Test
    public void testMathFunctionDTO() {
        MathFunctionDTO dto = new MathFunctionDTO(1, "Linear", 2, 0.0, 10.0, null);

        assertEquals(1, dto.getId());
        assertEquals("Linear", dto.getNameOfFunction());
        assertEquals(2, dto.getCount());
        assertEquals(0.0, dto.getXFrom());
        assertEquals(10.0, dto.getXTo());
    }

}