package ru.ssau.tk.BerbentsevBalabashin.labiii.functions;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.Point;
import java.util.Iterator;
public interface TabulatedFunction extends MathFunction,Iterable<Point>{
    int getCount();
    double getX(int index);
    double getY(int index);
    void setY(int index, double value);
    int indexOfX(double x);
    int indexOfY(double y);
    double leftBound();
    double rightBound();
    Iterator<Point> iterator();
}