package com.ucsal.command;

public interface CommandInvoker {
    void executeCommand(Command command);
    void undo();
}
