package DAL.DAO;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import DAL.POJO.Registration;
import DAL.POJO.Student;
import DAL.POJO.User;
import DAL.UTIL.UserUtil;

public class UserDao {
	public void prepare() {
		Transaction transaction=null;
		try(Session session = UserUtil.getSessionFactory().openSession()){
			transaction=session.beginTransaction();
		}
		catch(Exception e) {
			if(transaction!=null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	public void updateDao(long id,String password) {

		Transaction transaction = null;
		try (Session session = UserUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// Delete a student object
			User user = session.get(User.class, id);
			if (user != null) {
				String hql = "UPDATE User S Set "
						+ " S.password = :w " + "WHERE S.id = :id";
				Query query = session.createQuery(hql);
				query.setParameter("id",id);
				query.setParameter("w",password);
				int result = query.executeUpdate();
				System.out.println("Rows affected: " + result);
			}

			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	public void saveUser(User user) {
		Transaction transaction = null;
		try (Session session = UserUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			
			// operation 1
			Object object = session.save(user);
			
			// operation 2
			session.get(User.class, (Serializable) object);
			
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void deleteUser(long id) {

		Transaction transaction = null;
		try (Session session = UserUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// Delete a student object
			User user = session.get(User.class, id);
			if (user != null) {
				String hql = "DELETE FROM User " + "WHERE id = :userId";
				Query query = session.createQuery(hql);
				query.setParameter("userId", id);
				int result = query.executeUpdate();
				System.out.println("Rows affected: " + result);
			}

			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public User getUser(long id) {

		Transaction transaction = null;
		User user = null;
		try (Session session = UserUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// get an student object
			String hql = " FROM User S WHERE S.id = :userId";
			Query query = session.createQuery(hql);
			query.setParameter("userId", id);
			List results = query.getResultList();
			
			if (results != null && !results.isEmpty()) {
				user = (User) results.get(0);
			}
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return user;
	}
	
	public void updateUser(User std) {

		Transaction transaction = null;
		try (Session session = UserUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// Delete a student object
			User stdi = session.get(User.class, std.getId());
			if (stdi != null) {
				String hql = "UPDATE User S Set "
						+ " id = :id ,"
						+ " password = :password " + "WHERE id = :id";
				Query query = session.createQuery(hql);
				query.setParameter("id", std.getId());
				query.setParameter("password", std.getPassword());
				int result = query.executeUpdate();
				System.out.println("Rows affected: " + result);
			}

			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public List<User> getUser() {
		try (Session session = UserUtil.getSessionFactory().openSession()) {
			return session.createQuery("from User", User.class).list();
		}
	}
}
