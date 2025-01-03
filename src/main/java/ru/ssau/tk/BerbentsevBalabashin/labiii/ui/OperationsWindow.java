package ru.ssau.tk.BerbentsevBalabashin.labiii.ui;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.factory.TabulatedFunctionFactory;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.TabulatedFunction;
import ru.ssau.tk.BerbentsevBalabashin.labiii.io.FunctionsIO;
import ru.ssau.tk.BerbentsevBalabashin.labiii.operations.TabulatedFunctionOperationService;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.*;

public class OperationsWindow extends JDialog {
    private final TabulatedFunctionOperationService operationService;
    private TabulatedFunction firstFunction;
    private TabulatedFunction secondFunction;
    private TabulatedFunction resultFunction;

    private final JTable resultFunctionTable;

    private final DefaultTableModel firstTableModel;
    private final DefaultTableModel secondTableModel;
    private final DefaultTableModel resultTableModel;

    private final int operand_1 = 1;
    private final int operand_2 = 2;
    JFrame owner;

    public OperationsWindow(JFrame frame, TabulatedFunctionOperationService operationService) {
        super(frame, "Operations with tabulated functions", true);
        owner = frame;
        this.operationService = operationService;
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        firstTableModel = new DefaultTableModel(new Object[]{"x", "y"}, 0);
        secondTableModel = new DefaultTableModel(new Object[]{"x", "y"}, 0);
        resultTableModel = new DefaultTableModel(new Object[]{"x", "y"}, 0);
        JTable firstFunctionTable = createTable(firstTableModel, true, operand_1);
        JTable secondFunctionTable = createTable(secondTableModel, true, operand_2);
        resultFunctionTable = createTable(resultTableModel, false, -1);

        JPanel firstFunctionPanel = createFunctionPanel("Function 1", firstFunctionTable,
                (param)-> createFunction(1), (param) -> loadFunction(1), (param) -> saveFunction(1));
        JPanel secondFunctionPanel = createFunctionPanel("Function 2", secondFunctionTable,
                (param) -> createFunction(2), (param) -> loadFunction(2), (param)-> saveFunction(2));
        JPanel resultFunctionPanel = createResultPanel();

        JPanel panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

                GradientPaint gp = new GradientPaint(200, 0, new Color(209, 96, 15), 0, getHeight(), new Color(209, 96, 15));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        panel.setLayout(new GridLayout(4, 1));
        JButton sumButton = new RoundedButton("Addition", new Color(247, 177, 126));
        JButton subtractButton = new RoundedButton("Substraction", new Color(247, 177, 126));
        JButton multiplyButton = new RoundedButton("Multiplication", new Color(247, 177, 126));
        JButton divideButton = new RoundedButton("Division", new Color(247, 177, 126));
        sumButton.addActionListener((param) -> performOperation(1));
        subtractButton.addActionListener((param)-> performOperation(2));
        multiplyButton.addActionListener((param)-> performOperation(3));
        divideButton.addActionListener((param) -> performOperation(4));
        panel.add(sumButton);
        panel.add(subtractButton);
        panel.add(multiplyButton);
        panel.add(divideButton);

        JPanel functionsPanel = new JPanel(new GridLayout(3, 3));
        functionsPanel.add(firstFunctionPanel);
        functionsPanel.add(secondFunctionPanel);
        functionsPanel.add(resultFunctionPanel);
        add(functionsPanel, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);
        setVisible(true);
    }

    private JPanel createFunctionPanel(String title, JTable table, ActionListener createListener, ActionListener loadListener, ActionListener saveListener) {
        JPanel panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

                GradientPaint gp = new GradientPaint(200, 0, new Color(209, 96, 15), 0, getHeight(), new Color(209, 96, 15));

                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(title));
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);
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
        JButton createButton = new RoundedButton("Create", new Color(209, 96, 15));
        JButton loadButton = new RoundedButton("Download", new Color(209, 96, 15));
        JButton saveButton = new RoundedButton("Save", new Color(209, 96, 15));
        createButton.addActionListener(createListener);
        loadButton.addActionListener(loadListener);
        saveButton.addActionListener(saveListener);
        buttonPanel.add(createButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(saveButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createResultPanel() {
        JPanel panel = new JPanel(){
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
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Result"));
        JScrollPane scrollPane = new JScrollPane(resultFunctionTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        JButton saveButton = new RoundedButton("Save", new Color(209, 96, 15));
        saveButton.addActionListener((param) -> saveFunction(3));
        panel.add(saveButton, BorderLayout.SOUTH);
        return panel;
    }

    private JTable createTable(DefaultTableModel tableModel, boolean editable, int operand) {
        JTable table = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return editable && column != 0;
            }
        };
        tableModel.addTableModelListener(e -> {
            if (operand == operand_1) {
                if (firstFunction != null && e.getType() == TableModelEvent.UPDATE) {
                    int row = e.getFirstRow();
                    int column = e.getColumn();

                    if (column == 1) {
                        try {
                            double newValue = Double.parseDouble(tableModel.getValueAt(row, column).toString());
                            firstFunction.setY(row, newValue);
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(table, "Enter a valid numeric value", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            } else if (operand == operand_2) {
                if (secondFunction != null && e.getType() == TableModelEvent.UPDATE) {
                    int row = e.getFirstRow();
                    int column = e.getColumn();

                    if (column == 1) {
                        try {
                            double newValue = Double.parseDouble(tableModel.getValueAt(row, column).toString());
                            secondFunction.setY(row, newValue); // Синхронизация с функцией
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(table, "Enter a valid numeric value", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });
        return table;
    }

    private void performOperation(int operation) {
        if (firstFunction == null || secondFunction == null) {
            JOptionPane.showMessageDialog(this, "Both functions must be created or loaded", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            switch (operation) {
                case 1:
                    resultFunction = operationService.sum(firstFunction, secondFunction);
                    break;
                case 2:
                    resultFunction = operationService.div(firstFunction, secondFunction);
                    break;
                case 3:
                    resultFunction = operationService.mult(firstFunction, secondFunction);
                    break;
                case 4:
                    resultFunction = operationService.div(firstFunction, secondFunction);
                    break;
            }
            updateTableWithFunction(resultTableModel, resultFunction);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error during operation: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void createFunction(int operand){
        TabulatedFunctionFactory selectedFactory = operationService.getFactory();

        TabulatedFunction createdFunction = null;

        if (selectedFactory instanceof ArrayTabulatedFunctionFactory) {
            TableController arraysWindow = new TableController(owner, operationService. getFactory());
            arraysWindow.setVisible(true);
            createdFunction = arraysWindow.getTabulatedFunction();

        } else if (selectedFactory instanceof LinkedListTabulatedFunctionFactory) {
            MathFunctionController mathWindow = new MathFunctionController(owner, operationService. getFactory());
            mathWindow.setVisible(true);
            createdFunction = mathWindow.getTabulatedFunction();
        }

        if (createdFunction != null) {
            if (operand == 1) {
                firstFunction = createdFunction;
                updateTableWithFunction(firstTableModel, firstFunction);
            } else if (operand == 2) {
                secondFunction = createdFunction;
                updateTableWithFunction(secondTableModel, secondFunction);
            }
        } else {
            JOptionPane.showMessageDialog(this, "The function was not created", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadFunction(int operand) {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try(FileInputStream fileInputStream = new FileInputStream(file);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream)) {
                TabulatedFunction function = FunctionsIO.deserialize(bufferedInputStream);
                if (operand == 1) {
                    firstFunction = function;
                    updateTableWithFunction(firstTableModel, firstFunction);
                } else if (operand == 2) {
                    secondFunction = function;
                    updateTableWithFunction(secondTableModel, secondFunction);
                }
            } catch (IOException | ClassNotFoundException e) {
                JOptionPane.showMessageDialog(this, "Error loading function: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void saveFunction(int operand) {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showSaveDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (FileOutputStream fileOutputStream = new FileOutputStream(file);
                 BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream)) {
                 TabulatedFunction function = (operand == 1) ? firstFunction : (operand == 2) ? secondFunction : resultFunction;
                FunctionsIO.serialize(bufferedOutputStream, function);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error saving function: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void updateTableWithFunction(DefaultTableModel tableModel, TabulatedFunction function) {
        tableModel.setRowCount(0);
        for (int i = 0; i < function.getCount(); i++) {
            tableModel.addRow(new Object[]{function.getX(i), function.getY(i)});
        }
    }

    public static class RoundedButton extends JButton {
        public RoundedButton(String label, Color textColor) {
            super(label);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setForeground(textColor);
            setBackground(new Color(247, 177, 126));
            setFont(new Font("Arial", Font.PLAIN, 24));
        }
    }
}
