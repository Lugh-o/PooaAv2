package com.ucsal;

import com.ucsal.factory.TerminalUIFactory;

public class Main {
    static void main(String[] args) {
        TerminalUIFactory.create().start();
    }
}