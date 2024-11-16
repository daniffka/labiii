package ru.ssau.tk.BerbentsevBalabashin.labiii.io;

import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.ArrayTabulatedFunction;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.TabulatedFunction;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

public class TabulatedFunctionFileWriter {
    public static void main(String[] args) {

        try (BufferedWriter arrayFileWriter = new BufferedWriter(
                new FileWriter(Paths.get("output/array function.txt").toAbsolutePath().toString())
        );

             BufferedWriter listFileWriter = new BufferedWriter(
                     new FileWriter(Paths.get("output/linked list function.txt").toAbsolutePath().toString())
             )
        )
        {
            TabulatedFunction arrayFunction = new ArrayTabulatedFunction(new double[]{}, new double[]{});
            TabulatedFunction linkedListFunction = new LinkedListTabulatedFunction(new double[]{}, new double[]{});

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}