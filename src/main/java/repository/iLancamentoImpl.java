package repository;

import java.io.Serializable;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import jpautil.JPAUtil;
import model.Lancamento;
import model.Pessoa;

public class iLancamentoImpl implements Serializable, iLancamento{

	private static final long serialVersionUID = 1L;

	@Override
	public List<Lancamento> carregarLancamentos(Long userId, String dataConsulta, int first, int pageSize) {
		
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		
		transaction.begin();
		
		StringBuilder sql = new StringBuilder();
		
		if(dataConsulta != null && !dataConsulta.trim().isEmpty()) {
		sql.append("select new model.Lancamento(l.id, l.dataLancamento, l.valor, l.usuario.id) from Lancamento l where l.usuario.id =  ").append(userId).append(" and l.dataLancamento = '")
		.append(dataConsulta).append("'");
		}else {
			sql.append("select new model.Lancamento(l.id, l.dataLancamento, l.valor, l.usuario.id) from Lancamento l where l.usuario.id =  ").append(userId).append("");
		}
		
		List<Lancamento> lancamentos = new ArrayList<Lancamento>();
		
		try {
			
			lancamentos = entityManager.createQuery(sql.toString(), Lancamento.class).setFirstResult(first).setMaxResults(pageSize).getResultList();
			
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

	@Override
	public List<Lancamento> consultarLancamentosIntervalo(String dataInicial, String dataFinal) {
		
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		
		transaction.begin();
		
		Query query = entityManager.createNativeQuery(
				"select sum(lanc.valor) as valorTotal,  usuario.nome, lanc.dataLancamento \r\n"
				+ "from lancamentos lanc inner join pessoa usuario on usuario.id = lanc.id_usuario\r\n"
				+ "where lanc.dataLancamento >= ? and lanc.dataLancamento <= ? \r\n"
				+ "group by month(lanc.dataLancamento) order by month(lanc.dataLancamento);");
		query.setParameter(1, dataInicial);
		query.setParameter(2, dataFinal);
		
		List<Lancamento> lancamentos = new ArrayList<Lancamento>();
		List<Object[]> objects  = new ArrayList<Object[]>();
		
		try {
			
			objects = query.getResultList();
			
			for (Object[] object : objects) {
				Lancamento lancamento = new Lancamento();
				Pessoa usuario = new Pessoa();
				
				lancamento.setValor((Double) object[0]);
				lancamento.setDataLancamento((Date) object[2]);
				usuario.setNome( (String) object[1] );
				lancamento.setUsuario(usuario);
				
				lancamentos.add(lancamento);
				
			}
			
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
	
	@Override
	public Integer count(String dataConsulta, Long userID) {

		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		
		transaction.begin();
		
		StringBuilder sql = new StringBuilder();
		
		if(dataConsulta != null && !dataConsulta.trim().isEmpty()) {
			sql.append("select count(1) from lancamentos l where l.dataLancamento = '").append(dataConsulta)
			.append("' and l.id_usuario = ").append(userID);
		}else {
			sql.append("select count(1) from lancamentos l where l.id_usuario = ").append(userID);
		}
		
		
		String quant = (String) entityManager.createNativeQuery(sql.toString()).getSingleResult().toString();
		
		return Integer.parseInt(quant);
	}
	
	
}
