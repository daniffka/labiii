package ru.ssau.tk.BerbentsevBalabashin.labiii.functions;

public class RungeCuttaMethod {
    private final MathFunction function;

    public RungeCuttaMethod(MathFunction function) {
        this.function = function;
    }

    public double integrate(double x0, double y0, double x, int n) {
        double h = (x - x0) / n;
        double result = y0;

        for (int i = 0; i < n; i++) {
            double k1 = h * function.apply(x0);
            double k2 = h * function.apply(x0 + h / 2);
            double k3 = h * function.apply(x0 + h / 2);
            double k4 = h * function.apply(x0 + h);

            result += (k1 + 2 * k2 + 2 * k3 + k4) / 6;
            x0 += h;
        }
        return result;
    }
}
