package tutorial.hibernate.relationships.config;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import tutorial.hibernate.relationships.model.Address;
import tutorial.hibernate.relationships.model.Customer;
import tutorial.hibernate.relationships.model.Item;
import tutorial.hibernate.relationships.model.Order;

public abstract class HibernateUtil {

	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				Configuration configuration = new Configuration();
				// Hibernate settings equivalent to hibernate.cfg.xml's properties

				Properties settings = new Properties();
				settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
				settings.put(Environment.URL, "jdbc:mysql://localhost:3306/shopping?useSSL=false");
				settings.put(Environment.USER, "root");
				settings.put(Environment.PASS, "Bhushan@007");

				settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
				settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
				settings.put(Environment.HBM2DDL_AUTO, "validate");

				settings.put(Environment.SHOW_SQL, "true");
				settings.put(Environment.FORMAT_SQL, "true");

				configuration.setProperties(settings);

				configuration.addAnnotatedClass(Customer.class);
				configuration.addAnnotatedClass(Address.class);
				configuration.addAnnotatedClass(Order.class);
				configuration.addAnnotatedClass(Item.class);

				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
						.applySettings(configuration.getProperties()).build();

				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sessionFactory;
	}

}
