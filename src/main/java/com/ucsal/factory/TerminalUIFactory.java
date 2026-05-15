package com.ucsal.factory;

import com.ucsal.command.Invoker;
import com.ucsal.persistence.Stock;
import com.ucsal.ui.TerminalUI;

import java.util.Scanner;

public class TerminalUIFactory {
    public static TerminalUI create() {
        final Scanner scanner = new Scanner(System.in);
        final Invoker invoker = Invoker.getInstance();
        final Stock stock = Stock.getInstance();
        final CommandRegistry registry = new CommandRegistry("com.ucsal.command");
        final CommandFactory factory = new CommandFactory(registry);

        return new TerminalUI(scanner, invoker, stock, factory);
    }
}
