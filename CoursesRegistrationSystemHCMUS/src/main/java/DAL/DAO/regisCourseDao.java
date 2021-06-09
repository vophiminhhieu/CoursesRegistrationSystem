package DAL.DAO;


import java.io.Serializable;
import java.util.List;
import java.util.ListIterator;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import DAL.POJO.regisCourse;
import DAL.POJO.regisCourse.Pk;
import DAL.UTIL.UserUtil;

public class regisCourseDao {
	public void saveRegisCourse(regisCourse regCour) {
		Transaction transaction = null;
		try (Session session = UserUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			
			// operation 1
			Object object = session.save(regCour);
			
			// operation 2
			session.get(regisCourse.class, (Serializable) object);
			
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void deleteRegisCourse(Pk pk) {

		Transaction transaction = null;
		try (Session session = UserUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// Delete a student object
			regisCourse regCour = session.get(regisCourse.class, pk);
			if (regCour != null) {
				String hql = "DELETE FROM regisCourse S " + "WHERE S.pk.idStudent = :stu and S.pk.idCourse = :course";
				Query query = session.createQuery(hql);
				query.setParameter("stu", pk.getIdStudent());
				query.setParameter("course",pk.getIdCourse());
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

	public regisCourse getRegisCourse(Pk pk) {

		Transaction transaction = null;
		regisCourse regCour = null;
		try (Session session = UserUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// get an student object
			String hql = " FROM regisCourse S WHERE S.pk.idStudent=:stu and S.pk.idCourse=:cour";
			Query query = session.createQuery(hql);
			query.setParameter("stu", pk.getIdStudent());
			query.setParameter("cour", pk.getIdCourse());
			List results = query.getResultList();
			
			if (results != null && !results.isEmpty()) {
				regCour = (regisCourse) results.get(0);
			}
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return regCour;
	}

	public List<regisCourse> getSemester() {
		try (Session session = UserUtil.getSessionFactory().openSession()) {
			return session.createQuery("from regisCourse",regisCourse.class).list();
		}
	}
	
	
}
