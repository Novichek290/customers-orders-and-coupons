package dev.sorokin.controller;

import dev.sorokin.operation.Inputs;
import org.springframework.stereotype.Controller;

@Controller
public class Menu {
    public boolean menu(int i) {
        Console console = new Console();
        switch (i) {
            case 1: console.hint("Add client");
                return true;

            case 2: console.hint("Delete client");
                return true;

            case 3: console.hint("Edit profile");
                return true;

            case 4: console.hint("Add order");
                return true;

            case 5: console.hint("Find order");
                return true;

            case 6: console.hint("Edit coupons");
                return true;

            case 0:
                System.out.println("бывайте, ихтиандры хуевы");
                System.exit(1);
                return false;
            default:
                System.out.println("\n---------->Wrong parameter, try again<----------\n");
                return true;

        }
    }
}
