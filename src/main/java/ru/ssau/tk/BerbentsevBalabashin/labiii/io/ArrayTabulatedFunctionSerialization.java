package ru.ssau.tk.BerbentsevBalabashin.labiii.io;

import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.ArrayTabulatedFunction;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.TabulatedFunction;
import ru.ssau.tk.BerbentsevBalabashin.labiii.operations.TabulatedDifferentialOperator;

import java.io.*;

public class ArrayTabulatedFunctionSerialization {
    public static void main(String[] args) {

        String filePath = "output/serialized array functions.bin";

        TabulatedFunction arrayTabulatedFunction = new ArrayTabulatedFunction(new double[]{10.0,2.3,4.44}, new double[]{10,23,444});
        TabulatedDifferentialOperator differentialOperator = new TabulatedDifferentialOperator();
        TabulatedFunction firstDerivative = differentialOperator.derive(arrayTabulatedFunction);
        TabulatedFunction secondDerivative = differentialOperator.derive(firstDerivative);

        try(BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(filePath))){
            FunctionsIO.serialize(bufferedOutputStream,arrayTabulatedFunction);
            FunctionsIO.serialize(bufferedOutputStream,firstDerivative);
            FunctionsIO.serialize(bufferedOutputStream,secondDerivative);
        }
        catch (IOException exception){
            exception.printStackTrace();
        }

        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(filePath))){
            TabulatedFunction deserializedArrayTabulatedFunction = FunctionsIO.deserialize(bufferedInputStream);
            TabulatedFunction deserializedFirstDerivative = FunctionsIO.deserialize(bufferedInputStream);
            TabulatedFunction deserializedSecondDerivative = FunctionsIO.deserialize(bufferedInputStream);

            System.out.println("Сurrent function: "+ deserializedArrayTabulatedFunction.toString());
            System.out.println("First derivative function: "+ deserializedFirstDerivative.toString());
            System.out.println("Second derivative function: "+ deserializedSecondDerivative.toString());
        }
        catch (IOException | ClassNotFoundException exception){
            exception.printStackTrace();
        }
    }

}
