package dev.sorokin;

import dev.sorokin.configuration.HibernateConfiguration;
import dev.sorokin.controller.Console;
import dev.sorokin.controller.DBPrintout;
import dev.sorokin.service.UniversalService;
import dev.sorokin.util.Color;
import dev.sorokin.entity.Client;
import dev.sorokin.entity.ClientOrder;
import dev.sorokin.service.ClientService;
import dev.sorokin.service.OrderService;
import dev.sorokin.util.OrderStatus;
import dev.sorokin.service.ProfileService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {

        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext(HibernateConfiguration.class)) {
            ClientService clientService = context.getBean(ClientService.class);
            OrderService orderService = context.getBean(OrderService.class);
            ProfileService profileService = context.getBean(ProfileService.class);
            Console console = context.getBean(Console.class);
            DBPrintout dbPrintout = context.getBean(DBPrintout.class);
            UniversalService universalService = context.getBean(UniversalService.class);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

            console.notification("сразу после context создаю клиента");

            Client client = Client.builder()
                    .name("name2")
                    .dateTime(LocalDateTime.now())
                    .build();
            client.setEmail("abc223@gmail.com");

            console.notification("клиент создан, но не добавлен в БД:");
            System.out.printf(Color.getCYAN() + "ID: %-12s " +
                            "| Name: %-12s " +
                            "| Email: %-26s " +
                            "| At: %-26s " +
                            "| profile: %-12s " +
                            "| order: %-12s \n",
                    client.getId(),
                    client.getName(),
                    client.getEmail(),
                    client.getDateTime() != null ? client.getDateTime().format(formatter) : "null",
                    client.getProfile() != null ? client.getProfile().getId() : null,
                    client.getOrder().isEmpty() ? client.getOrder().size() : null
            );
            System.out.print(Color.getRESET());

            console.notification("создаю заказ");

            ClientOrder car = ClientOrder.builder()
                    .orderStatus(OrderStatus.PROCESSING)
                    .orderDate(LocalDateTime.now())
                    .client(client)
                    .build();
            car.setTotalAmount(5476122.6578);

            console.notification("создан заказ:");

            System.out.printf(Color.getCYAN() + "total Amount: %s | orderDate: %-12s | status: " + Color.getPURPLE() + "%-12s\n",
                    car.getTotalAmount(),
                    car.getOrderDate() != null ? car.getOrderDate().format(formatter) : null,
                    car.getOrderStatus());
            System.out.print(Color.getRESET());

            console.notification("добавляю, связываю");

//            client.addOrder(car);
//            car.setClient(client);
//            clientService.create(client);

            console.notification("готово");
            console.notification("клиенту привязал заказ");

            dbPrintout.printAll();


        }
    }

    public static void deleteAllFromDB() {
        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext(HibernateConfiguration.class)) {
            Console console = context.getBean(Console.class);
            ClientService clientService = context.getBean(ClientService.class);

            if(clientService.getAll().isEmpty()) {
                console.notification("Data base is empty");
            } else {
                clientService.getAll().clear();
                if(clientService.getAll().isEmpty()) {
                    console.notification("База даных клиентов успешно очищена");
                    console.border();
                }
            }
        }
    }
}
