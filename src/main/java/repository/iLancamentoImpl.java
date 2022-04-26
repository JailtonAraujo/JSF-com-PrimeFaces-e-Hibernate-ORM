package repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import jpautil.JPAUtil;
import model.Lancamento;

public class iLancamentoImpl implements Serializable, iLancamento{

	private static final long serialVersionUID = 1L;

	@Override
	public List<Lancamento> carregarLancamentos(Long userId, String dataConsulta) {
		
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		
		transaction.begin();
		
		StringBuilder sql = new StringBuilder();
		
		if(dataConsulta != null && !dataConsulta.trim().isEmpty()) {
		sql.append("select new model.Lancamento(l.id, l.dataLancamento, l.valor, l.usuario.id) from Lancamento l where l.usuario.id =  ").append(userId).append(" and l.dataLancamento = '")
		.append(dataConsulta).append("' order by l.id desc");
		}else {
			sql.append("select new model.Lancamento(l.id, l.dataLancamento, l.valor, l.usuario.id) from Lancamento l where l.usuario.id =  ").append(userId).append(" order by l.id desc");
		}
		
		List<Lancamento> lancamentos = new ArrayList<Lancamento>();
		
		try {
			
			lancamentos = entityManager.createQuery(sql.toString(), Lancamento.class).getResultList();
			
			transaction.commit();
			
			return lancamentos;
			
		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}finally {
			entityManager.close();
		}
		
		return null;
	}
	
	
}
