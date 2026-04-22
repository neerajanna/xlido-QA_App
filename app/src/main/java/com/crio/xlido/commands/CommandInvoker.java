package com.crio.xlido.commands;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandInvoker {

    private final Map<String, ICommand> commandMap = new HashMap<>();

    public void register(String command, ICommand handler) {
        commandMap.put(command, handler);
    }

    public void invokeCommand(List<String> tokens) {
        ICommand command = commandMap.get(tokens.get(0));
        if (command == null) {
            throw new RuntimeException("INVALID_COMMAND");
        }
        command.invoke(tokens);
    }
}
