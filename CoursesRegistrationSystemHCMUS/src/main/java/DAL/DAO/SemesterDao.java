package DAL.DAO;


import java.io.Serializable;
import java.util.List;
import java.util.ListIterator;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import DAL.POJO.Semester;
import DAL.POJO.Teacher;
import DAL.POJO.Semester.Pk;
import DAL.UTIL.UserUtil;

public class SemesterDao {
	public void saveSemester(Semester semester) {
		Transaction transaction = null;
		try (Session session = UserUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			
			// operation 1
			Object object = session.save(semester);
			
			// operation 2
			session.get(Semester.class, (Serializable) object);
			
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void deleteSemester(Pk pk) {

		Transaction transaction = null;
		try (Session session = UserUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// Delete a student object
			Semester semester = session.get(Semester.class, pk);
			if (semester != null) {
				String hql = "DELETE FROM Semester S " + "WHERE S.pk.name = :name and S.pk.year = :year";
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

	public Semester getSemester(Pk pk) {

		Transaction transaction = null;
		Semester semester = null;
		try (Session session = UserUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// get an student object
			String hql = " FROM Semester S WHERE S.pk.name=:name and S.pk.year=:year";
			Query query = session.createQuery(hql);
			query.setParameter("name", pk.getName());
			query.setParameter("year", pk.getYear());
			List results = query.getResultList();
			
			if (results != null && !results.isEmpty()) {
				semester = (Semester) results.get(0);
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

	public List<Semester> getSemester() {
		try (Session session = UserUtil.getSessionFactory().openSession()) {
			return session.createQuery("from Semester",Semester.class).list();
		}
	}
	
	public String[][] getListSemester(){
		List<Semester> sem = getSemester();
		String[][] res = new String[sem.size()][4];
		ListIterator<Semester> itr=sem.listIterator();
		int index=0;
		while(itr.hasNext()) {
			Semester semester = itr.next();
			res[index][0]=semester.getPk().getName();
			res[index][1]= String.valueOf(semester.getPk().getYear());
			res[index][2]=semester.getStart_Date();
			res[index][3]=semester.getEnd_Date();
			index++;
		}
		return res;
	}
}
