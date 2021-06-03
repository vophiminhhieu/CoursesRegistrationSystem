package DAL.DAO;

import java.io.Serializable;
import java.util.List;
import java.util.ListIterator;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import DAL.POJO.Classs;

import DAL.POJO.Semester;
import DAL.POJO.Student;
import DAL.UTIL.UserUtil;

public class ClassDao {
	public void saveClasss(Classs _class) {
		Transaction transaction = null;
		try (Session session = UserUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			
			// operation 1
			Object object = session.save(_class);
			
			// operation 2
			session.get(Classs.class, (Serializable) object);
			
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void deleteClasss(String id) {

		Transaction transaction = null;
		try (Session session = UserUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// Delete a student object
			Classs student = session.get(Classs.class, id);
			if (student != null) {
				String hql = "DELETE FROM Classs " + "WHERE id = :studentId";
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
	public Classs getClasss(String id) {

		Transaction transaction = null;
		Classs student = null;
		try (Session session = UserUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// get an student object
			String hql = " FROM Classs S WHERE S.id = :studentId";
			Query query = session.createQuery(hql);
			query.setParameter("studentId", id);
			List results = query.getResultList();
			
			if (results != null && !results.isEmpty()) {
				student = (Classs) results.get(0);
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
	public List<Classs> getClasss() {
		try (Session session = UserUtil.getSessionFactory().openSession()) {
			return session.createQuery("from Classs",Classs.class).list();
		}
	}
	
	public String[][] getListClass(){
		List<Classs> sem = getClasss();
		String[][] res = new String[sem.size()][5];
		ListIterator<Classs> itr=sem.listIterator();
		JoinClassDao deo = new JoinClassDao();
		int index=0;
		while(itr.hasNext()) {
			Classs classs = itr.next();
			res[index][0]=classs.getId();
			res[index][1]=classs.getName();
			int[] nn = deo.getNumberStudent(classs.getId());
			res[index][2]=String.valueOf(nn[0]);
			res[index][3]=String.valueOf(nn[1]);
			res[index][4]=String.valueOf(nn[0]+nn[1]);
			index++;
		}
		return res;
	}
}
