package dev.sorokin.controller;

import dev.sorokin.service.InputManager;
import dev.sorokin.service.command.ClientCreateCommand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@AllArgsConstructor
//@Component
public class Runner {
    private final DBPrintout dbPrintout;
    private final ClientCreateCommand clientCreateCommand;
    private final InputManager inputManager;
    private final ConsoleNotification console;

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        while (isRunning) {
            printMenu();
            int choice = scanner.nextInt();
            isRunning = switcher(choice);
        }
        scanner.close();
        System.out.println("Бывайте, ихтиандры хуевы!");
    }

    private void printMenu() {
        System.out.println("""
                    Выберите действие:
                    1. Добавить клиента
                    2. Удалить клиента
                    3. Редактировать профиль
                    4. Добавить заказ
                    5. Редактировать купоны
                    6. Найти заказы
                    7. Выход
                    
                    Введите номер команды:
                    """);
    }

    private boolean switcher(int i) {
        switch (i) {
            case 1:
                var client = inputManager.addClient();
                clientCreateCommand.execute();
                return true;

            case 6:
                dbPrintout.printAll();
                return true;

            case 7: return false;

            default:
                console.notification(">>>>>WRONG REQUEST<<<<<");
                return true;
        }
    }
}
