package br.com.htisoftware.pdv.bean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.htisoftware.pdv.dto.CupomDTO;
import br.com.htisoftware.pdv.dto.FormaPagamentoDTO;
import br.com.htisoftware.pdv.service.CupomService;
import br.com.htisoftware.pdv.service.FormaPagamentoService;

@Named
@ViewScoped
public class RelatorioFormaPagamentoBean implements Serializable {

	private static final long serialVersionUID = -4449090787332291183L;
	List<FormaPagamentoDTO> finalizadoras;
	List<CupomDTO> cupons;
	@Inject
	FormaPagamentoService formaPagamentoService;
	@Inject
	CupomService cupomService;
	// parametros
	Calendar dataInicial;
	Calendar dataFinal;
	int pdv;

	public void buscar() {
		finalizadoras = formaPagamentoService.buscarPorDataEPDV(dataInicial, dataFinal, pdv);
	}

	public void consultaCupomPorFinalizadora(FormaPagamentoDTO formaPagamento) {
		cupons = cupomService.buscaCupomPorFinalizadora(formaPagamento, dataInicial, dataFinal);
	}

	public List<FormaPagamentoDTO> getFinalizadoras() {
		return finalizadoras;
	}

	public void setFinalizadoras(List<FormaPagamentoDTO> finalizadoras) {
		this.finalizadoras = finalizadoras;
	}

	public Calendar getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Calendar dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Calendar getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Calendar dataFinal) {
		this.dataFinal = dataFinal;
	}

	public int getPdv() {
		return pdv;
	}

	public void setPdv(int pdv) {
		this.pdv = pdv;
	}

	public List<CupomDTO> getCupons() {
		return cupons;
	}

	public void setCupons(List<CupomDTO> cupons) {
		this.cupons = cupons;
	}
}
