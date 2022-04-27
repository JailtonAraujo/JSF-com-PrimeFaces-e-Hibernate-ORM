package managedbeans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.BarChartModel;

@ManagedBean(name = "relLancamentoBean")
@ViewScoped
public class relLancamentoBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private BarChartModel barChartModel = new BarChartModel();
	
	public BarChartModel getBarChartModel() {
		return barChartModel;
	}
	
	public void setBarChartModel(BarChartModel barChartModel) {
		this.barChartModel = barChartModel;
	}
}
