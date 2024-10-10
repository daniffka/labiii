package ru.ssau.tk.BerbentsevBalabashin.labiii;
import java.util.Arrays;
// Вычисление сплайна S(x) по алгоритму де Бура.
public class DeBuraAlgoritm implements MathFunction {
    private double[] controlPoints;  // Массив контрольных точек
    private double[] nodes; // Массив узлов
    private int Degree; // Степень сплайна


    public DeBuraAlgoritm(double[] nodes, double[] controlPoints, int Degree) {
        if (nodes.length < Degree + 1 || controlPoints.length < Degree + 1) {
            throw new IllegalArgumentException("Недостаточное количество узлов или контрольных точек.");
        }
        this.controlPoints = Arrays.copyOf(controlPoints, controlPoints.length);
        this.nodes = Arrays.copyOf(nodes, nodes.length);
        this.Degree = Degree;
    }

    // Алгоритм де Бура
    private double algorithm(double x) {
        int segmentIndex = findSegment(x); // Найдём сегмент по значению x
        double[] d = new double[Degree + 1]; // Массив значений d

        // Инициализируем значения d на основе контрольных точек
        for (int i = 0; i <= Degree; ++i) {
            d[i] = controlPoints[segmentIndex - Degree + i];
        }

        // Применяем алгоритм де Бура
        //1, 2, 3
        for (int r = 1; r <= Degree; ++r) {
            for (int j = Degree; j >= r; --j) {
                int knotIndex = segmentIndex + j - Degree;
                double denominator = nodes[knotIndex + Degree - r + 1] - nodes[knotIndex];
                if (denominator == 0) {
                    throw new ArithmeticException("Деление на ноль: найдены одинаковые узлы.");
                }
                double alpha = (x - nodes[knotIndex]) / denominator;
                d[j] = (1 - alpha) * d[j - 1] + alpha * d[j];
            }
        }

        return d[Degree];
    }

    // Поиск индекса сегмента, содержащего x
    private int findSegment(double x) {
        int n = nodes.length - 1;
        if (x < nodes[Degree] || x > nodes[n - Degree]) {
            throw new IllegalArgumentException("Значение x выходит за допустимые границы узлов.");
        }
        for (int i = Degree; i < n - Degree; ++i) {
            if (x >= nodes[i] && x < nodes[i + 1]) {
                return i;
            }
        }
        return n - Degree - 1; // В случае, если x совпадает с последним узлом
    }

    // Вычисление значения сплайна в точке x
    @Override
    public double apply(double x) {
        return algorithm(x); // Возвращаем значение сплайна S(x)
    }
}