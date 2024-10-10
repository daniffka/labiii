package ru.ssau.tk.BerbentsevBalabashin.labiii;
import java.util.Arrays;


// Вычисление сплайна S(x) по алгоритму де Бура.
public class DeBuraAlgoritm implements MathFunction {
    private double[] nodeArray; // Массив узлов
    private double[] control_Points; // Массив контрольных точек
    private int splineDegree; // Степень сплайна

    public DeBuraAlgoritm(double[] nodeArray, double[] controlPoints, int splineDegree) {
        if (nodeArray.length < splineDegree + 1 || controlPoints.length < splineDegree + 1) {
            throw new IllegalArgumentException("Недостаточное количество узлов или контрольных точек.");
        }
        this.nodeArray = Arrays.copyOf(nodeArray, nodeArray.length);
        this.control_Points = Arrays.copyOf(controlPoints, controlPoints.length);
        this.splineDegree = splineDegree;
    }

    // Алгоритм де Бура
    private double algorithm(double x) {
        int segmentIndex = findSegment(x); // Найдём сегмент по значению x
        double[] d = new double[splineDegree + 1]; // Массив значений d
        // Инициализируем значения d на основе контрольных точек
        for (int i = 0; i <= splineDegree; ++i) {
            d[i] = control_Points[segmentIndex - splineDegree + i];
        }

        // Применяем алгоритм де Бура
        for (int r = 1; r <= splineDegree; ++r) {
            for (int j = splineDegree; j >= r; --j) {
                int knotIndex = segmentIndex + j - splineDegree;
                double denominator = nodeArray[knotIndex + splineDegree - r + 1] - nodeArray[knotIndex];
                if (denominator == 0) {
                    throw new ArithmeticException("Деление на ноль: найдены одинаковые узлы.");
                }
                double alpha = (x - nodeArray[knotIndex]) / denominator;
                d[j] = (1 - alpha) * d[j - 1] + alpha * d[j];
            }
        }
        return d[splineDegree];
    }
    // Поиск индекса сегмента, содержащего x
    private int findSegment(double x) {
        int n = nodeArray.length - 1;
        for (int i = splineDegree; i < n - splineDegree; ++i) {
            if (x >= nodeArray[i] && x < nodeArray[i + 1]) {
                return i;
            }
        }
        return n - splineDegree - 1; // В случае, если x совпадает с последним узлом
    }
    // Вычисляем значение сплайна в точке x
    @Override
    public double apply(double x) {
        return algorithm(x); // Возвращаем значение сплайна S(x)
    }
}