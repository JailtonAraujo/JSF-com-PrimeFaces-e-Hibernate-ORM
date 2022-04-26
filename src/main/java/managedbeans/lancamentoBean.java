package managedbeans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.DAOGeneric;
import model.Lancamento;
import model.Pessoa;
import repository.iLancamento;
import repository.iLancamentoImpl;

@ManagedBean(name = "lancementoBean")
@ViewScoped
public class lancamentoBean implements Serializable{


	private static final long serialVersionUID = 1L;
	
	private Lancamento lancamento = new Lancamento();

	private DAOGeneric<Lancamento> daoGeneric = new DAOGeneric<Lancamento>();
	
	private List<Lancamento> lancamentos = new ArrayList<Lancamento>();
	
	private iLancamento iLancamento = new iLancamentoImpl();
	
	private Date dataConsulta;
	
	public String salvar() {
		lancamento.setUsuario(getUserLogado());
		lancamento = daoGeneric.salvar(lancamento);
		gerarMsg("Salvo com suceso!");
		
		return "";
	}
	
	@PostConstruct
	public void init() {
		
	}
	
	public void limpar() {
		lancamento = new Lancamento();
	}
	
	public String consultarLancamentos() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		if(dataConsulta != null && !dataConsulta.toString().trim().isEmpty()) {
			lancamentos = iLancamento.carregarLancamentos(getUserLogado().getId(), format.format(dataConsulta).toString());
		}else {
			lancamentos = iLancamento.carregarLancamentos(getUserLogado().getId(), null);
		}
		return "";
	}
	
	public Pessoa getUserLogado() {
		Pessoa usuarioLogado = 	(Pessoa) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado");
	
		return usuarioLogado; 
	}
	
	public void gerarMsg(String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
	}
	
	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}
	
	public Lancamento getLancamento() {
		return lancamento;
	}
	
	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}
	
	public void setLancamentos(List<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}
	
	public Date getDataConsulta() {
		return dataConsulta;
	}
	
	public void setDataConsulta(Date dataConsulta) {
		this.dataConsulta = dataConsulta;
	}

}
