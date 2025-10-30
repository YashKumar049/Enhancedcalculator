import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EnhancedCalculator extends JFrame implements ActionListener {

    private JTextField textField;
    private String operator = "";
    private double firstNumber = 0, secondNumber = 0;
    private boolean startNew = true;

    public EnhancedCalculator() {
        setTitle("Enhanced Calculator");
        setSize(350, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Text field
        textField = new JTextField("0");
        textField.setEditable(false);
        textField.setFont(new Font("Arial", Font.BOLD, 28));
        textField.setHorizontalAlignment(SwingConstants.RIGHT);
        add(textField, BorderLayout.NORTH);

        // Buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 5, 5));

        String[] buttons = {
            "AC", "CE", "←", "/",
            "7", "8", "9", "*",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "0", ".", "±", "="
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.setBackground(Color.LIGHT_GRAY);
            button.addActionListener(this);
            panel.add(button);
        }

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        try {
            if ("0123456789".contains(command)) {
                if (startNew) {
                    textField.setText(command);
                    startNew = false;
                } else {
                    textField.setText(textField.getText() + command);
                }
            } else if (command.equals(".")) {
                if (!textField.getText().contains(".")) {
                    textField.setText(textField.getText() + ".");
                    startNew = false;
                }
            } else if (command.equals("AC")) {
                textField.setText("0");
                firstNumber = 0;
                secondNumber = 0;
                operator = "";
                startNew = true;
            } else if (command.equals("CE")) {
                textField.setText("0");
                startNew = true;
            } else if (command.equals("←")) {
                String text = textField.getText();
                if (text.length() > 1) {
                    textField.setText(text.substring(0, text.length() - 1));
                } else {
                    textField.setText("0");
                    startNew = true;
                }
            } else if (command.equals("±")) {
                double value = Double.parseDouble(textField.getText());
                textField.setText(String.valueOf(-value));
            } else if (command.equals("=")) {
                secondNumber = Double.parseDouble(textField.getText());
                double result = 0;

                switch (operator) {
                    case "+": result = firstNumber + secondNumber; break;
                    case "-": result = firstNumber - secondNumber; break;
                    case "*": result = firstNumber * secondNumber; break;
                    case "/": 
                        if (secondNumber == 0) {
                            JOptionPane.showMessageDialog(this, "Cannot divide by zero!");
                            textField.setText("0");
                            startNew = true;
                            return;
                        }
                        result = firstNumber / secondNumber; break;
                }

                textField.setText(String.valueOf(result));
                startNew = true;
            } else { // Operators
                operator = command;
                firstNumber = Double.parseDouble(textField.getText());
                startNew = true;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid Input!");
            textField.setText("0");
            startNew = true;
        }
    }

    public static void main(String[] args) {
        new EnhancedCalculator();
    }
}
