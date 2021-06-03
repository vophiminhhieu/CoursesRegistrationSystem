package DAL.DAO;


import java.io.Serializable;
import java.util.List;
import java.util.ListIterator;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import DAL.POJO.Classs;
import DAL.POJO.JoinClass;
import DAL.POJO.Semester;
import DAL.POJO.Student;
import DAL.POJO.JoinClass.Pk;
import DAL.UTIL.UserUtil;

public class JoinClassDao {
	public void saveJoinClass(JoinClass joinClass) {
		Transaction transaction = null;
		try (Session session = UserUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			
			// operation 1
			Object object = session.save(joinClass);
			
			// operation 2
			session.get(JoinClass.class, (Serializable) object);
			
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void deleteJoinClass(Pk pk) {

		Transaction transaction = null;
		try (Session session = UserUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// Delete a student object
			JoinClass joinClass = session.get(JoinClass.class, pk);
			if (joinClass != null) {
				String hql = "DELETE FROM JoinClass S " + "WHERE S.pk.idClass = :idClass and S.pk.idStudent = :idStudent";
				Query query = session.createQuery(hql);
				query.setParameter("idClass", pk.getIdClass());
				query.setParameter("idStudent",pk.getIdStudent());
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
	public void deleteJoinClass(String idClass) {

		List<JoinClass> _class = getJoinClass();
		ListIterator<JoinClass> itr=_class.listIterator();
		while(itr.hasNext()) {
			JoinClass joinClass = itr.next();
			if(joinClass.getPk().getIdClass().equals(idClass)) {
				Pk pk = joinClass.getPk();
				deleteJoinClass(pk);
			}
		}
	}
	public JoinClass getJoinClass(Pk pk) {

		Transaction transaction = null;
		JoinClass joinClass = null;
		try (Session session = UserUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// get an student object
			String hql = " FROM JoinClass S WHERE S.pk.idClass=:idClass and S.pk.idStudent=:idStudent";
			Query query = session.createQuery(hql);
			query.setParameter("idClass", pk.getIdClass());
			query.setParameter("idStudent", pk.getIdStudent());
			List results = query.getResultList();
			
			if (results != null && !results.isEmpty()) {
				joinClass = (JoinClass) results.get(0);
			}
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return joinClass;
	}

	public List<JoinClass> getJoinClass() {
		try (Session session = UserUtil.getSessionFactory().openSession()) {
			return session.createQuery("from JoinClass",JoinClass.class).list();
		}
	}

	public int[] getNumberStudent(String idClass) {
		int[] nn = new int[2];
		List<JoinClass> _class = getJoinClass();
		ListIterator<JoinClass> itr=_class.listIterator();
		StudentDao studentDao = new StudentDao();
		Student std = new Student();
		int countMale=0;
		int countFemale=0;
		while(itr.hasNext()) {
			JoinClass joinClass = itr.next();
			if(joinClass.getPk().getIdClass().equals(idClass)) {
				if(joinClass.getDone()==true) {
					std=studentDao.getStudent(joinClass.getPk().getIdStudent());
					if(std.getSex().equals("Nam")) {
						countMale++;
					}
					else {
						countFemale++;
					}
				}
			}
		}
		nn[0]=countMale;
		nn[1]=countFemale;
		return nn;
	}
}
