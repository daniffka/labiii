package ru.ssau.tk.BerbentsevBalabashin.labiii.ui;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.factory.TabulatedFunctionFactory;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.*;
import ru.ssau.tk.BerbentsevBalabashin.labiii.ui.InputData.DoubleNumeric;
import ru.ssau.tk.BerbentsevBalabashin.labiii.ui.InputData.IntNumeric;
import ru.ssau.tk.BerbentsevBalabashin.labiii.functions.TabulatedFunction;
import ru.ssau.tk.BerbentsevBalabashin.labiii.ui.NewFunctoins.*;
import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.TreeMap;

public class MathFunctionController extends JDialog {
    private final JComboBox<String> functionComboBox;
    private final JTextField leftBoundField;
    private final JTextField rightBoundField;
    private final JTextField pointsCountField;
    private final Map<String, MathFunction> functionMap;
    final int PANEL_ROWS = 5;
    final int PANEL_COLUMNS = 2;
    private final TabulatedFunctionFactory factory;
    private TabulatedFunction tabulatedFunction;
    JFrame frame = new JFrame();

    public MathFunctionController(JFrame frame, TabulatedFunctionFactory factory) {
        super(frame, true);
        this.factory = new LinkedListTabulatedFunctionFactory();
        this.functionMap = createFunctionMap();
        frame.setTitle("\n" +
                "Create a Tabulated Function");
        setSize(850, 480);
        setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(PANEL_ROWS, PANEL_COLUMNS)){
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

        JLabel functionLabel = new JLabel("Select function:");
        functionComboBox = new JComboBox<>(functionMap.keySet().toArray(new String[0]));
        JLabel leftBoundLabel = new JLabel("Left border:");
        leftBoundField = new JTextField();
        ((AbstractDocument) leftBoundField.getDocument()).setDocumentFilter(new DoubleNumeric());
        JLabel rightBoundLabel = new JLabel("Right border:");
        rightBoundField = new JTextField();
        ((AbstractDocument) rightBoundField.getDocument()).setDocumentFilter(new DoubleNumeric());
        JLabel pointsCountLabel = new JLabel("Number of points:");
        pointsCountField = new JTextField();
        ((AbstractDocument) pointsCountField.getDocument()).setDocumentFilter(new IntNumeric());

        panel.add(functionLabel);
        panel.add(functionComboBox);
        panel.add(leftBoundLabel);
        panel.add(leftBoundField);
        panel.add(rightBoundLabel);
        panel.add(rightBoundField);
        panel.add(pointsCountLabel);
        panel.add(pointsCountField);

        JButton createButton = new RoundedButton("Create", new Color(140, 88, 24));
        createButton.addActionListener(new CreateFunctionListener());

        add(panel, BorderLayout.CENTER);
        add(createButton, BorderLayout.SOUTH);
        panel.setVisible(true);
    }

    private Map<String, MathFunction> createFunctionMap() {
        Map<String, MathFunction> map = new TreeMap<>();
        map.put("Quadratic function", new SqrFunction());
        map.put("Identity function", new IdentityFunction());
        map.put("Constant function 0", new ZeroFunction());
        map.put("Constant function 1", new UnitFunction());
        map.put("Sin", new SinFunction());
        map.put("Cos", new CosFunction());
        map.put("Tg", new TgFunction());
        return map;
    }
    public static class RoundedButton extends JButton {
        public RoundedButton(String label, Color textColor) {
            super(label);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setForeground(textColor);
            setBackground(new Color(140, 88, 24));
            setFont(new Font("Arial", Font.PLAIN, 24));
        }
    }
    private class CreateFunctionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String selectedFunctionName = (String) functionComboBox.getSelectedItem();
                MathFunction selectedFunction = functionMap.get(selectedFunctionName);

                double leftX = Double.parseDouble(leftBoundField.getText());
                double rightX = Double.parseDouble(rightBoundField.getText());
                int pointsCount = Integer.parseInt(pointsCountField.getText());

                if (leftX >= rightX) {
                    throw new IllegalArgumentException("The left border must be smaller than the right.");
                }
                if (pointsCount < 2) {
                    throw new IllegalArgumentException("The number of points must be greater than 1.");
                }
                double[] xValues = new double[pointsCount];
                double[] yValues = new double[pointsCount];
                double step = (rightX - leftX) / (pointsCount - 1);
                for (int i = 0; i < pointsCount; i++) {
                    xValues[i] = leftX + i * step;
                    yValues[i] = selectedFunction.apply(xValues[i]);
                }

                tabulatedFunction = factory.create(xValues, yValues);
                JOptionPane.showMessageDialog(MathFunctionController.this, "Function created");
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(MathFunctionController.this, "Invalid input!", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(MathFunctionController.this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public TabulatedFunction getTabulatedFunction() {
        return tabulatedFunction;
    }

}
