package ru.ssau.tk.BerbentsevBalabashin.labiii.ui;
import ru.ssau.tk.BerbentsevBalabashin.labiii.operations.TabulatedDifferentialOperator;
import ru.ssau.tk.BerbentsevBalabashin.labiii.operations.TabulatedFunctionOperationService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindowController extends JFrame {
    private final int WIDTH_WINDOW = 800;
    private final int HEIGHT_WINDOW = 600;
    private TabulatedFunctionOperationService factoryService;
    private ChooseСreateFactory settingsWindow;

    public MainWindowController() {
        setTitle("Главное меню");
        setSize(WIDTH_WINDOW, HEIGHT_WINDOW);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().setBackground(new Color(209, 230, 199));

        factoryService = new TabulatedFunctionOperationService();
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(200, 0, new Color(255, 230, 55), 0, getHeight(), new Color(166, 255, 199));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        buttonPanel.setLayout(new GridLayout(5, 1, 30, 50));

        JButton settingsButton = new RoundedButton("Открыть настройки", new Color(0, 48, 255));
        settingsButton.addActionListener((param)-> openSettingsWindow());

        JButton operationsButton = new RoundedButton("Элементарные операции с функциями", new Color(0, 48, 255));
        operationsButton.addActionListener((param) -> openOperationsWindow());

        JButton differentialOperation = new RoundedButton("Операция дифференцирования над функцией", new Color(0, 48, 255));
        differentialOperation.addActionListener((param) -> openDifferentialOperations());

        buttonPanel.add(settingsButton);
        buttonPanel.add(operationsButton);
        buttonPanel.add(differentialOperation);

        add(buttonPanel, BorderLayout.CENTER);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        setVisible(true);
    }
    public static class RoundedButton extends JButton {
        public RoundedButton(String label, Color textColor) {
            super(label);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setForeground(textColor);
            setBackground(new Color(250, 157, 101));
            setFont(new Font("MerriWeather", Font.PLAIN, 20));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(getBackground());
            g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 120, 120);
            super.paintComponent(g);
        }
    }


    private void openSettingsWindow() {
        if (settingsWindow == null || !settingsWindow.isShowing()) {
            settingsWindow = new ChooseСreateFactory(this, factoryService);
            settingsWindow.setVisible(true);
        }
    }

    private void openOperationsWindow() {
        new OperationsWindow(this, factoryService);
    }

    private void openDifferentialOperations() {
        new DifferentialOperations(this, new TabulatedDifferentialOperator(factoryService.getFactory()));
    }

}
