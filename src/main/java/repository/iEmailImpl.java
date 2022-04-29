package repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import jpautil.JPAUtil;
import model.Email;

public class iEmailImpl implements Serializable, iEmail{

	@Override
	public boolean salvarEmail(Email email) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		
		transaction.begin();
		
		try {
		entityManager.persist(email);
		
		transaction.commit();
		
		return true;
		}catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}finally {
			entityManager.close();
		}
		
		return false;
	}

	@Override
	public List<Email> listarEmails(Long userID) {
		
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		
		transaction.begin();
		
		List<Email> emails = new ArrayList<Email>();
		
		try {
			emails = entityManager.createQuery("select new model.Email( em.id,em.email, em.pessoa.id ) from Email em where em.pessoa.id = "+userID+" ", Email.class).getResultList();
			
			transaction.commit();
			
			return emails;
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}finally {
			entityManager.close();
		}
		
		return null;
	}

}
