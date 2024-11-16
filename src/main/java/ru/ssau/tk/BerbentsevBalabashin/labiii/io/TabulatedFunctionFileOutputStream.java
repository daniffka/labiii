package ru.ssau.tk.BerbentsevBalabashin.labiii.io;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.TabulatedFunction;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.ArrayTabulatedFunction;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.LinkedListTabulatedFunction;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TabulatedFunctionFileOutputStream {

    public static void main(String[] args) {
        try (
                BufferedOutputStream arrayOutputStream = new BufferedOutputStream(new FileOutputStream("output/array_function.bin"));
                BufferedOutputStream linkedListOutputStream = new BufferedOutputStream(new FileOutputStream("output/linked_list_function.bin"))
        ) {
            TabulatedFunction arrayFunction = new ArrayTabulatedFunction(new double[]{0.0, 0.5, 1.0}, new double[]{0.0, 0.25, 1.0});
            TabulatedFunction linkedListFunction = new LinkedListTabulatedFunction(new double[]{0.0, 0.5, 1.0}, new double[]{0.0, 0.25, 1.0});
            FunctionsIO.writeTabulatedFunction(arrayOutputStream, arrayFunction);
            FunctionsIO.writeTabulatedFunction(linkedListOutputStream, linkedListFunction);
        } catch (IOException ex) {
            ex.printStackTrace(); // Обработка исключения
        }
    }
}
