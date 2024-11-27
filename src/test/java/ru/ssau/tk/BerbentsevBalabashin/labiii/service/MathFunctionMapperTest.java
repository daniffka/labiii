package ru.ssau.tk.BerbentsevBalabashin.labiii.service;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import ru.ssau.tk.BerbentsevBalabashin.labiii.entity.MathFunctionEntity;
import ru.ssau.tk.BerbentsevBalabashin.labiii.dto.MathFunctionDTO;


class MathFunctionMapperTest {

    @Test
    void testFunctionEntityToDTO() {
        MathFunctionEntity entity = new MathFunctionEntity(1, "linear", 10, 0.0, 10.0, null);
        MathFunctionDTO dto = MathFunctionMapper.functionEntityToDTO(entity);

        assertNotNull(dto);
        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getNameOfFunction(), dto.getNameOfFunction());

        dto = null;
        assertNull(dto);
    }

    @Test
    void testFunctionDTOToFunctionEntity() {
        MathFunctionDTO dto = new MathFunctionDTO(1, "linear", 10, 0.0, 10.0, null);
        MathFunctionEntity entity = MathFunctionMapper.functionDTOToFunctionEntity(dto);

        assertNotNull(entity);
        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getNameOfFunction(), entity.getNameOfFunction());

        entity = null;
        assertNull(entity);
    }

}