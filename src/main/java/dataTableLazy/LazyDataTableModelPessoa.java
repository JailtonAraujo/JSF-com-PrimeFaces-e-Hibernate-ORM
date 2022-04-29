package dataTableLazy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import model.Pessoa;
import repository.iPessoa;
import repository.iPessoaImpl;

public class LazyDataTableModelPessoa <T> extends LazyDataModel<Pessoa>{

	private iPessoa daoPessoa = new iPessoaImpl(); 
	
	public List<Pessoa> list = new ArrayList<Pessoa>();
	
	String nome = "";
	
	@Override
	public List<Pessoa> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		
		list = daoPessoa.buscarPaginator(first, pageSize, nome);
		nome = "";
		
		setPageSize(pageSize);
		
		Integer quantRegistros = daoPessoa.countPaginator(nome);
		
		
		setRowCount(quantRegistros);
		
		return list;
	}
	
	public List<Pessoa> getList() {
		return list;
	}
	
	public void pesquisarNome (String nomeBusca) {
		nome = nomeBusca;
	}
}
