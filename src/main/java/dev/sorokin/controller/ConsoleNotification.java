package dev.sorokin.controller;

import dev.sorokin.util.Color;
import org.springframework.stereotype.Component;

@Component
public class ConsoleNotification {
    public void notification(String notification) {
        System.out.println(Color.getYELLOW() + notification + Color.getRESET());
    }
    public void notification(double notification) {
        System.out.println(Color.getYELLOW() + notification + Color.getRESET());
    }

    public void hint(String n) {
        System.out.println(Color.getGREEN() + n + Color.getRESET());
    }

    public void border() {
        System.out.print(Color.getBLUE() + "-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-");
        System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-" + Color.getRESET());
    }
}
