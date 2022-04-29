package managedbeans;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import model.Lancamento;
import repository.iLancamento;
import repository.iLancamentoImpl;
import util.replaceMonthToData;

@ManagedBean(name = "relLancamentoBean")
@ViewScoped
public class relLancamentoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private BarChartModel barChartModel = new BarChartModel();

	private Lancamento lancamento = new Lancamento();
	
	private List<Lancamento> lancamentos = new ArrayList<Lancamento> ();

	private iLancamento iLancamento = new iLancamentoImpl();
	
	private replaceMonthToData replaceMonthToData = new replaceMonthToData();

	private Date dataInicial;
	private Date dataFinal;

	public void GerarRalatorio() {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		lancamentos =  iLancamento.consultarLancamentosIntervalo(format.format(dataInicial).toString(), format.format(dataFinal).toString());
		
		GerarGrafico();
	}
	
	public void GerarGrafico() {
		lancamentos = replaceMonthToData.replaceNameMonthLancemento(lancamentos);
		
		ChartSeries chartSeries = new ChartSeries();
		
		for (Lancamento lanc : lancamentos) {
			chartSeries.set(lanc.getMonth(), lanc.getValor());
		}
		
		barChartModel.addSeries(chartSeries);
		barChartModel.setTitle("Grafico de Receita Mensal");
	}

	public BarChartModel getBarChartModel() {
		return barChartModel;
	}

	public void setBarChartModel(BarChartModel barChartModel) {
		this.barChartModel = barChartModel;
	}

	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}

	public Lancamento getLancamento() {
		return lancamento;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}
	
	public void setLancamentos(List<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}
	
	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}

}
