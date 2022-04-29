package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import jpautil.JPAUtil;

public class DAOGeneric <E>{

	public E salvarMerge(E entity) {
		
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		
		transaction.begin();
		try {
		E object = entityManager.merge(entity);
		
		transaction.commit();
		
		return object;
		}catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			return null;
		}finally {
			entityManager.close();
		}
	}
	
	public boolean deletar(Long id, Class<E> entidade) {
		
		
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		
		transaction.begin();
		
		try {
		entityManager.createNativeQuery("delete from "+entidade.getSimpleName().toLowerCase()+" where id = "+id+" ").executeUpdate();
		
		transaction.commit();
		
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			return false;
		}finally {
			entityManager.close();
		}
	}
	
	public E buscarEntity (Long id, Class<E> entidade ) {
		
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		
		transaction.begin();
		
		try {
			
			E object = entityManager.find(entidade, id);
			
			transaction.commit();
			 
			return object;
			
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
			return null;
		}finally {
			entityManager.close();
		}
	}
	
	
	public boolean persist (E entidade) {
		
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		
		transaction.begin();
		
		try {
			
			entityManager.persist(entidade);
			
			transaction.commit();
			
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}finally {
			entityManager.close();
		}
		
		return false;
	}
	
	public boolean remove(E entidade) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		
		transaction.begin();
		
		try {
			entityManager.remove(entidade);
			
			transaction.commit();
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}finally {
			entityManager.close();
		}
		return false;
	}
	
	
}
