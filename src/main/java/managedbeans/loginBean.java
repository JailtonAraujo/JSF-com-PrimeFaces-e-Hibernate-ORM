package managedbeans;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import model.Pessoa;
import repository.iPessoa;
import repository.iPessoaImpl;

@ManagedBean(name = "loginBean")
@ViewScoped
public class loginBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Pessoa Usuario = new Pessoa();
	
	iPessoa iPessoa = new iPessoaImpl();
	
	public String logar() {
		
		Pessoa usuarioLogado = iPessoa.logar(Usuario);
		
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

		HttpServletRequest request = (HttpServletRequest) context.getCurrentInstance().getExternalContext()
				.getRequest();
		request.getSession().invalidate();

		return "index.jsf";
	}
	
	public void GerarMSG(String msg) {
		FacesContext.getCurrentInstance().addMessage(null,  new FacesMessage(msg));
	}

	public Pessoa getUsuario() {
		return Usuario;
	}

	public void setUsuario(Pessoa usuario) {
		Usuario = usuario;
	}
	
	

}
