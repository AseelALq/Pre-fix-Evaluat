import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.util.Stack;

public class Maintenance extends JFrame {

    private JRadioButton pre;
    private JLabel operation;
    private JLabel expression;
    private JLabel result;
    private ButtonGroup radioGroup;
    private JTextField text;
    private JButton button;

    public Maintenance() {
        super("Pre-fix Evaluation");
        setLayout(new FlowLayout());
        operation = new JLabel("What expression do you want to evaluate?");
        add(operation);
        pre = new JRadioButton("Pre-fix", true);
        add(pre);
        radioGroup = new ButtonGroup();
        radioGroup.add(pre);
        expression = new JLabel("Enter the expression you want to evaluate");
        add(expression);
        text = new JTextField("", 20);
        add(text);
        button = new JButton("Evaluate");
        buttonHandler handler = new buttonHandler();
        button.addActionListener(handler);
        add(button);
        result = new JLabel();
        result.setVisible(false);
        add(result);
    }

    private class buttonHandler implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (!text.getText().isEmpty()) {
                String r = evaluatePrefix(text.getText());
                if (r.equals("Error")) {
                    result.setText("Error! The entered expression is not valid");
                    result.setForeground(Color.RED);
                } else {
                    result.setText("The result of the entered expression is: " + r);
                    result.setForeground(Color.BLUE);
                }
                result.setVisible(true);
            }
        }

        public String evaluatePrefix(String exp) {
            Stack<Double> stack = new Stack<>();
            String[] tokens = exp.split("\\s+");
            for (int i = tokens.length - 1; i >= 0; i--) {
                if (Character.isDigit(tokens[i].charAt(0))) {
                    stack.push(Double.parseDouble(tokens[i]));
                } else {
                    if (stack.size() < 2) {
                        return "Error";
                    }
                    double val1 = stack.pop();
                    double val2 = stack.pop();
                    switch (tokens[i]) {
                        case "+":
                            stack.push(val1 + val2);
                            break;
                        case "-":
                            stack.push(val1 - val2);
                            break;
                        case "*":
                            stack.push(val1 * val2);
                            break;
                        case "/":
                            stack.push(val1 / val2);
                            break;
                        default:
                            return "Error";
                    }
                }
            }
            if (stack.size() != 1) {
                return "Error";
            }
            return stack.pop().toString();
        }
    }

    public static void main(String[] args) {
        Maintenance maintenance = new Maintenance();
        maintenance.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        maintenance.setSize(300, 200);
        maintenance.setVisible(true);
    }
}