package managedbeans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.DAOGeneric;
import dataTableLazy.LazyDataTableModelPessoa;
import model.Pessoa;
import repository.iPessoa;
import repository.iPessoaImpl;

@ManagedBean(name = "pessoaBean")
@ViewScoped
public class pessoaBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private Pessoa pessoa = new Pessoa();
	
	private LazyDataTableModelPessoa<Pessoa> listUsuarios = new LazyDataTableModelPessoa<Pessoa>();
	
	DAOGeneric<Pessoa> daoGeneric = new DAOGeneric<Pessoa>();
	iPessoa iPessoa = new iPessoaImpl();
	
	@PostConstruct
	public void init() {
		//listUsuarios.load(0, 5, null, null, null);
		verificarUsuarioExist();
		return;
	}
	
	public String salvar() {
		try {
		if(iPessoa.validarLogin(pessoa.getLogin())) {
			pessoa = daoGeneric.salvarMerge(pessoa);
			GerarMSG("Salvo com Sucesso!");
		}else {
			GerarMSG("Login Indisponivél!");
		}
		
		}catch (Exception e) {
			GerarMSG(e.getMessage());
		}
		return "";
	}
	
	
	public void GerarMSG(String msg) {
		FacesContext.getCurrentInstance().addMessage(null,  new FacesMessage(msg));
	}
	
	
	public void pesquisar() {
		listUsuarios.pesquisarNome(pessoa.getNome());
	}
	
	public void deletar() {
		if(daoGeneric.deletar(pessoa.getId(), Pessoa.class)) {
			GerarMSG("Excluido com sucesso!");
		}
	}
	
	public String limpar() {
		pessoa = new Pessoa();
		return "";
	}
	
	public String editar() {
		pessoa = daoGeneric.buscarEntity(pessoa.getId(), Pessoa.class);
		GerarMSG("Usuario carregado para edição");
		return "/pages/principal.jsf?faces-redirect=true";
	}
	
	public boolean verificarUsuarioExist() {
		if(pessoa.getId() != null) {
			return true;
		}
		return false;
	}
	
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	public Pessoa getPessoa() {
		return pessoa;
	}
	
	
	public LazyDataTableModelPessoa<Pessoa> getListUsuarios() {
		return listUsuarios;
	}
	
	
}
