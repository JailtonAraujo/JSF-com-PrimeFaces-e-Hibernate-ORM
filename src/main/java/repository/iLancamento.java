package repository;

import java.util.List;

import model.Lancamento;

public interface iLancamento {

	public List<Lancamento> carregarLancamentos(Long userId, String dataConsulta, int first, int pageSize );
	
	public List<Lancamento> consultarLancamentosIntervalo(String dataInicial, String dataFinal);
	
	public Integer count(String dataConsulta, Long userID);
}
