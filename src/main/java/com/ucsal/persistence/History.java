package com.ucsal.persistence;

import com.ucsal.command.Command;

import java.util.ArrayDeque;
import java.util.Deque;

public class History implements CommandHistory {
    private static final int MAX_HISTORY = 5;
    private static final History INSTANCE = new History();
    private final Deque<Command> history = new ArrayDeque<>();

    private History(){}

    public static History getInstance() {
        return INSTANCE;
    }

    @Override
    public void push(Command command) {
        if (history.size() >= MAX_HISTORY) {
            history.removeFirst();
        }
        history.push(command);
    }

    @Override
    public Command pop() {
        if (history.isEmpty()) {
            return null;
        }
        return history.pop();
    }

    @Override
    public boolean isEmpty() {
        return history.isEmpty();
    }
}
