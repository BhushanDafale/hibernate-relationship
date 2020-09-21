package tutorial.hibernate.relationships;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import tutorial.hibernate.relationships.dao.HibernateDao;
import tutorial.hibernate.relationships.model.Address;
import tutorial.hibernate.relationships.model.Customer;
import tutorial.hibernate.relationships.model.Item;
import tutorial.hibernate.relationships.model.Order;

public class App {
	public static void main(String[] args) throws ParseException {

		// One-to-One mapping
		Customer customer = new Customer();
		customer.setName("Bhushan Dafale");

		Address address = new Address();
		address.setAddress("AUS");

		customer.setAddress(address);
		
		// One-to-Many = Many-to-One
		Order order_1 = new Order();
		order_1.setOrderDate("2020-05-14");
		order_1.setAmount(456.34);
		order_1.setCustomer(customer);

		Order order_2 = new Order();
		order_2.setOrderDate("2020-05-14");
		order_2.setAmount(199.00);
		order_2.setCustomer(customer);

		// Many-to-Many
		List<Order> orders_1 = new ArrayList<Order>();
		orders_1.add(order_1);
		
		Item item_1 = new Item();
		item_1.setName("A-item");
		item_1.setDescription("A-description");
		item_1.setOrders(orders_1);

		Item item_2 = new Item();
		item_2.setName("B-item");
		item_2.setDescription("B-description");
		item_2.setOrders(orders_1);
		
		Item item_3 = new Item();
		item_3.setName("C-item");
		item_3.setDescription("C-description");
		item_3.setOrders(Arrays.asList(order_2));
			
		List<Item> items_1 = new ArrayList<Item>();
		items_1.add(item_1);
		items_1.add(item_2);
		order_1.setItems(items_1);

		List<Item> items_2 = new ArrayList<Item>();
		items_2.add(item_3);
		order_2.setItems(items_2);

		Set<Order> orders = new HashSet<Order>();
		orders.add(order_1);
		orders.add(order_2);

		// set one-to-many, many-to-one and many-to-many
		customer.setOrders(orders);

		HibernateDao.saveRecord(customer);

	}
}
