package org.example.consoleui;

import org.example.account.BankAccount;
import org.example.money.Money;
import org.example.transaction.Transaction;
import org.example.transaction.TransactionType;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class TransactionHistoryMenu {

    private static final String OPTIONS_SEPARATOR =
            "----------------------------------------------------------------------------------------------------";

    private static final String TRANSACTIONS_SEPARATOR =
            "....................................................................................................";

    private final BankAccount bankAccount;

    private final Scanner scanner = new Scanner(System.in);


    TransactionHistoryMenu(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    void run() {

        String selectedOption;

        do {
            printOptions();
            selectedOption = readUserSelection();
            perform(selectedOption);
        } while (!selectedOption.equalsIgnoreCase("c"));
    }

    private void printOptions() {
        StringBuilder sb = new StringBuilder("\n                                            Options:")
                .append("\n                                                a: See Last Transaction")
                .append("\n                                                b: See All Transactions")
                .append("\n                                                c: Return");

        System.out.println(sb);
    }

    private String readUserSelection() {
        System.out.println("\nSelect An Option And Press Enter:");
        return scanner.nextLine().trim();
    }

    private void perform(String selected) {
        switch (selected) {
            case "a":
                System.out.println(OPTIONS_SEPARATOR);
                printLastTransaction();
                System.out.println(OPTIONS_SEPARATOR);
                break;
            case "b":
                System.out.println(OPTIONS_SEPARATOR);
                printAllTransactions();
                System.out.println(OPTIONS_SEPARATOR);
                break;
            case "c":
                break;
            default:
                System.out.println("Select an option to continue");
        }
    }

    private void printLastTransaction() {
        Transaction lastTransaction = bankAccount.getLastTransaction();

        if (lastTransaction == null) {
            printNoTransactionsMessage();
            return;
        }
        System.out.println(getFormattedTransactionDetails(lastTransaction));
    }

    private void printAllTransactions() {
        List<Transaction> transactions = bankAccount.getTransactionHistory();
        if (transactions.isEmpty()) {
            printNoTransactionsMessage();
            return;
        }

        for (Transaction each : transactions) {
            System.out.println(TRANSACTIONS_SEPARATOR);
            System.out.println(getFormattedTransactionDetails(each));
            System.out.println(TRANSACTIONS_SEPARATOR);
        }
    }

    private void printNoTransactionsMessage() {
        System.out.println("                                        No Transactions Found");
    }

    private String getFormattedTransactionDetails(Transaction lastTransaction) {
        TransactionType transactionType = lastTransaction.getType();
        Money transactionAmount = lastTransaction.getAmount();
        Money finalBalance = lastTransaction.getResult();

        StringBuilder sb = new StringBuilder("\n                                             Type:\t")
                .append(transactionType.getType())
                .append("\n                                  Initial Balance:\t")
                .append(lastTransaction.getInitialBalance().getAmount())
                .append(" ")
                .append(lastTransaction.getInitialBalance().getCurrency())
                .append("\n                                           Amount:\t")
                .append(transactionAmount.getAmount())
                .append(" ")
                .append(lastTransaction.getInitialBalance().getCurrency())
                .append("\n                                    Final Balance:\t")
                .append(finalBalance.getAmount())
                .append(" ")
                .append(lastTransaction.getInitialBalance().getCurrency())
                .append("\n                                 Transaction Date:\t")
                .append(getFormattedDate(lastTransaction.getDate()))
                .append("\n");

        return sb.toString();
    }

    private String getFormattedDate(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return date.format(formatter);
    }
}
