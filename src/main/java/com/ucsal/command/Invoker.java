package com.ucsal.command;

import com.ucsal.persistence.CommandHistory;
import com.ucsal.persistence.History;

// TODO tirar essa porqueira da pra centralizar tudo no CommandHistory
public class Invoker implements CommandInvoker {
    private static final Invoker INSTANCE = new Invoker(History.getInstance());

    private final CommandHistory history;

    public Invoker(CommandHistory history) {
        this.history = history;
    }

    public static Invoker getInstance() {
        return INSTANCE;
    }

    @Override
    public void executeCommand(Command command) {
        command.execute();
        history.push(command);
    }

    @Override
    public void undo() {
        if (!history.isEmpty()) {
            Command command = history.pop();
            command.undo();
            System.out.println("Desfeito: " + command.getDescription());
        } else {
            System.out.println("Nada para desfazer.");
        }
    }
}
