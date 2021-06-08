package DAL.DAO;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import DAL.POJO.Student;
import DAL.POJO.User;
import DAL.UTIL.UserUtil;

public class StudentDao {
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
	public void saveStudent(Student student) {
		Transaction transaction = null;
		try (Session session = UserUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			
			// operation 1
			Object object = session.save(student);
			
			// operation 2
			session.get(Student.class, (Serializable) object);
			
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void deleteStudent(long id) {

		Transaction transaction = null;
		try (Session session = UserUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// Delete a student object
			Student student = session.get(Student.class, id);
			if (student != null) {
				String hql = "DELETE FROM Student " + "WHERE id = :studentId";
				Query query = session.createQuery(hql);
				query.setParameter("studentId", id);
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
	public void updateStudent(Student std) {

		Transaction transaction = null;
		try (Session session = UserUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// Delete a student object
			Student student = session.get(Student.class, std.getId());
			if (student != null) {
				String hql = "UPDATE Student S Set "
						+ " name = :name ,"
						+ " birthDate = :birthDate ,"
						+ " birthPlace = :birthPlace ,"
						+ " sex = :sex ,"
						+ " phone = :phone ,"
						+ " email = :email ,"
						+ " address = :address ,"
						+ " major = :major ,"
						+ " startDate = :startDate " + "WHERE id = :id";
				Query query = session.createQuery(hql);
				query.setParameter("id", std.getId());
				query.setParameter("name", std.getName());
				query.setParameter("birthDate", std.getBirthDate());
				query.setParameter("birthPlace", std.getBirthPlace());
				query.setParameter("sex", std.getSex());
				query.setParameter("address", std.getAddress());
				query.setParameter("phone", std.getPhone());
				query.setParameter("email", std.getEmail());
				query.setParameter("major", std.getMajor());
				query.setParameter("startDate", std.getStartDate());
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

	public Student getStudent(long id) {

		Transaction transaction = null;
		Student student = null;
		try (Session session = UserUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// get an student object
			String hql = " FROM Student S WHERE S.id = :studentId";
			Query query = session.createQuery(hql);
			query.setParameter("studentId", id);
			List results = query.getResultList();
			
			if (results != null && !results.isEmpty()) {
				student = (Student) results.get(0);
			}
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return student;
	}

	public List<Student> getStudent() {
		try (Session session = UserUtil.getSessionFactory().openSession()) {
			return session.createQuery("from Student", Student.class).list();
		}
	}
}
