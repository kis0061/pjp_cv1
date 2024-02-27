package org.example;

import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        scanner.nextLine();

        List<String> expressions = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            expressions.add(scanner.nextLine().replaceAll("\\s", ""));
        }

        for (String x : expressions) {

                int result = evaluateExpression(x);
                if(result < 0)
                {
                    System.out.println("ERROR");
                }
                else
                {
                    System.out.println(result);
                }
        }
    }

    public static int evaluateExpression(String expression) throws Exception {
        Stack<Integer> operands = new Stack<>();
        Stack<Character> operators = new Stack<>();
        for (char c : expression.toCharArray()) {
            if (Character.isDigit(c)) {
                operands.push(c - '0');
            }
            else if (c == '(')
            {
                operators.push(c);
            }
            else if (c == ')')
            {
                while (operators.peek() != '(')
                {
                    performOperation(operands, operators);
                }
                operators.pop();
            }
            else if (c == '+' || c == '-' || c == '*' || c == '/')
            {
                while (!operators.isEmpty() && operator_weight(operators.peek()) >= operator_weight(c))
                {
                    performOperation(operands, operators);
                }
                operators.push(c);
            }

        }
        while (!operators.isEmpty())
        {
            performOperation(operands, operators);
        }

        return operands.pop();
    }

    private static int operator_weight(char op)
    {
        switch (op)
        {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return -1;
        }
    }

    private static void performOperation(Stack<Integer> operands, Stack<Character> operators) throws Exception
    {
        char op = operators.pop();
        int rightOperand = operands.pop();
        int leftOperand = operands.pop();
        int result;
        switch (op)
        {
            case '+':
                result = leftOperand + rightOperand;
                break;
            case '-':
                result = leftOperand - rightOperand;
                break;
            case '*':
                result = leftOperand * rightOperand;
                break;
            case '/':
                result = leftOperand / rightOperand;
                break;
            default:
                throw new Exception("Invalid operator");
        }
        operands.push(result);
    }
}
