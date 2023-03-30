package org.example.consoleui;

import org.example.account.BankAccount;
import org.example.account.UnauthorizedBankOperationException;
import org.example.money.Money;

import java.math.BigDecimal;
import java.util.Scanner;

public class TransactionRunningMenu {

    private static final String OPTIONS_SEPARATOR =
            "----------------------------------------------------------------------------------------------------";

    private final BankAccount bankAccount;

    private final Scanner scanner = new Scanner(System.in);

    TransactionRunningMenu(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    void run() {

        String selectedOption = "";

        do {
            printOptions();
            selectedOption = readUserSelection();
            perform(selectedOption);
        } while (!selectedOption.equalsIgnoreCase("c"));
    }

    private void printOptions() {
        StringBuilder sb = new StringBuilder("\nOptions:")
                .append("\n\ta: Deposit Funds")
                .append("\n\tb: Withdraw Funds")
                .append("\n\tc: Return");

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
                runDeposit();
                System.out.println(OPTIONS_SEPARATOR);
                break;
            case "b":
                System.out.println(OPTIONS_SEPARATOR);
                runWithdrawal();
                System.out.println(OPTIONS_SEPARATOR);
                break;
            case "c":
                break;
            default:
                System.out.println("Select an option to continue");
        }
    }

    private void runDeposit() {
        System.out.println("Enter an amount:");
        Money amount = readAmount();

        try {
            bankAccount.deposit(amount);
            printAccountBalance();
        } catch (UnauthorizedBankOperationException ex) {
            System.out.println("Transaction failed: " + ex.getMessage());
        }
    }

    private void runWithdrawal() {
        System.out.println("Enter an amount:");
        Money amount = readAmount();

        try {
            bankAccount.withdraw(amount);
            printAccountBalance();
        } catch (UnauthorizedBankOperationException ex) {
            System.out.println("Transaction failed: " + ex.getMessage());
        }
    }

    private Money readAmount() {
        boolean validInput = false;
        Money money = null;

        do {
            String value = scanner.nextLine().trim();

            try {
                BigDecimal amount = new BigDecimal(value);
                money = new Money(amount);
                validInput = true;
            } catch (NumberFormatException ex) {
                System.out.println("Enter a number to continue. Use '.' as the decimal separator.");
            }

        } while (!validInput);

        return money;
    }

    private void printAccountBalance() {
        StringBuilder sb = new StringBuilder("                                        Balance:\t")
                .append(bankAccount.getBalance().getAmount())
                .append("\t")
                .append(bankAccount.getBalance().getCurrency());

        System.out.println(sb.toString());
    }
}
