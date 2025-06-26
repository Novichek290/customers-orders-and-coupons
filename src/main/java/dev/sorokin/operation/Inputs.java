package dev.sorokin.operation;

import dev.sorokin.contract.Command;
import dev.sorokin.service.Plug;
import dev.sorokin.service.command.DefaultCommand;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;


@Getter
public enum Inputs {
    ONE(1, new Plug()),
    DEFAULT(0, new DefaultCommand());




    private final Command command;
    private final int input;

    Inputs(int input, Command command) {

        this.command = command;
        this.input = input;
    }

    public static Inputs findByInputOrDefault(int i) {
        return Arrays.stream(Inputs.values())
                .filter(input -> i == input.getInput())
                .findFirst()
                .orElse(DEFAULT);
    }
}
