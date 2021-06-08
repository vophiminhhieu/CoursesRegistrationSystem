package DAL.DAO;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import DAL.POJO.Classs;
import DAL.POJO.JoinClass;
import DAL.POJO.Semester;
import DAL.POJO.Student;
import DAL.POJO.Teacher;
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
	public List<JoinClass> getJoinClass(String idClass) {
		List<JoinClass> _class = getJoinClass();
		ListIterator<JoinClass> itr=_class.listIterator();
		List<JoinClass> res = new ArrayList<JoinClass>();
		while(itr.hasNext()) {
			JoinClass joinClass = itr.next();
			if(joinClass.getPk().getIdClass().equals(idClass)&&joinClass.getDone()==false) {
				res.add(joinClass);
			}
		}
		return res;
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
	
	public List<Student> getStudent(String idClass){
		List<JoinClass> _class = getJoinClass();
		ListIterator<JoinClass> itr=_class.listIterator();
		StudentDao studentDao = new StudentDao();
		List<Student> std = new ArrayList<Student>();
		while(itr.hasNext()) {
			JoinClass joinClass = itr.next();
			if(joinClass.getPk().getIdClass().equals(idClass)&&joinClass.getDone()) {
				std.add(studentDao.getStudent(joinClass.getPk().getIdStudent()));
			}
		}
		return std;
	}
	public String[][] getStringStudent(String idClass){
		List<Student> tea = getStudent(idClass);
		String[][] res = new String[tea.size()][10];
		ListIterator<Student> itr=tea.listIterator();
		int index=0;
		while(itr.hasNext()) {
			Student student = itr.next();
			res[index][0]=String.valueOf(student.getId());
			res[index][1]=student.getName();
			res[index][2]=student.getBirthDate();
			res[index][3]=student.getBirthPlace();
			res[index][4]=student.getSex();
			res[index][5]=student.getEmail();
			res[index][6]=student.getAddress();
			res[index][7]=student.getPhone();
			res[index][8]=student.getMajor();
			res[index][9]=student.getStartDate();
			index++;
		}
		return res;
	}
	
}
