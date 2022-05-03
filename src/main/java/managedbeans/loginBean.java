package managedbeans;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import model.Pessoa;
import repository.iPessoa;
import repository.iPessoaImpl;

@ManagedBean(name = "loginBean")
@SessionScoped
public class loginBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Pessoa usuarioLogado = new Pessoa();
	
	iPessoa iPessoa = new iPessoaImpl();
	
	public String logar() {
		
		usuarioLogado = iPessoa.logar(usuarioLogado);
		
		if(usuarioLogado != null && usuarioLogado.getId() != null) {
			
			FacesContext context = FacesContext.getCurrentInstance();
			ExternalContext externalContext = context.getExternalContext();
			externalContext.getSessionMap().put("usuarioLogado", usuarioLogado);
			
			return "pages/principal.jsf?faces-redirect=true";
		}
		
		GerarMSG("Usuario ou Senha Incorretos!");
		return "index.jsf?faces-redirect=true";
	}
	
	public void logout() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().remove("usuarioLogado");

		HttpServletRequest request = (HttpServletRequest) context.getCurrentInstance().getExternalContext()
				.getRequest();
		request.getSession().invalidate();
		
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath()+"/index.jsf");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void GerarMSG(String msg) {
		FacesContext.getCurrentInstance().addMessage(null,  new FacesMessage(msg));
	}

	public Pessoa getUsuario() {
		return usuarioLogado;
	}

	public void setUsuario(Pessoa usuario) {
		usuarioLogado = usuario;
	}
	
	

}
