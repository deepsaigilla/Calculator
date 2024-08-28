import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Calculator extends JFrame implements ActionListener {
    private JTextField display;
    private JButton[] numberButtons;
    private JButton[] functionButtons;
    private JButton addButton, subButton, mulButton, divButton, modButton;
    private JButton decButton, equButton, delButton, clrButton;
    private JPanel panel;

    private Font font = new Font("Arial", Font.PLAIN, 24);

    public Calculator() {
        setTitle("Calculator");
        setSize(420, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(50, 50, 50));

        display = new JTextField();
        display.setBounds(50, 25, 300, 50);
        display.setFont(font);
        display.setEditable(false);
        display.setBackground(Color.BLACK);
        display.setForeground(Color.WHITE);
        add(display);

        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(font);
            numberButtons[i].setFocusable(false);
            numberButtons[i].setBackground(new Color(100, 100, 100));
            numberButtons[i].setForeground(Color.WHITE);
        }

        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        modButton = new JButton("%");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("Dlt");
        clrButton = new JButton("Clr");

        functionButtons = new JButton[]{
            addButton, subButton, mulButton, divButton, modButton, decButton, equButton, delButton, clrButton
        };

        for (JButton button : functionButtons) {
            button.addActionListener(this);
            button.setFont(font);
            button.setFocusable(false);
            button.setBackground(new Color(70, 70, 70));
            button.setForeground(Color.WHITE);
        }

        panel = new JPanel();
        panel.setBounds(50, 100, 300, 400);
        panel.setLayout(new GridLayout(5, 4, 10, 10));
        panel.setBackground(new Color(50, 50, 50));

        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(divButton);
        panel.add(equButton);
        panel.add(modButton);
        panel.add(delButton);
        panel.add(clrButton);

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                display.setText(display.getText().concat(String.valueOf(i)));
            }
        }

        if (e.getSource() == decButton) {
            display.setText(display.getText().concat("."));
        }

        if (e.getSource() == addButton) {
            display.setText(display.getText().concat(" + "));
        }

        if (e.getSource() == subButton) {
            display.setText(display.getText().concat(" - "));
        }

        if (e.getSource() == mulButton) {
            display.setText(display.getText().concat(" * "));
        }

        if (e.getSource() == divButton) {
            display.setText(display.getText().concat(" / "));
        }

        if (e.getSource() == modButton) {
            display.setText(display.getText().concat(" % "));
        }

        if (e.getSource() == equButton) {
            try {
                String expression = display.getText();
                double result = evaluateExpression(expression);
                display.setText(String.valueOf(result));
            } catch (Exception ex) {
                display.setText("Error");
            }
        }

        if (e.getSource() == clrButton) {
            display.setText("");
        }

        if (e.getSource() == delButton) {
            String string = display.getText();
            if (!string.isEmpty()) {
                display.setText(string.substring(0, string.length() - 1));
            }
        }
    }

    private double evaluateExpression(String expression) {
        String[] tokens = expression.split(" ");
        double result = Double.parseDouble(tokens[0]);

        for (int i = 1; i < tokens.length; i += 2) {
            String operator = tokens[i];
            double nextNumber = Double.parseDouble(tokens[i + 1]);

            switch (operator) {
                case "+":
                    result += nextNumber;
                    break;
                case "-":
                    result -= nextNumber;
                    break;
                case "*":
                    result *= nextNumber;
                    break;
                case "/":
                    result /= nextNumber;
                    break;
                case "%":
                    result %= nextNumber;
                    break;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Calculator());
    }
}
