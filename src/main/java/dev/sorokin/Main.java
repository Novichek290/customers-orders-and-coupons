package dev.sorokin;

import dev.sorokin.configuration.HibernateConfiguration;
import dev.sorokin.design.Color;
import dev.sorokin.entity.Client;
import dev.sorokin.entity.Order;
import dev.sorokin.entity.Profile;
import dev.sorokin.service.ClientService;
import dev.sorokin.service.OrderService;
import dev.sorokin.service.OrderStatus;
import dev.sorokin.service.ProfileService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {

        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext(HibernateConfiguration.class)) {
            ClientService clientService = context.getBean(ClientService.class);
            OrderService orderService = context.getBean(OrderService.class);
            ProfileService profileService = context.getBean(ProfileService.class);

            System.out.println(Color.getYELLOW() + "сразу после context" + Color.getRESET());

//            Client client = Client.builder()
//                    .name("Seller228")
//                    .email("IFucker228@in.ass")
//                    .dateTime(LocalDateTime.now())
//                    .build();

            Client client = ClientService.getById(80L);
            System.out.println(Color.getYELLOW() + "вернул клиента" + Color.getRESET());

            Order car = Order.builder()
                    .totalAmount(2)
                    .orderDate(LocalDateTime.now())
                    .orderStatus(OrderStatus.PROCESSING)
                    .client(client)
                    .build();

            System.out.println(Color.getYELLOW() + "создал заказ:" + Color.getRESET());
            System.out.printf(Color.getCYAN() + "total Amount: %s | orderDate: %-12s | status: " + Color.getPURPLE() + "%-12s\n", car.getTotalAmount(), car.getOrderDate(), car.getOrderStatus());
            System.out.print(Color.getRESET());

//            Profile profile = Profile.builder()
//                    .address("Gods heaven, 1")
//                    .phone("not-your-business")
//                    .build();

            client.addOrder(car);
            orderService.orderCreate(car);



            System.out.println(Color.getYELLOW() + "клиенту установил заказ" + Color.getRESET());
            borders();

            borders();


//            for(Client mrX : clientService.getAll()) {
//                clientService.delete(mrX.getId());
//            }
            clientService.printAll();
        }
    }
    public static void borders() {
        System.out.print(Color.getBLUE() + "-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-");
        System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-" + Color.getRESET());
    }
}
