package repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import jpautil.JPAUtil;
import model.Telefone;

public class iTelefoneImpl implements Serializable, iTelefone{

	private static final long serialVersionUID = 1L;

	
	@Override
	public List<Telefone> buscarTelefones(Long id) {
		
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		
		transaction.begin();
		
		try {
			
			List<Telefone> telefones = new ArrayList<Telefone>();
			
			StringBuilder sql = new StringBuilder();
			
			sql.append("select new model.Telefone(t.id, t.numero) from Telefone t where t.pessoa.id = ").append(id).append(" order by t.id desc");
			
			telefones = entityManager.createQuery(sql.toString(),Telefone.class).getResultList();
			
			transaction.commit();
			
			return telefones;
			
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}finally {
			entityManager.close();
		}
		
		return null;
	}

}
