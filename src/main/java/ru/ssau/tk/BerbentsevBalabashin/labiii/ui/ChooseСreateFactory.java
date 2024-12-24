package ru.ssau.tk.BerbentsevBalabashin.labiii.ui;
import ru.ssau.tk.BerbentsevBalabashin.labiii.operations.TabulatedFunctionOperationService;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.factory.ArrayTabulatedFunctionFactory;
import javax.swing.*;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ChooseСreateFactory extends JDialog {
    private final int WIDTH_DIALOG = 400;
    private final int HEIGHT_DIALOG = 250;

    public ChooseСreateFactory(JFrame owner, TabulatedFunctionOperationService factoryService) {
        super(owner, "Настройки", true); // Модальное окно
        setSize(WIDTH_DIALOG, HEIGHT_DIALOG);
        setLocationRelativeTo(null);

        JPanel gradientPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

                GradientPaint gp = new GradientPaint(200, 0, new Color(237, 199, 183), 0, getHeight(), new Color(172, 59, 97)); // Нижняя часть фона (более светлый серый)

                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        JRadioButton arrayFactoryButton = new JRadioButton("Фабрика на основе массива", factoryService.getFactory() instanceof ArrayTabulatedFunctionFactory);
        JRadioButton listFactoryButton = new JRadioButton("Фабрика на основе связного списка", factoryService.getFactory() instanceof LinkedListTabulatedFunctionFactory);

        ButtonGroup group = new ButtonGroup();
        group.add(arrayFactoryButton);
        group.add(listFactoryButton);

        JButton saveButton = new JButton("Сохранить");
        saveButton.addActionListener((param) -> {
            if (arrayFactoryButton.isSelected()) {
                factoryService.setFactory(new ArrayTabulatedFunctionFactory());
            } else if (listFactoryButton.isSelected()) {
                factoryService.setFactory(new LinkedListTabulatedFunctionFactory());
            }
            dispose();
        });

        gradientPanel.add(arrayFactoryButton);
        gradientPanel.add(listFactoryButton);
        gradientPanel.add(saveButton);

        add(gradientPanel);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }
}