package com.ucsal.factory;

import com.ucsal.annotation.CommandHandler;
import com.ucsal.command.Command;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CommandRegistry {
    private final Map<String, Class<? extends Command>> commands = new HashMap<>();

    public CommandRegistry(String packageName) {
        autoRegister(packageName);
    }

    @SuppressWarnings("unchecked")
    private void autoRegister(String packageName) {
        Reflections reflections = new Reflections(packageName);
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(CommandHandler.class);

        for (Class<?> clazz : classes) {
            if (!Command.class.isAssignableFrom(clazz)) {
                continue;
            }
            CommandHandler annotation = clazz.getAnnotation(CommandHandler.class);
            commands.put(
                    annotation.value(),
                    (Class<? extends Command>) clazz
            );
        }
    }

    public Class<? extends Command> get(String name) {
        return commands.get(name);
    }
}