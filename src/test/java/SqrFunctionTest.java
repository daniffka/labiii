import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.ssau.tk.BerbentsevBalabashin.labiii.SqrFunction;

class SqrFunctionTest {
    SqrFunction Sqr;
    @Test
    void test(){
        Sqr=new SqrFunction();
        Assertions.assertEquals(100,Sqr.apply(10));
        Assertions.assertEquals(121,Sqr.apply(11));
        Assertions.assertEquals(4,Sqr.apply(2));

    }
}
