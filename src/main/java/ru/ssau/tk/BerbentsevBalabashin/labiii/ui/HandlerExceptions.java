package ru.ssau.tk.BerbentsevBalabashin.labiii.ui;


import javax.swing.JOptionPane;

public class HandlerExceptions {

    public static int getPointCount(String input) {
        int pointCount;
        try {
            pointCount = Integer.parseInt(input);
            if (pointCount <= 1) {
                throw new IllegalArgumentException("\n" +
                        "The number of points should be more 1!");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Please enter a valid value!");
        }

        return pointCount;
    }

    public static void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}