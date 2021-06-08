package DAL.DAO;


import java.io.Serializable;
import java.util.List;
import java.util.ListIterator;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import DAL.POJO.Registration;
import DAL.POJO.Student;
import DAL.UTIL.UserUtil;
import DAL.POJO.Registration.Pk;


public class RegistrationDao {
	public void saveRegistration(Registration registration) {
		Transaction transaction = null;
		try (Session session = UserUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			
			// operation 1
			Object object = session.save(registration);
			
			// operation 2
			session.get(Registration.class, (Serializable) object);
			
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void deleteRegistration(Pk pk) {

		Transaction transaction = null;
		try (Session session = UserUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// Delete a student object
			Registration registration = session.get(Registration.class, pk);
			if (registration != null) {
				String hql = "DELETE FROM Registration S " + "WHERE S.pk.name = :name and S.pk.year = :year";
				Query query = session.createQuery(hql);
				query.setParameter("name", pk.getName());
				query.setParameter("year",pk.getYear());
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

	public Registration getRegistration(Pk pk) {

		Transaction transaction = null;
		Registration registration = null;
		try (Session session = UserUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// get an student object
			String hql = " FROM Registration S WHERE S.pk.name=:name and S.pk.year=:year";
			Query query = session.createQuery(hql);
			query.setParameter("name", pk.getName());
			query.setParameter("year", pk.getYear());
			List results = query.getResultList();
			
			if (results != null && !results.isEmpty()) {
				registration = (Registration) results.get(0);
			}
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return registration;
	}

	public List<Registration> getRegistration() {
		try (Session session = UserUtil.getSessionFactory().openSession()) {
			return session.createQuery("from Registration",Registration.class).list();
		}
	}
	public void offAll() {
		List<Registration> l = getRegistration();
		for(int i=0;i<l.size();i++) {
			updateOFFRegistration(l.get(i));
		}
		CourseDao dao = new CourseDao();
		dao.deleteAll();
	}
	
	public void updateOFFRegistration(Registration std) {

		Transaction transaction = null;
		try (Session session = UserUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// Delete a student object
			Registration registration = session.get(Registration.class, std.getPk());
			if (registration != null) {
				String hql = "UPDATE Registration S Set "
						+ " S.isCurrent = :curr " + "WHERE S.pk.name = :name and S.pk.year=:year";
				Query query = session.createQuery(hql);
				query.setParameter("curr",false);
				query.setParameter("name",std.getPk().getName());
				query.setParameter("year",std.getPk().getYear());
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
	
	public String[][] getListRegistration(){
		List<Registration> sem = getRegistration();
		String[][] res = new String[sem.size()][5];
		ListIterator<Registration> itr=sem.listIterator();
		int index=0;
		while(itr.hasNext()) {
			Registration registration = itr.next();
			res[index][0]=registration.getPk().getName();
			res[index][1]= String.valueOf(registration.getPk().getYear());
			res[index][2]=registration.getStart_Date();
			res[index][3]=registration.getEnd_Date();
			res[index][4]=registration.getCurrent()?"Yes":"No";
			
			index++;
		}
		return res;
	}
}
