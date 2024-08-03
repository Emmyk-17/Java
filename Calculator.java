import java.util.InputMismatchException;
import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continueCalculation = true;

        while (continueCalculation) {
            try {
                System.out.println("Enter the first number:");
                double num1 = scanner.nextDouble();

                System.out.println("Enter the second number:");
                double num2 = scanner.nextDouble();

                System.out.println("Choose an operation: +, -, *, /");
                char operator = scanner.next().charAt(0);

                double result;
                switch (operator) {
                    case '+':
                        result = num1 + num2;
                        System.out.println("Result: " + result);
                        break;

                    case '-':
                        result = num1 - num2;
                        System.out.println("Result: " + result);
                        break;

                    case '*':
                        result = num1 * num2;
                        System.out.println("Result: " + result);
                        break;

                    case '/':
                        if (num2 != 0) {
                            result = num1 / num2;
                            System.out.println("Result: " + result);
                        } else {
                            System.out.println("Error: Division by zero is not allowed.");
                        }
                        break;

                    default:
                        System.out.println("Error: Invalid operator. Please choose one of the following: +, -, *, /.");
                        break;
                }

            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid input. Please enter a number.");
                scanner.next(); // Clear the invalid input
            }

            System.out.println("Do you want to perform another calculation? (yes/no)");
            String response = scanner.next();
            if (!response.equalsIgnoreCase("yes")) {
                continueCalculation = false;
            }
        }

        System.out.println("Thank you for using the calculator!");
        scanner.close();
    }
}
