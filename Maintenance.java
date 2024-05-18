/*import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.util.Stack;*/
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.util.Stack;

public class Maintenance extends JFrame {

    private JRadioButton pre;
    private JRadioButton post;
    private JLabel operation;
    private JLabel expression;
    private JLabel result;
    private ButtonGroup radioGroup;
    private JTextField text;
    private JButton button;

    public Maintenance() {
        super("Pre-fix /Post-fix Evaluation");
        
         setLayout(new GridLayout(0, 1, 10, 10));
        setFont(new Font("Arial", Font.PLAIN, 16));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        operation = new JLabel("Select expression type:");
        operation.setHorizontalAlignment(SwingConstants.CENTER);
        add(operation);

        JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new GridLayout(1, 2, 10, 10));
        radioPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
       
        pre = new JRadioButton("Pre-fix", true);
        post = new JRadioButton("Post-fix", false);
        add(pre);
        add(post);
        radioGroup = new ButtonGroup();
        radioGroup.add(pre);
        radioGroup.add(post);
        expression = new JLabel("Enter the expression you want to evaluate");
        add(expression);
        text = new JTextField("", 20);
        add(text);
        
        button = new JButton("Evaluate");
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.addActionListener(new buttonHandler());
        add(button);

        result = new JLabel();
        result.setHorizontalAlignment(SwingConstants.CENTER);
        result.setBorder(new EmptyBorder(10, 0, 0, 0));
        result.setVisible(false);
        add(result);

        pack();
        
      
    }

    private class buttonHandler implements ActionListener {

       public void actionPerformed(ActionEvent e) {
            if (!text.getText().isEmpty()) {
                 
  
               try {
                    String r = evaluatePrefix(text.getText());
                    result.setText("The result of the entered expression is: " + r);
                    result.setForeground(Color.BLUE);
                } catch (NumberFormatException ex) {
                    result.setText("Error! Invalid number format in the expression");
                    result.setForeground(Color.RED);
                } catch (ArithmeticException ex) {
                    result.setText("Error! Division by zero");
                    result.setForeground(Color.RED);
                } catch (Exception ex) {
                    result.setText("Error! An unexpected error occurred");
                    result.setForeground(Color.RED);
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
        
         public String evalutePost(String exp) {
             
            Stack<Double> Stack = new Stack<>();
            
            for (int i = 0; i < exp.length(); i++) {
                
                if (exp.charAt(i) == ' ') {
                    continue;
                } else if (Character.isDigit(exp.charAt(i))) {
                    
                    String op = "" + exp.charAt(i);
                    Stack.push(Double.parseDouble(op));
                } else {
                   
                 
                        double val1 = Stack.pop();
                        double val2 = Stack.pop();
                        switch (exp.charAt(i)) {
                            case '+':
                                Stack.push(val2 + val1);
                                break;
                            case '-':
                                Stack.push(val2 - val1);
                                break;
                            case '*':
                                Stack.push(val2 * val1);
                                break;
                            case '/':
                                Stack.push(val2 / val1);
                                break;
                        }
                    } 
            }
             return Stack.pop().toString();
           
        }
    }

    public static void main(String[] args) {
        Maintenance maintenance = new Maintenance();
        maintenance.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        maintenance.setSize(300, 200);
        maintenance.setVisible(true);
    }
}