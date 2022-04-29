package dataTableLazy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import model.Lancamento;
import repository.iLancamento;
import repository.iLancamentoImpl;

public class LazyDataTableModelLancamento<T> extends LazyDataModel<Lancamento>{

	private iLancamento daoLancamento = new iLancamentoImpl();
	
	private Long idUser = (long) 0;
	
	private String dateConsulta = "";
	
	List<Lancamento> listLancamentos = new ArrayList<Lancamento>();
	
	@Override
	public List<Lancamento> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		
		listLancamentos = daoLancamento.carregarLancamentos(idUser, dateConsulta, first, pageSize);
		
		setPageSize(pageSize);
		
		int quant = daoLancamento.count(dateConsulta, idUser);
		
		setRowCount(quant);
		
		return listLancamentos;
	}
	
	public void pesquisarData(String data) {
		this.dateConsulta = data;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getDateConsulta() {
		return dateConsulta;
	}

	public void setDateConsulta(String dateConsulta) {
		this.dateConsulta = dateConsulta;
	}

	public List<Lancamento> getListLancamentos() {
		return listLancamentos;
	}
	
	
}
