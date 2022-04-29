package repository;

import java.util.List;

import model.Pessoa;

public interface iPessoa {
	
	public Pessoa logar(Pessoa usuario);
	
	public List<Pessoa> searchUsers(String name);
	
	public List<Pessoa> buscarPaginator(int first, int pageSize, String nomeBusca);
	
	public Integer countPaginator(String nomeBusca);
}
