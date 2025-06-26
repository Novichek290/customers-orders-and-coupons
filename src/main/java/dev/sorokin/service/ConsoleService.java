package dev.sorokin.service;

import dev.sorokin.operation.Inputs;

public class ConsoleService {
    public void switcher(int nextInt) {
        var inputs = Inputs.findByInputOrDefault(nextInt);
        var command = inputs.getCommand();
        command.execute();
    }
}
