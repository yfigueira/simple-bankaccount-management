package org.example;

import org.example.consoleui.AccountMenu;
import org.example.consoleui.Login;

public class Main {
    public static void main(String[] args) {
        Login login = new Login();
        login.run();

        String customerName = login.getCustomerName();
        String customerId = login.getCustomerId();

        AccountMenu accountMenu = new AccountMenu(customerName, customerId);
        accountMenu.run();
    }
}