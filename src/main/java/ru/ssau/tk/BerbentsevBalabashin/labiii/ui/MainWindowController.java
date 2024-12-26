package ru.ssau.tk.BerbentsevBalabashin.labiii.ui;
import ru.ssau.tk.BerbentsevBalabashin.labiii.operations.TabulatedDifferentialOperator;
import ru.ssau.tk.BerbentsevBalabashin.labiii.operations.TabulatedFunctionOperationService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindowController extends JFrame {
    private final int WIDTH_WINDOW = 1440;
    private final int HEIGHT_WINDOW = 900;
    private TabulatedFunctionOperationService factoryService;
    private ChooseСreateFactory settingsWindow;

    public MainWindowController() {
        setTitle("Главное меню");
        setSize(WIDTH_WINDOW, HEIGHT_WINDOW);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().setBackground(new Color(209, 96, 15));

        factoryService = new TabulatedFunctionOperationService();
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        buttonPanel.setLayout(new GridLayout(3, 3, 10, 100));

        JButton settingsButton = new RoundedButton("Settings", new Color(255, 199, 115));
        settingsButton.addActionListener((param)-> openSettingsWindow());

        JButton operationsButton = new RoundedButton("Operations with functions", new Color(255, 199, 115));
        operationsButton.addActionListener((param) -> openOperationsWindow());

        JButton differentialOperation = new RoundedButton("Differentiation operation", new Color(255, 199, 115));
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
            setBackground(new Color(209, 96, 15));
            setFont(new Font("Arial", Font.PLAIN, 24));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(getBackground());
            g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 0, 0);
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
