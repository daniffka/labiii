package ru.ssau.tk.BerbentsevBalabashin.labiii;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RungeCuttaMethodTest {

    @Test
    void linearFunction() {

        MathFunction function = new MathFunction() {
            @Override
            public double apply(double x) {
                return x;
            }
        };
        RungeCuttaMethod RungeCuttaMethod = new RungeCuttaMethod(function);
        double x0 = 0;
        double y0 = 0;
        double x = 1;
        int n = 10;

        double result = RungeCuttaMethod.integrate(x0, y0, x, n);

        assertEquals(0.5, result, 0.01);
    }

    @Test
    void forQuadraticFunction() {
        MathFunction function = new MathFunction() {
            @Override
            public double apply(double x) {
                return x * x;
            }
        };
        RungeCuttaMethod RungeCuttaMethod = new RungeCuttaMethod(function);
        double x0 = 0;
        double y0 = 0;
        double x = 1;
        int n = 10;

        double result = RungeCuttaMethod.integrate(x0, y0, x, n);

        assertEquals(1.0 / 3.0, result, 0.01);
    }

    @Test
    void forNegativeFunction() {

        MathFunction function = new MathFunction() {
            @Override
            public double apply(double x) {
                return -x;
            }
        };
        RungeCuttaMethod RungeCuttaMethod = new RungeCuttaMethod(function);
        double x0 = 0;
        double y0 = 0;
        double x = 1;
        int n = 10;

        double result = RungeCuttaMethod.integrate(x0, y0, x, n);

        assertEquals(-0.5, result, 0.01);
    }

    @Test
    void forZeroSteps() {
        MathFunction function = new MathFunction() {
            @Override
            public double apply(double x) {
                return x;
            }
        };
        RungeCuttaMethod RungeCuttaMethod = new RungeCuttaMethod(function);
        double x0 = 0;
        double y0 = 5;
        double x = 1;
        int n = 0;

        double result = RungeCuttaMethod.integrate(x0, y0, x, n);

        assertEquals(y0, result);
    }

    @Test
    void forSingleStep() {
        MathFunction function = new MathFunction() {
            @Override
            public double apply(double x) {
                return x;
            }
        };
        RungeCuttaMethod RungeCuttaMethod = new RungeCuttaMethod(function);
        double x0 = 0;
        double y0 = 0;
        double x = 1;
        int n = 1;

        double result = RungeCuttaMethod.integrate(x0, y0, x, n);

        assertEquals(0.5, result, 0.01);
    }
}