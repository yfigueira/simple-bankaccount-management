package org.example.consoleui;

import java.util.Scanner;

public class AccountMenu {

    private static final String MAIN_SEPARATOR =
            "****************************************************************************************************";

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void run() {

        String selectedOption = "";

        do {
            printOptions();
            selectedOption = readUserSelection();
            perform(selectedOption);

        } while (!selectedOption.equalsIgnoreCase("e"));
    }

    private static void printOptions() {
        StringBuilder sb = new StringBuilder("\nOptions:")
                .append("\n\ta: Check Account Balance")
                .append("\n\tb: Perform Transaction")
                .append("\n\tc: Transactions History")
                .append("\n\td: Account Settings")
                .append("\n\te: Exit");

        System.out.println(sb.toString());
    }

    private static String readUserSelection() {
        System.out.println("\nSelect An Option And Press Enter:");
        return SCANNER.nextLine().trim();
    }

    private static void perform(String selected) {
        switch (selected) {
            case "e":
                exit();
                break;
            default:
                System.out.println("Select an option to continue");
        }
    }

    private static void exit() {
        System.out.println(MAIN_SEPARATOR);
        System.out.println("                                  Thank You For Using Simple Bank!                                  ");
        System.out.println(MAIN_SEPARATOR);

        SCANNER.close();
    }
}
