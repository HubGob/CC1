import java.util.Scanner;

public class radix_arithmetic {

    // Convert a number into Unicode subscript
    public static String toSubscript(int number) {
        String normal = String.valueOf(number);
        StringBuilder subscript = new StringBuilder();

        for (char c : normal.toCharArray()) {
            switch (c) {
                case '0' -> subscript.append('\u2080');
                case '1' -> subscript.append('\u2081');
                case '2' -> subscript.append('\u2082');
                case '3' -> subscript.append('\u2083');
                case '4' -> subscript.append('\u2084');
                case '5' -> subscript.append('\u2085');
                case '6' -> subscript.append('\u2086');
                case '7' -> subscript.append('\u2087');
                case '8' -> subscript.append('\u2088');
                case '9' -> subscript.append('\u2089');
            }
        }

        return subscript.toString();
    }

    // Format the result based on the radix
    public static String formatResult(int result, int radix) {
        String number = Integer.toString(result, radix).toUpperCase();

        if (radix == 10) {
            return number;
        }

        return "(" + number + ")" + toSubscript(radix);
    }

    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {

            // First number
            System.out.print("Enter the first number: ");
            String number1 = sc.nextLine();

            System.out.print("Enter the first number's radix (2-36): ");
            int radix1 = sc.nextInt();
            sc.nextLine();

            // Operation
            System.out.print("Enter operation (+, -, *, /): ");
            char operator = sc.nextLine().charAt(0);

            // Second number
            System.out.print("Enter the second number: ");
            String number2 = sc.nextLine();

            System.out.print("Enter the second number's radix (2-36): ");
            int radix2 = sc.nextInt();

            // Validate radices
            if (radix1 < 2 || radix1 > 36 ||
                radix2 < 2 || radix2 > 36) {

                System.out.println("Radix must be between 2 and 36.");
                return;
            }

            try {

                // Convert both numbers to decimal
                int decimal1 = Integer.parseInt(number1, radix1);
                int decimal2 = Integer.parseInt(number2, radix2);

                int result;

                // Perform arithmetic
                switch (operator) {

                    case '+':
                        result = decimal1 + decimal2;
                        break;

                    case '-':
                        result = decimal1 - decimal2;
                        break;

                    case '*':
                        result = decimal1 * decimal2;
                        break;

                    case '/':
                        if (decimal2 == 0) {
                            System.out.println("Cannot divide by zero.");
                            return;
                        }

                        result = decimal1 / decimal2;
                        break;

                    default:
                        System.out.println("Invalid operation.");
                        return;
                }

                // Ask for output radix
                System.out.print("Enter result radix (2-36): ");
                int resultRadix = sc.nextInt();

                if (resultRadix < 2 || resultRadix > 36) {
                    System.out.println("Radix must be between 2 and 36.");
                    return;
                }

                // Format and display result
                String formattedResult =
                        formatResult(result, resultRadix);

                System.out.println("Result: " + formattedResult);

            } catch (NumberFormatException e) {
                System.out.println("Invalid number for the specified radix.");
            }
        }
    }
}
