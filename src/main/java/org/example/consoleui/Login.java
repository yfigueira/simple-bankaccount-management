package org.example.consoleui;

import java.util.Scanner;

public class Login {

    private static final String MAIN_SEPARATOR =
            "****************************************************************************************************";

    private static String customerName;

    private static String customerId;

    public static void run() {
        System.out.println(MAIN_SEPARATOR);
        printWelcomeMessage();
        System.out.println(MAIN_SEPARATOR);

        askForCredentials();

        System.out.println(MAIN_SEPARATOR);
        printConfirmation();
        System.out.println(MAIN_SEPARATOR);
    }

    static private void printWelcomeMessage() {
        System.out.println("\n                                       Welcome To Simple Bank                                       \n");
    }

    private static void askForCredentials() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your name and press 'Enter' to confirm:");
        customerName = scanner.nextLine();

        System.out.println("Enter your customer ID and press 'Enter' to confirm:");
        customerId = scanner.nextLine();

        scanner.close();
    }

    private static void printConfirmation() {
        StringBuilder sb = new StringBuilder("\nCustomer ID: ")
                .append(customerId)
                .append(" confirmed. Welcome ")
                .append(customerName)
                .append("!\n");

        System.out.println(sb.toString());
    }
}
