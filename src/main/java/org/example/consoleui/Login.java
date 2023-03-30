package org.example.consoleui;

import java.util.Scanner;

public class Login {

    private static final String MAIN_SEPARATOR =
            "****************************************************************************************************";

    private String customerName;

    private String customerId;

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void run() {
        System.out.println(MAIN_SEPARATOR);
        printWelcomeMessage();
        System.out.println(MAIN_SEPARATOR);

        askForCredentials();

        System.out.println(MAIN_SEPARATOR);
        printConfirmation();
        System.out.println(MAIN_SEPARATOR);
    }

    static void printWelcomeMessage() {
        System.out.println("\n                                       Welcome To Simple Bank                                       \n");
    }

    private void askForCredentials() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your name and press 'Enter' to confirm:");
        customerName = scanner.nextLine();

        System.out.println("Enter your customer ID and press 'Enter' to confirm:");
        customerId = scanner.nextLine();
    }

    private void printConfirmation() {
        StringBuilder sb = new StringBuilder("\nCustomer ID: ")
                .append(customerId)
                .append(" confirmed. Welcome ")
                .append(customerName)
                .append("!\n");

        System.out.println(sb.toString());
    }
}
