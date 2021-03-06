package repository;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.hibernate.query.Query;

import jpautil.JPAUtil;
import model.Pessoa;


public class iPessoaImpl implements Serializable, iPessoa{

	private static final long serialVersionUID = 1L;

	@Override
	public Pessoa logar(Pessoa usuario) {
		
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		
		transaction.begin();
		
		Query query = (Query) entityManager.createNativeQuery("select p.id, p.nome from Pessoa p where p.login = ? and p.senha = ?");
		query.setParameter(1, usuario.getLogin());
		query.setParameter(2, usuario.getSenha());
		
		Pessoa usuarioLogado = new Pessoa();
		
		
			try {
			Object [] object = (Object[]) query.getSingleResult();
			usuarioLogado.setId( ((BigInteger) object[0]).longValue());
			usuarioLogado.setNome((String)object[1]);
			}catch ( javax.persistence.NoResultException e) {
				return usuarioLogado;
			}
		
		transaction.commit();
		entityManager.close();
		
		return usuarioLogado;
	}

	@Override
	public List<Pessoa> searchUsers(String name) {
		
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		
		transaction.begin();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select new model.Pessoa(p.id, p.nome, p.sobrenome, p.login, p.senha) from Pessoa p where p.nome like '").append(name).append("%' order by p.id desc");
		
		List<Pessoa> listPersons = new ArrayList<Pessoa>();
		
		listPersons = entityManager.createQuery(sql.toString(), Pessoa.class).setMaxResults(10).getResultList();
		
		transaction.commit();
		entityManager.close();
		
		return listPersons;
	}

	@Override
	public List<Pessoa> buscarPaginator(int first, int pageSize, String nome) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		
		transaction.begin();
		
		StringBuilder sql = new StringBuilder();
		sql.append("select new model.Pessoa(p.id, p.nome, p.sobrenome, p.login, p.senha) from Pessoa p where p.nome like '").append(nome).append("%'");
		
		List<Pessoa> listPersons = new ArrayList<Pessoa>();
		
		listPersons = entityManager.createQuery(sql.toString(), Pessoa.class).setFirstResult(first).setMaxResults(pageSize).getResultList();
		
		transaction.commit();
		entityManager.close();
		
		return listPersons;
	}

	@Override
	public Integer countPaginator(String nomeBusca) {
		
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		
		transaction.begin();
		
		String quant = entityManager.createQuery("select count(1) from Pessoa p where p.nome like '"+nomeBusca+"%' ").getSingleResult().toString();
		
		transaction.commit();
		
		entityManager.close();
		
		return Integer.parseInt(quant);
	}

	@Override
	public boolean validarLogin(String login) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		
		transaction.begin();
		
		Integer existe = Integer.parseInt(entityManager.createNativeQuery("select count(1) from pessoa where login = '"+login+"'").getSingleResult().toString());
		
		if(existe > 0){
			transaction.commit();
			entityManager.close();
			return false;
		}
		
		transaction.commit();
		entityManager.close();
		return true;
	}

}
