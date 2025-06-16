package dev.sorokin.design;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class Color {
    @Getter
    private static final String RESET = "\u001B[0m";
    @Getter
    private static final String BLACK = "\u001B[30m";
    @Getter
    private static final String RED = "\u001B[31m";
    @Getter
    private static final String GREEN = "\u001B[32m";
    @Getter
    private static final String YELLOW = "\u001B[33m";
    @Getter
    private static final String BLUE = "\u001B[34m";
    @Getter
    private static final String PURPLE = "\u001B[35m";
    @Getter
    private static final String CYAN = "\u001B[36m";
    @Getter
    private static final String WHITE = "\u001B[37m";

}
