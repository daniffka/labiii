package ru.ssau.tk.BerbentsevBalabashin.labiii.io;

import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.TabulatedFunction;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.factory.TabulatedFunctionFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TabulatedFunctionFileReader {
    public static void main(String[] args) {
        Path path = Paths.get("input","function.txt");

        try (BufferedReader arrayFileReader = new BufferedReader(
                new FileReader(path.toAbsolutePath().toString())
        );
             BufferedReader ListReader = new BufferedReader(
                     new FileReader(path.toAbsolutePath().toString())
             )
        ){
            TabulatedFunctionFactory arrayFactory = new ArrayTabulatedFunctionFactory();
            TabulatedFunctionFactory ListFactory = new LinkedListTabulatedFunctionFactory();
            TabulatedFunction arrayFunction = FunctionsIO.readTabulatedFunction(arrayFileReader,arrayFactory);
            TabulatedFunction ListFunction = FunctionsIO.readTabulatedFunction(ListReader,arrayFactory);

            System.out.println(arrayFunction.toString());
            System.out.println(ListFunction.toString());

        } catch (IOException exception){
            exception.printStackTrace();
        }
    }

}


