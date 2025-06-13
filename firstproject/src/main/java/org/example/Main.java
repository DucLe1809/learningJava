package org.example;
import java.util.Scanner;
import java.util.Stack;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter expression: ");

        String expression = scanner.nextLine();

        int result = evaluateExpression(expression);
        System.out.println("Result: " + result);
    }

    public static int evaluateExpression(String expression) {
        char[] chars = expression.toCharArray();

        Stack<Integer> values = new Stack<>();
        Stack<Character> operators = new Stack<>();

        // Iterate through every element of the input
        for (int i = 0; i < chars.length; i++) {
            // Skip empty space
            if (chars[i] == ' ') {
                continue;
            }

            // If it is a number, parse the number
            if (chars[i] >= '0' && chars[i] <= '9')
            {
                StringBuilder number = new StringBuilder();
                while (i < chars.length && Character.isDigit(chars[i])) {
                    number.append(chars[i]);
                    ++i;
                }
                values.push(Integer.parseInt(number.toString()));
                --i;
            }

            else if (chars[i] == '(') {
                operators.push(chars[i]);
            }
            else if (chars[i] == ')') {
                // if the character is ')', pop and execute the operator until '(' is encountered
                // i.e we evaluate all the expressions inside the parenthesis
                while (operators.peek() != '(') {
                    values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
                }
                operators.pop();
            }
            // If the character is an operator, applying the operators with higher precedence
            else if (chars[i] == '+' || chars[i] == '-'
                        || chars[i] == 'x' || chars[i] == '/')
            {
                while (!operators.isEmpty() && hasPrecedence(chars[i], operators.peek()))
                {
                    values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
                }
                operators.push(chars[i]);
            }
        }
        // Process any remaining operators in the stack
        while (!operators.isEmpty()) {
            values.push(applyOperator(operators.pop(),
                    values.pop(),
                    values.pop()));
        }

        // The result is the only remaining element in the
        // values stack
        return values.pop();
    }

    // Function to check the order between operators
    private static boolean hasPrecedence(char operator1,
                                         char operator2)
    {
        if (operator2 == '(' || operator2 == ')')
            return false;
        return (operator1 != 'x' && operator1 != '/')
                || (operator2 != '+' && operator2 != '-');
    }

    // Function to compute the operator between 2 operands
    private static int applyOperator(char op, int b, int a) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case 'x':
                return a * b;
            case '/':
                if (b == 0) {
                    throw new ArithmeticException("Cannot divide by zero");
                }
                return a / b;
        }
        return 0;
        }
    public void test1() {

    }
}