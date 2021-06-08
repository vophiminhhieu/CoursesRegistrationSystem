package DAL.DAO;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import DAL.POJO.Course;
import DAL.POJO.Subject;
import DAL.UTIL.UserUtil;

public class SubjectDao {
	public void saveSubject(Subject subject) {
		Transaction transaction = null;
		try (Session session = UserUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			
			// operation 1
			Object object = session.save(subject);
			
			// operation 2
			session.get(Subject.class, (Serializable) object);
			
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void deleteSubject(String id) {

		Transaction transaction = null;
		try (Session session = UserUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// Delete a student object
			Subject subject = session.get(Subject.class, id);
			if (subject != null) {
				String hql = "DELETE FROM Subject " + "WHERE id = :subjectId";
				Query query = session.createQuery(hql);
				query.setParameter("subjectId", id);
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
		CourseDao dao = new CourseDao();
		dao.deleteCourse(id);
	}

	public Subject getSubject(String id) {

		Transaction transaction = null;
		Subject subject = null;
		try (Session session = UserUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// get an student object
			String hql = " FROM Subject S WHERE S.id = :subjectId";
			Query query = session.createQuery(hql);
			query.setParameter("subjectId", id);
			List results = query.getResultList();
			
			if (results != null && !results.isEmpty()) {
				subject = (Subject) results.get(0);
			}
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return subject;
	}

	public List<Subject> getStudent() {
		try (Session session = UserUtil.getSessionFactory().openSession()) {
			return session.createQuery("from Subject",Subject.class).list();
		}
	}
}
