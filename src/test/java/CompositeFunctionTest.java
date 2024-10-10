import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.ssau.tk.BerbentsevBalabashin.labiii.IdentityFunction;
import ru.ssau.tk.BerbentsevBalabashin.labiii.MathFunction;
import ru.ssau.tk.BerbentsevBalabashin.labiii.SqrFunction;
import ru.ssau.tk.BerbentsevBalabashin.labiii.CompositeFunction;
class CompositeFunctionTest {
    @Test
    void testApply(){
        MathFunction Func1 = new IdentityFunction();
        MathFunction Func2 = new SqrFunction();
        CompositeFunction compositeFunction1 = new CompositeFunction(Func1, Func2);
        CompositeFunction compositeFunction2 = new CompositeFunction(Func2, Func2);
        CompositeFunction doubleCompositeFunction = new CompositeFunction(compositeFunction1, Func2);
        Assertions.assertEquals(4096, doubleCompositeFunction.apply(8));
        Assertions.assertEquals(16, compositeFunction2.apply(2));

    }
}