package DAL.DAO;

import java.io.Serializable;
import java.util.List;
import java.util.ListIterator;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import DAL.POJO.Course;
import DAL.UTIL.UserUtil;

public class CourseDao {
	public void saveCourse(Course course) {
		Transaction transaction = null;
		try (Session session = UserUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			
			// operation 1
			Object object = session.save(course);
			
			// operation 2
			session.get(Course.class, (Serializable) object);
			
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void deleteCourse(String idSubject) {
		Transaction transaction = null;
		try (Session session = UserUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// Delete a student object
			Course course = session.get(Course.class, idSubject);
			if (course != null) {
				String hql = "DELETE FROM Course " + "WHERE idSubject = :id";
				Query query = session.createQuery(hql);
				query.setParameter("id", idSubject);
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
	public void deleteAll() {
		List<Course> list = getCourse();
		for(int i=0;i<list.size();i++) {
			deleteCourse(list.get(i).getIdSubject());
		}
	}
	public Course getCourse(String id) {

		Transaction transaction = null;
		Course course = null;
		try (Session session = UserUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// get an student object
			String hql = " FROM Course S WHERE S.idSubject = :id";
			Query query = session.createQuery(hql);
			query.setParameter("id", id);
			List results = query.getResultList();
			
			if (results != null && !results.isEmpty()) {
				course = (Course) results.get(0);
			}
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return course;
	}

	public List<Course> getCourse() {
		try (Session session = UserUtil.getSessionFactory().openSession()) {
			return session.createQuery("from Course", Course.class).list();
		}
	}
	public String[][] getListCourse(){
		List<Course> tea = getCourse();
		String[][] res = new String[tea.size()][8];
		ListIterator<Course> itr=tea.listIterator();
		int index=0;
		while(itr.hasNext()) {
			Course course = itr.next();
			res[index][0]=course.getIdSubject();
			res[index][1]=course.getNameSubject();
			res[index][2]=String.valueOf(course.getCredits());
			res[index][3]=course.getIdTeacher();
			res[index][4]=course.getNameTeacher();
			res[index][5]=course.getNameClass();
			res[index][6]=course.getDay();
			if(course.getTime().equals("ca1")) {
				res[index][7]="7:30 - 9:30";
			}
			else if(course.getTime().equals("ca2")) {
				res[index][7]="9:30 - 11:30";
			}
			else if(course.getTime().equals("ca3")) {
				res[index][7]="13:30 - 15:30";
			}
			else if(course.getTime().equals("ca4")) {
				res[index][7]="15:30 - 17:30";
			}
			else {
				res[index][7]=course.getTime();
			}
			index++;
		}
		return res;
	}
}
