package src.main;

import java.util.Scanner;

public class UI {
    public final static String welcomeMessage = """
                Welcome to Amazing Numbers!
                                
                Supported requests:
                - enter a natural number to know its properties;
                - enter two natural numbers to obtain the properties of the list:
                  * the first parameter represents a starting number;
                  * the second parameter shows how many consecutive numbers are to be processed;
                - two natural numbers and properties to search for;
                - a property preceded by minus must not be present in numbers;
                - separate the parameters with one space;
                - enter 0 to exit.
                """;


    public void printWelcomeMessage() {
        System.out.println(welcomeMessage);
    }

    public String[] getParametersFromUser() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().split(" ");
    }

    public void runUI() {

        printWelcomeMessage();

        while (true) {
            System.out.println("Enter a request: ");

            Parameters parameters = new Parameters(getParametersFromUser());

            if (!parameters.isValid()) {
                System.out.println(parameters.getErrorMessage());
                continue;
            }

            if (parameters.getFirstParameter() == 0) {
                System.out.println("Goodbye!");
                break;
            }

            if (parameters.parameters.length == 1) {
                System.out.println(Numbers.getProperties(parameters.getFirstParameter()));
            } else if (parameters.parameters.length == 2) {
                for (long i = parameters.getFirstParameter(); i < parameters.getSecondParameter() + parameters.getFirstParameter(); i++) {
                    System.out.println(Numbers.describeNumber(i));
                }
            } else {
                long count = 0;
                long currentNumber = parameters.getFirstParameter();

                while (count < parameters.getSecondParameter()) {
                    if (Numbers.applyFunctions(parameters.parameters, currentNumber)) {
                        System.out.println(Numbers.describeNumber(currentNumber));
                        count += 1;
                    }
                    currentNumber += 1;
                }
            }
        }
    }
}