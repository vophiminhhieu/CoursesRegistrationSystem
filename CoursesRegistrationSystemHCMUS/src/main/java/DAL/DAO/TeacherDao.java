package DAL.DAO;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import DAL.POJO.Student;
import DAL.POJO.Teacher;
import DAL.POJO.User;
import DAL.UTIL.UserUtil;

public class TeacherDao {
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
	public void saveTeacher(Teacher teacher) {
		Transaction transaction = null;
		try (Session session = UserUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			
			// operation 1
			Object object = session.save(teacher);
			
			// operation 2
			session.get(Teacher.class, (Serializable) object);
			
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void deleteTeacher(long id) {

		Transaction transaction = null;
		try (Session session = UserUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// Delete a student object
			Teacher teacher = session.get(Teacher.class, id);
			if (teacher != null) {
				String hql = "DELETE FROM Teacher " + "WHERE id = :teacherId";
				Query query = session.createQuery(hql);
				query.setParameter("teacherId", id);
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

	public Teacher getTeacher(long id) {

		Transaction transaction = null;
		Teacher teacher = null;
		try (Session session = UserUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// get an student object
			String hql = " FROM Teacher S WHERE S.id = :teacherId";
			Query query = session.createQuery(hql);
			query.setParameter("teacherId", id);
			List results = query.getResultList();
			
			if (results != null && !results.isEmpty()) {
				teacher = (Teacher) results.get(0);
			}
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return teacher;
	}

	public List<Teacher> getTeacher() {
		try (Session session = UserUtil.getSessionFactory().openSession()) {
			return session.createQuery("from Teacher", Teacher.class).list();
		}
	}
}
