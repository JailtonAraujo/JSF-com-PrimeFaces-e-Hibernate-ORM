package util;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import model.Lancamento;

public class replaceMonthToData implements Serializable{

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("deprecation")
	public List<Lancamento> replaceNameMonthLancemento(List<Lancamento> lancamentos) {
		
		if(lancamentos != null && lancamentos.size() > 0) {
			for (Lancamento lancamento : lancamentos) {
				Date date = new Date();
				try {
					date = new SimpleDateFormat("yyyy-MM-dd").parse(new SimpleDateFormat("yyyy-MM-dd").format(lancamento.getDataLancamento()));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(date.getMonth() == 0) {
					lancamento.setMonth("Janeiro");
				}else if(date.getMonth() == 1) {
					lancamento.setMonth("Fevereiro");
				}else if(date.getMonth() == 2) {
					lancamento.setMonth("Março");
				}else if(date.getMonth() == 3) {
					lancamento.setMonth("Abril");
				}else if(date.getMonth() == 4) {
					lancamento.setMonth("Maio");
				}else if(date.getMonth() == 5) {
					lancamento.setMonth("Junho");
				}else if(date.getMonth() == 6) {
					lancamento.setMonth("Julho");
				}else if(date.getMonth() == 7) {
					lancamento.setMonth("Agosto");
				}else if(date.getMonth() == 8) {
					lancamento.setMonth("Setembro");
				}else if(date.getMonth() == 9) {
					lancamento.setMonth("Outubro");
				}else if(date.getMonth() == 10) {
					lancamento.setMonth("Novembro");
				}else if(date.getMonth() == 11) {
					lancamento.setMonth("Dezembro");
				}
				
			}
			return lancamentos;
		}
		
		return null;
	}
}
