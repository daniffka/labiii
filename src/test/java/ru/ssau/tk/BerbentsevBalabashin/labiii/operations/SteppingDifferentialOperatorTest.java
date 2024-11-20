package ru.ssau.tk.BerbentsevBalabashin.labiii.operations;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.MathFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SteppingDifferentialOperatorTest {

    private static class TestForSteppingDifferentOperator extends SteppingDifferentialOperator {
        public TestForSteppingDifferentOperator(double step){
            super(step);
        }

        @Override
        public MathFunction derive(MathFunction function){
            return null;
        }
    }

    @Test
    public void testConstructor(){
        TestForSteppingDifferentOperator steppingDifferentialOperator = new TestForSteppingDifferentOperator(12);
        assertNotNull(steppingDifferentialOperator);
        assertEquals(12,steppingDifferentialOperator.getStep());
    }

    @Test
    public void testSetStep(){
        TestForSteppingDifferentOperator testForSteppingDifferentOperator = new TestForSteppingDifferentOperator(4444);
        testForSteppingDifferentOperator.setStep(12);
        assertEquals(12,testForSteppingDifferentOperator.getStep());
    }

    @Test
    public void testGetStep(){
        TestForSteppingDifferentOperator testForSteppingDifferentOperator = new TestForSteppingDifferentOperator(12);
        assertEquals(12,testForSteppingDifferentOperator.getStep());
    }
}
