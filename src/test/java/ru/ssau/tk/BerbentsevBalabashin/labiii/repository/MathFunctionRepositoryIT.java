package ru.ssau.tk.BerbentsevBalabashin.labiii.repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import ru.ssau.tk.BerbentsevBalabashin.labiii.entity.MathFunctionEntity;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Sql(scripts = {"/sql/test_schema.sql", "/sql/math_functions.sql"})
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MathFunctionRepositoryIT {

    @Autowired
    private MathFunctionRepository mathFunctionRepository;

    @BeforeEach
    public void setup() {
        MathFunctionEntity function = new MathFunctionEntity();
        function.setFunctionType("Linear");
        this.mathFunctionRepository.save(function);
    }

    @Test
    public void testFindByFunctionType_ReturnsFunctionList() {
        List<MathFunctionEntity> functions = this.mathFunctionRepository.findByFunctionType("Linear");
        assertEquals(1, functions.size());
        assertEquals("Linear", functions.get(0).getFunctionType());
    }
}
