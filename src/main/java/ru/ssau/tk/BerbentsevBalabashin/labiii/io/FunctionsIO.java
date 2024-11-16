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

    public static void writeTabulatedFunction(BufferedWriter writer, TabulatedFunction function){
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

    public static TabulatedFunction readTabulatedFunction(BufferedReader reader, TabulatedFunctionFactory factory) throws IOException{
        String line = reader.readLine();
        int count = Integer.parseInt(line.trim());
        double[] xValues = new double[count];
        double[] yValues = new double[count];

        NumberFormat numberFormat = NumberFormat.getInstance(Locale.forLanguageTag("ru"));

        for (int i = 0; i<count; i++){
            try{
                line = reader.readLine();
                String[] parts = line.split(" ");
                xValues[i] = numberFormat.parse(parts[0]).doubleValue();
                yValues[i] = numberFormat.parse(parts[0]).doubleValue();

            } catch (ParseException exception){
                throw new IOException(exception);
            }
        }
        return factory.create(xValues,yValues);
    }

    public static TabulatedFunction readTabulatedFunction(BufferedInputStream inputStream, TabulatedFunctionFactory factory) throws IOException {
        try (DataInputStream dis = new DataInputStream(inputStream)) {
            int size = dis.readInt();
            double[] xValues = new double[size];
            double[] yValues = new double[size];
            for (int i = 0; i < size; i++) {
                xValues[i] = dis.readDouble();
                yValues[i] = dis.readDouble();
            }

            return factory.create(xValues, yValues);
        }

    }
}