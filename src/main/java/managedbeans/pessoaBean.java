package managedbeans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.DAOGeneric;
import model.Pessoa;

@ManagedBean(name = "pessoaBean")
@ViewScoped
public class pessoaBean {

	Pessoa pessoa = new Pessoa();
	
	DAOGeneric<Pessoa> daoGeneric = new DAOGeneric<Pessoa>();
	
	
	public String salvar() {
		try {
		pessoa = daoGeneric.salvar(pessoa);
		GerarMSG("Salvo com Sucesso!");
		}catch (Exception e) {
			GerarMSG(e.getMessage());
		}
		return "";
	}
	
	
	public void GerarMSG(String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
	}
	
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	public Pessoa getPessoa() {
		return pessoa;
	}
}
