package ru.ssau.tk.BerbentsevBalabashin.labiii.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.factory.TabulatedFunctionFactory;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.TabulatedFunction;
public class TableController extends JDialog {

    private JTextField pointCount;
    private JTable table;
    private DefaultTableModel tableModel;
    private JPanel tablePanel;
    private LinkedListTabulatedFunctionFactory factory;
    private TabulatedFunction tabulatedFunction;

    public TableController(JFrame owner, TabulatedFunctionFactory tabulatedFunctionFactory) {
        super(owner, true);
        factory = new LinkedListTabulatedFunctionFactory();
        setTitle("Create Tabulated Function");
        setSize(800, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel gradientPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;


                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

                GradientPaint gp = new GradientPaint(200, 0, new Color(247, 177, 126), 0, getHeight(), new Color(247, 177, 126));

                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        setLayout(new BorderLayout());
        gradientPanel.setLayout(new FlowLayout());
        JLabel pointCountLabel = new JLabel("Number of points:");
        pointCount = new JTextField(10);
        JButton createTableButton = new RoundedButton("Add", new Color(209, 96, 15));
        gradientPanel.add(pointCountLabel);
        gradientPanel.add(pointCount);
        gradientPanel.add(createTableButton);

        tablePanel = new JPanel();
        tableModel = new DefaultTableModel(new Object[]{"X", "Y"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        tablePanel.setLayout(new BorderLayout());
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        JButton createFunctionButton = new RoundedButton("Create", new Color(209, 96, 15));
        JPanel buttonPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

                GradientPaint gp = new GradientPaint(200, 0, new Color(247, 177, 126), 0, getHeight(), new Color(247, 177, 126));

                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        buttonPanel.add(createFunctionButton);

        add(gradientPanel, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        createTableButton.addActionListener(e -> createTable());
        createFunctionButton.addActionListener(e -> createTabulatedFunction());
        gradientPanel.setVisible(true);
    }

    private void createTable() {
        int pointCount;
        String input = this.pointCount.getText();

        try {
            pointCount = HandlerExceptions.getPointCount(input);
        } catch (IllegalArgumentException e) {
            HandlerExceptions.showErrorDialog(e.getMessage());
            return;
        }

        tableModel.setRowCount(0);
        for (int i = 0; i < pointCount; i++) {
            tableModel.addRow(new Object[]{"", ""});
        }
    }

    private void createTabulatedFunction() {
        if (table.isEditing()) {
            table.getCellEditor().stopCellEditing();
        }
        int rowCount = tableModel.getRowCount();
        double[] xValues = new double[rowCount];
        double[] yValues = new double[rowCount];
        try {
            for (int i = 0; i < rowCount; i++) {
                xValues[i] = Double.parseDouble(tableModel.getValueAt(i, 0).toString());
                yValues[i] = Double.parseDouble(tableModel.getValueAt(i, 1).toString());
            }

            for (int i = 1; i < rowCount; i++) {
                if (xValues[i] <= xValues[i - 1]) {
                    JOptionPane.showMessageDialog(this, "X should increase!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            tabulatedFunction = factory.create(xValues, yValues);
            JOptionPane.showMessageDialog(this, "Function created!", "Correct", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Enter the correct point value!", "Error", JOptionPane.ERROR_MESSAGE);
        }
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
    }

    public TabulatedFunction getTabulatedFunction() {
        return tabulatedFunction;
    }
}