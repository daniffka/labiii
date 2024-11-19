package ru.ssau.tk.BerbentsevBalabashin.labiii.io;

import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.ArrayTabulatedFunction;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.TabulatedFunction;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

public class TabulatedFunctionFileWriter {

    public static void main(String[] args){

        try(BufferedWriter arrayFileWriter = new BufferedWriter(
                new FileWriter(Paths.get("output/array function.txt").toAbsolutePath().toString())
        );

            BufferedWriter lListFileWriter = new BufferedWriter(
                    new FileWriter("output/linked list function.txt")
            )
        ){
            TabulatedFunction arrayFunction = new ArrayTabulatedFunction(
                    new double[]{0.0,0.52,0.69}, new double[]{0.0, 52, 69}
            );
            TabulatedFunction lListFunction = new ArrayTabulatedFunction(
                    new double[]{0.0,0.52,0.69}, new double[]{0.0, 52, 69}
            );

            FunctionsIO.writeTabulatedFunction(arrayFileWriter,arrayFunction);
            FunctionsIO.writeTabulatedFunction(lListFileWriter,lListFunction);
        }
        catch (IOException exception){
            exception.printStackTrace();
        }
    }
}