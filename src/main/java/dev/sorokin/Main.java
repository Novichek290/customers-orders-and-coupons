package dev.sorokin;

import dev.sorokin.configuration.HibernateConfiguration;
import dev.sorokin.controller.Console;
import dev.sorokin.util.Color;
import dev.sorokin.entity.Client;
import dev.sorokin.entity.Order;
import dev.sorokin.service.ClientService;
import dev.sorokin.service.OrderService;
import dev.sorokin.util.OrderStatus;
import dev.sorokin.service.ProfileService;
import dev.sorokin.util.TransactionHelper;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDateTime;


public class Main {
    public static void main(String[] args) {

        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext(HibernateConfiguration.class)) {
            ClientService clientService = context.getBean(ClientService.class);
            OrderService orderService = context.getBean(OrderService.class);
            ProfileService profileService = context.getBean(ProfileService.class);
            Console console = context.getBean(Console.class);

            notification("сразу после context");

            Client client = Client.builder()
                    .name("Seller228")
                    .email("mail")
                    .dateTime(LocalDateTime.now())
                    .build();

            notification("клиент создан, но не добавлен в БД");

            Order car = Order.builder()
                    .totalAmount(2)
                    .orderDate(LocalDateTime.now())
                    .orderStatus(OrderStatus.PROCESSING)
                    .client(client)
                    .build();

            notification("создан заказ:");
            System.out.printf(Color.getCYAN() + "total Amount: %s | orderDate: %-12s | status: " + Color.getPURPLE() + "%-12s\n",
                    car.getTotalAmount(),
                    car.getOrderDate(),
                    car.getOrderStatus());
            System.out.print(Color.getRESET());

            client.addOrder(car);
            orderService.orderCreate(car);

            notification("клиенту установил заказ");

            borders();
            console.printAll();
        }
    }
    public static void borders() {
        System.out.print(Color.getBLUE() + "-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-");
        System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-" + Color.getRESET());
    }

    public static void deleteAllFromDB() {
        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext(HibernateConfiguration.class)) {
            Console console = context.getBean(Console.class);
            ClientService clientService = context.getBean(ClientService.class);

            for (Client mrX : console.getAll()) {
                clientService.delete(mrX.getId());
            }
        }
    }

    public static void notification(String notification) {
        System.out.println(Color.getYELLOW() + notification + Color.getRESET());
    }
}
