import org.junit.jupiter.api.Test;
import ru.ssau.tk.BerbentsevBalabashin.labiii.ConstantFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConstantFunctionTest {

    @Test
    public void returnValidConstant(){
        ConstantFunction constantFunction = new ConstantFunction(33);
        assertEquals(33,constantFunction.apply(22));
    }

    @Test
    public void getValidConstant(){
        ConstantFunction constantFunction = new ConstantFunction(99);
        assertEquals(99,constantFunction.GetConstant());
    }
}