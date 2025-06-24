package dev.sorokin.customers_orders_and_coupons;

import dev.sorokin.entity.Client;
import dev.sorokin.entity.Order;
import dev.sorokin.entity.Profile;
import dev.sorokin.service.ClientService;
import dev.sorokin.util.TransactionHelper;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


import java.time.LocalDateTime;

@DisplayName("MyFirstTest test testy test")
class CustomersOrdersAndCouponsApplicationTests {
	TransactionHelper transactionHelper;
	SessionFactory sessionFactory;

//	private Client client;
//	private Client client1;
//	private Client client2;

	private Profile profile;
	private Profile profile1;
	private Profile profile2;

	private Order order;
	private Order order1;
	private Order order2;

//	Подготовки данных (Arrange)
//	Вызова тестируемого метода (Act)
//	Проверки результата (Assert)
//	@Test
//	void testCreateClient() {
//		// Arrange (подготовка)
//		ClientService clientService = new ClientService(transactionHelper);
//		client.setName("name1");
//		client.setEmail("example@gmail.com");
//		client.setDateTime(LocalDateTime.now());
//
//		profile.setAddress("Some address");
//		profile.setPhone("812345");
//		profile.setClient(client);
//
//		order.setOrderDate(LocalDateTime.now());
//		order.setOrderStatus(OrderStatus.CONFIRMED);
//		order.setTotalAmount(17);
//		order.setClient(client);
//
//		client = clientService.create(client);
//
//		// Act (действие)
//		Client createClient = clientService.create(client);
//
//		// Assert (проверка)
//		assertNotNull(createClient.getId());
//		assertEquals(client.getName(), createClient);
//
//	}
	@Test
	void createClient_ShouldReturnClientWithId() {
		ClientService clientService = new ClientService(transactionHelper);
		Client client = new Client();
		client.setName("name1");
		client.setEmail("example@gmail.com");
		client.setDateTime(LocalDateTime.now());
		client = clientService.create(client);

		assertNotNull(client.getId());
		assertEquals("name1", client.getName());
	}

	@Test
	void addProfile_ShouldLinkProfileToClient() {
		ClientService clientService = new ClientService(transactionHelper);
	}

	@Test
	void createOrder_ShouldAssignOrderToClient() {

	}

}
