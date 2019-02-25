package br.com.htisoftware.pdv.dto;

import java.io.Serializable;
import java.util.Calendar;

import br.com.htisoftware.pdv.modelo.Produto;
import br.com.htisoftware.pdv.modelo.TipoAjuste;

public class RelatorioProdutoEstoqueDTO implements Serializable {

	private static final long serialVersionUID = -7842121804839288451L;
	private Calendar dataInicio;
	private Calendar dataFinal;
	private Produto produto;
	private TipoAjuste tipoAjuste;

	public Calendar getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Calendar dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Calendar getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Calendar dataFinal) {
		this.dataFinal = dataFinal;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public TipoAjuste getTipoAjuste() {
		return tipoAjuste;
	}

	public void setTipoAjuste(TipoAjuste tipoAjuste) {
		this.tipoAjuste = tipoAjuste;
	}
}
