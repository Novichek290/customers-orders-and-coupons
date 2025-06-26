package dev.sorokin.service;

import dev.sorokin.contract.Command;
import dev.sorokin.controller.Console;

public class Plug implements Command {
    @Override
    public void execute() {
        System.out.println("Hello world");
    }
}
