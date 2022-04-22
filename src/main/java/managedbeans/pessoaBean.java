package managedbeans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.servlet.http.HttpServletRequest;

import dao.DAOGeneric;
import model.Pessoa;
import repository.iPessoa;
import repository.iPessoaImpl;

@ManagedBean(name = "pessoaBean")
@ViewScoped
public class pessoaBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private Pessoa pessoa = new Pessoa();
	
	DAOGeneric<Pessoa> daoGeneric = new DAOGeneric<Pessoa>();
	iPessoa iPessoa = new iPessoaImpl();
	
	public String salvar() {
		try {
		pessoa = daoGeneric.salvar(pessoa);
		GerarMSG("Salvo com Sucesso!");
		}catch (Exception e) {
			GerarMSG(e.getMessage());
		}
		return "";
	}
	
	public String logar() {
		Pessoa usuarioLogado = iPessoa.logar(pessoa);
		
		if(usuarioLogado != null && usuarioLogado.getId() != null) {
			
			FacesContext context = FacesContext.getCurrentInstance();
			ExternalContext externalContext = context.getExternalContext();
			externalContext.getSessionMap().put("usuarioLogado", usuarioLogado);
			
			return "pages/principal.jsf?faces-redirect=true";
		}
		
		GerarMSG("Usuario ou Senha Incorretos!");
		return "index.jsf?faces-redirect=true";
	}
	
	public String logout() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().remove("usuarioLogado");

		@SuppressWarnings("static-access")
		HttpServletRequest request = (HttpServletRequest) context.getCurrentInstance().getExternalContext()
				.getRequest();
		request.getSession().invalidate();

		return "/index.jsf?faces-redirect=true";
	}
	
	
	public void GerarMSG(String msg) {
		FacesContext.getCurrentInstance().addMessage(null,  new FacesMessage(msg));
	}
	
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	public Pessoa getPessoa() {
		return pessoa;
	}
}
