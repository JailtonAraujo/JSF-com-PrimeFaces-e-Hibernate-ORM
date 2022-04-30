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
import model.Email;
import model.Pessoa;
import repository.iEmail;
import repository.iEmailImpl;

@ManagedBean(name = "emailBean")
@ViewScoped
public class emailBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Pessoa usuario = new Pessoa();

	private iEmail imail = new iEmailImpl();
	
	private DAOGeneric<Email> daoGeneric = new DAOGeneric<Email>();

	private Email email = new Email();
	
	private List<Email> emails = new ArrayList<Email>();

	public void addEmail() {
		email.setPessoa(usuario);
		email = daoGeneric.salvarMerge(email);
		if(email != null) {
			emails.add(email);
			GerarMSG("Email Cadastrado com sucesso!");
		}else {
			GerarMSG("Erro ao Cadastrar email!");
		}
		email = new Email();
	}
	
	@PostConstruct
	public void init() {
		
	}
	
	public void carregarEmails() {
		emails = imail.listarEmails(usuario.getId());
	}
	
	public void deletar() {
		if(daoGeneric.deletar(email.getId(), Email.class)) {
			GerarMSG("Ecluido com sucesso");
			emails.remove(email);
		}	
	}

	public void GerarMSG(String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
	}

	public Pessoa getUsuario() {
		return usuario;
	}

	public void setUsuario(Pessoa usuario) {
		this.usuario = usuario;
	}

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}
	
	public List<Email> getEmails() {
		return emails;
	}
	
	public void setEmails(List<Email> emails) {
		this.emails = emails;
	}

}
