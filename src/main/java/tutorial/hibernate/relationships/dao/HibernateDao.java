package tutorial.hibernate.relationships.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import tutorial.hibernate.relationships.config.HibernateUtil;

public class HibernateDao {

	public static <T> Integer saveRecord(T entity) {
		Integer generatedId = null;

		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			generatedId = (Integer) session.save(entity);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		}

		return generatedId;
	}

}
