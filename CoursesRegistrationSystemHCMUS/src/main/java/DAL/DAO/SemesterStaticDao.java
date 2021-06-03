package DAL.DAO;


import java.io.Serializable;
import java.util.List;
import java.util.ListIterator;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import DAL.POJO.Semester;
import DAL.POJO.Teacher;
import DAL.POJO.SemesterStatic;
import DAL.POJO.SemesterStatic.Pk;
import DAL.UTIL.UserUtil;

public class SemesterStaticDao {
	public void saveSemesterStatic(SemesterStatic semester) {
		Transaction transaction = null;
		try (Session session = UserUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			
			// operation 1
			Object object = session.save(semester);
			
			// operation 2
			session.get(SemesterStatic.class, (Serializable) object);
			
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void deleteSemesterStatic(Pk pk) {

		Transaction transaction = null;
		try (Session session = UserUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// Delete a student object
			SemesterStatic semester = session.get(SemesterStatic.class, pk);
			if (semester != null) {
				String hql = "DELETE FROM SemesterStatic S " + "WHERE S.pk.name = :name and S.pk.year = :year";
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

	public SemesterStatic getSemesterStatic(Pk pk) {

		Transaction transaction = null;
		SemesterStatic semester = null;
		try (Session session = UserUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// get an student object
			String hql = " FROM SemesterStatic S WHERE S.pk.name=:name and S.pk.year=:year";
			Query query = session.createQuery(hql);
			query.setParameter("name", pk.getName());
			query.setParameter("year", pk.getYear());
			List results = query.getResultList();
			
			if (results != null && !results.isEmpty()) {
				semester = (SemesterStatic) results.get(0);
			}
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return semester;
	}

	public List<SemesterStatic> getSemesterStatic() {
		try (Session session = UserUtil.getSessionFactory().openSession()) {
			return session.createQuery("from SemesterStatic",SemesterStatic.class).list();
		}
	}
	
}
