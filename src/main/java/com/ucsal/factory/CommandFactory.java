package com.ucsal.factory;

import com.ucsal.command.Command;

import java.lang.reflect.Constructor;

public class CommandFactory {
    private final CommandRegistry registry;

    public CommandFactory(CommandRegistry registry) {
        this.registry = registry;
    }

    public Command create(String commandName, Object... args) {
        try {
            Class<? extends Command> clazz = registry.get(commandName);

            if (clazz == null) {
                throw new RuntimeException("Comando não encontrado.");
            }

            for (Constructor<?> constructor : clazz.getConstructors()) {
                if (constructor.getParameterCount() == args.length) {
                    return (Command) constructor.newInstance(args);
                }
            }
            throw new RuntimeException("Construtor incompatível.");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}