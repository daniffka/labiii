package ru.ssau.tk.BerbentsevBalabashin.labiii.io;

import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.Point;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.TabulatedFunction;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.factory.TabulatedFunctionFactory;
import java.io.*;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public final class FunctionsIO {
    private FunctionsIO() {
        throw new UnsupportedOperationException("Class is final");
    }

    void writeTabulatedFunction(BufferedWriter writer, TabulatedFunction function){
        try(PrintWriter printWriter = new PrintWriter(writer)){
            printWriter.println(function.getCount());
            for (Point point: function){
                printWriter.printf("%f %f\n",point.getX(),point.getY());
            }
            writer.flush();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static void writeTabulatedFunction(BufferedOutputStream outputStream, TabulatedFunction function) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(outputStream)) {
            dos.writeInt(function.getCount());
            for (Point point : function){
                dos.writeDouble(point.getX());
                dos.writeDouble(point.getY());
            }
            dos.flush();
        }
    }

}