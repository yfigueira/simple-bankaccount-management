package org.example.consoleui;

import org.example.account.BankAccount;
import org.example.transaction.InMemoryTransactionHistory;

import java.util.Scanner;

public class AccountMenu {

    private static final String MAIN_SEPARATOR =
            "****************************************************************************************************";

    private static final String OPTIONS_SEPARATOR =
            "----------------------------------------------------------------------------------------------------";

    private final Scanner scanner = new Scanner(System.in);

    private final BankAccount bankAccount;

    private final TransactionRunningMenu transactionRunningMenu;

    private final TransactionHistoryMenu transactionHistoryMenu;

    public AccountMenu(String customerName, String customerId) {
        this.bankAccount = new BankAccount(customerName, customerId, new InMemoryTransactionHistory());
        this.transactionRunningMenu = new TransactionRunningMenu(bankAccount);
        this.transactionHistoryMenu = new TransactionHistoryMenu(bankAccount);
    }

    public void run() {

        String selectedOption = "";

        do {
            printOptions();
            selectedOption = readUserSelection();
            perform(selectedOption);

        } while (!selectedOption.equalsIgnoreCase("e"));
    }

    private void printOptions() {
        StringBuilder sb = new StringBuilder("\nOptions:")
                .append("\n\ta: Check Account Balance")
                .append("\n\tb: Perform Transaction")
                .append("\n\tc: Transactions History")
                .append("\n\td: Account Settings")
                .append("\n\te: Exit");

        System.out.println(sb.toString());
    }

    private String readUserSelection() {
        System.out.println("\nSelect An Option And Press Enter:");
        return scanner.nextLine().trim();
    }

    private void perform(String selected) {
        switch (selected) {
            case "a":
                System.out.println(OPTIONS_SEPARATOR);
                printAccountBalance();
                System.out.println(OPTIONS_SEPARATOR);
                break;
            case "b":
                System.out.println(OPTIONS_SEPARATOR);
                openTransactionRunningMenu();
                System.out.println(OPTIONS_SEPARATOR);
                break;
            case "c":
                System.out.println(OPTIONS_SEPARATOR);
                openTransactionHistoryMenu();
                System.out.println(OPTIONS_SEPARATOR);
                break;
            case "e":
                exit();
                break;
            default:
                System.out.println("Select an option to continue");
        }
    }

    private void printAccountBalance() {
        StringBuilder sb = new StringBuilder("                                        Balance:\t")
                .append(bankAccount.getBalance().getAmount())
                .append("\t")
                .append(bankAccount.getBalance().getCurrency());

        System.out.println(sb.toString());
    }

    private void openTransactionRunningMenu() {
        transactionRunningMenu.run();
    }

    private void openTransactionHistoryMenu() {
        transactionHistoryMenu.run();
    }

    private void exit() {
        System.out.println(MAIN_SEPARATOR);
        System.out.println("                                  Thank You For Using Simple Bank!");
        System.out.println(MAIN_SEPARATOR);

        scanner.close();
    }
}
