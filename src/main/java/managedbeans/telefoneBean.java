package managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.DAOGeneric;
import model.Pessoa;
import model.Telefone;
import repository.iTelefone;
import repository.iTelefoneImpl;

@ManagedBean(name = "telefoneBean")
@ViewScoped
public class telefoneBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Pessoa usuario = new Pessoa();
	
	private DAOGeneric<Telefone> daoGeneric = new DAOGeneric<Telefone>();
	
	private iTelefone iTelefone = new iTelefoneImpl();

	Telefone telefone = new Telefone();

	List<Telefone> telefones = new ArrayList<Telefone>();

	@PostConstruct
	public void init() {
		String userId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("userId");
		String userNome = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("userNome");
		
		usuario.setId(Long.parseLong(userId));
		usuario.setNome(userNome);
		
		carregarTelefones();
	}
	
	public String salvar() {
		
		telefone.setPessoa(usuario);
		
		daoGeneric.salvarMerge(telefone);
		gerarMsg("Salvo com sucesso!");
		
		carregarTelefones();
		
		return "";
	}
	
	
	public void carregarTelefones() {
		telefones = iTelefone.buscarTelefones(usuario.getId());
	}
	
	public void gerarMsg(String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
	}
	
	public String deletar() {
		if(daoGeneric.deletar(telefone.getId(), Telefone.class)) {
			gerarMsg("Excluido com sucesso!");
			carregarTelefones();
		}else {
		gerarMsg("Erro ao tentar excluir o telefone selecionado!");
		}
		return "";
	}

	public Pessoa getUsuario() {
		return usuario;
	}

	public void setUsuario(Pessoa usuario) {
		this.usuario = usuario;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

}
