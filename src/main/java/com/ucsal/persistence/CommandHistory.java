package com.ucsal.persistence;

import com.ucsal.command.Command;

public interface CommandHistory {
    void push(Command command);
    Command pop();
    boolean isEmpty();
}
