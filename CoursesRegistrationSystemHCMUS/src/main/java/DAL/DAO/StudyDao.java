package DAL.DAO;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import DAL.POJO.JoinClass;
import DAL.POJO.Study;
import DAL.POJO.Study.Pk;
import DAL.UTIL.UserUtil;


public class StudyDao {
	public void saveStudy(Study study) {
		Transaction transaction = null;
		try (Session session = UserUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			
			// operation 1
			Object object = session.save(study);
			
			// operation 2
			session.get(Study.class, (Serializable) object);
			
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	public void deleteStudy(Pk pk) {

		Transaction transaction = null;
		try (Session session = UserUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// Delete a student object
			Study study = session.get(Study.class, pk);
			if (study != null) {
				String hql = "DELETE FROM Study S " + "WHERE S.pk.idStudent = :idStd and S.pk.idSubject = :idSubject";
				Query query = session.createQuery(hql);
				query.setParameter("idStd", pk.getIdStudent());
				query.setParameter("idSubject",pk.getIdSubject());
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

	public Study getStudy(Pk pk) {

		Transaction transaction = null;
		Study study=null;
		try (Session session = UserUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// get an student object
			String hql = " FROM Study S WHERE S.pk.idStudent=:idStd and S.pk.idSubject=:idSbj";
			Query query = session.createQuery(hql);
			query.setParameter("idStd", pk.getIdStudent());
			query.setParameter("idSbj", pk.getIdSubject());
			List results = query.getResultList();
			
			if (results != null && !results.isEmpty()) {
				study = (Study) results.get(0);
			}
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return study;
	}

	public List<Study> getStudy() {
		try (Session session = UserUtil.getSessionFactory().openSession()) {
			return session.createQuery("from Study",Study.class).list();
		}
	}
	public List<Study> getStudy(String idStd) {
		List<Study> _study = getStudy();
		ListIterator<Study> itr=_study.listIterator();
		List<Study> res = new ArrayList<Study>();
		while(itr.hasNext()) {
			Study study = itr.next();
			if(study.getPk().getIdStudent()==Long.parseLong(idStd)&&study.getDone()==false) {
				res.add(study);
			}
		}
		return res;
	}
	public List<Study> getStudyFull(String idStd) {
		List<Study> _study = getStudy();
		ListIterator<Study> itr=_study.listIterator();
		List<Study> res = new ArrayList<Study>();
		while(itr.hasNext()) {
			Study study = itr.next();
			if(study.getPk().getIdStudent()==Long.parseLong(idStd)) {
				res.add(study);
			}
		}
		return res;
	}
	
}
