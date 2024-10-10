import org.junit.jupiter.api.Test;
import ru.ssau.tk.BerbentsevBalabashin.labiii.ZeroFunction;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ZeroFunctionTest {
    @Test
    public void validZero(){
        ZeroFunction ZeroFunction = new ZeroFunction();
        assertEquals(0,ZeroFunction.apply(28));
    }
}
