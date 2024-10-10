import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.ssau.tk.BerbentsevBalabashin.labiii.IdentityFunction;
class IdentityFunctionTest {
    @Test
    void testApply() {
        IdentityFunction identityFunction = new IdentityFunction();
        Assertions.assertEquals(1, identityFunction.apply(1));
        Assertions.assertEquals(0.05, identityFunction.apply(0.05));
    }
}