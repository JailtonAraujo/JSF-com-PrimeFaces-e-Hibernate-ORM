package repository;

import java.util.List;

import model.Lancamento;

public interface iLancamento {

	public List<Lancamento> carregarLancamentos(Long userId, String dataConsulta);
}
