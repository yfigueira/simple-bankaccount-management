package org.example.consoleui;

import org.example.account.AccountLimit;
import org.example.account.BankAccount;
import org.example.account.UnauthorizedBankOperationException;
import org.example.money.Money;

import java.math.BigDecimal;
import java.util.Scanner;

public class AccountSettingsMenu {

    private static final String OPTIONS_SEPARATOR =
            "----------------------------------------------------------------------------------------------------";

    BankAccount bankAccount;

    private final Scanner scanner = new Scanner(System.in);


    AccountSettingsMenu(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    void run() {

        String selectedOption = "";

        do {
            printOptions();
            selectedOption = readUserSelection();
            perform(selectedOption);
        } while (!selectedOption.equalsIgnoreCase("d"));
    }

    private void printOptions() {
        StringBuilder sb = new StringBuilder("\nOptions:")
                .append("\n\ta: See Account Limits")
                .append("\n\tb: Change Deposit Limit")
                .append("\n\tc: Change Withdrawal Limit")
                .append("\n\td: Return");

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
                printTransactionLimits();
                System.out.println(OPTIONS_SEPARATOR);
                break;
            case "b":
                System.out.println(OPTIONS_SEPARATOR);
                changeTransactionLimit(bankAccount.getDepositLimit());
                System.out.println(OPTIONS_SEPARATOR);
                break;
            case "c":
                System.out.println(OPTIONS_SEPARATOR);
                changeTransactionLimit(bankAccount.getWithdrawalLimit());
                System.out.println(OPTIONS_SEPARATOR);
                break;
            case "d":
                break;
            default:
                System.out.println("Select an option to continue");
        }
    }

    private void printTransactionLimits() {
        AccountLimit depositLimit = bankAccount.getDepositLimit();
        AccountLimit withdrawalLimit = bankAccount.getWithdrawalLimit();

        StringBuilder sb = new StringBuilder("\nCurrent Account Limits:")
                .append("\n      Deposit Limit:\t")
                .append(getFormattedAccountLimit(depositLimit))
                .append("\n   Withdrawal Limit:\t")
                .append(getFormattedAccountLimit(withdrawalLimit))
                .append("\n");

        System.out.println(sb.toString());
    }

    private void changeTransactionLimit(AccountLimit accountLimit) {
        System.out.println("Enter an amount:");
        Money amount = readAmount();

        try {
            accountLimit.setTransactionLimit(amount);
            printTransactionLimits();
        } catch (UnauthorizedBankOperationException ex) {
            System.out.println("Operation Failed: " + ex.getMessage());
        }
    }

    private String getFormattedAccountLimit(AccountLimit accountLimit) {
        StringBuilder sb = new StringBuilder()
                .append(accountLimit.getTransactionLimit().getAmount())
                .append(" ")
                .append(accountLimit.getTransactionLimit().getCurrency());

        return sb.toString();
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
}
