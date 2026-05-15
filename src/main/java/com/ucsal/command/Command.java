package com.ucsal.command;

public interface Command {
    void execute();
    void undo();
    String getDescription();
}
