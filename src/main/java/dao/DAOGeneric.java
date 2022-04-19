package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import jpautil.JPAUtil;

public class DAOGeneric <E>{

	public E salvar(E entity) {
		
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		
		transaction.begin();
		
		E object = entityManager.merge(entity);
		
		transaction.commit();
		entityManager.close();
		
		return object;
	}
}
