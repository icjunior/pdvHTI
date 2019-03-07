package br.com.htisoftware.pdv.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

public class FinanceiroMassaDTO implements Serializable {

	private static final long serialVersionUID = 724331321312452783L;
	private String atribuicao;
	private String observacao;
	private Calendar vencimento;
	private BigDecimal desconto;
	private BigDecimal acrescimo;

	public String getAtribuicao() {
		return atribuicao;
	}

	public void setAtribuicao(String atribuicao) {
		this.atribuicao = atribuicao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Calendar getVencimento() {
		return vencimento;
	}

	public void setVencimento(Calendar vencimento) {
		this.vencimento = vencimento;
	}

	public BigDecimal getDesconto() {
		return desconto;
	}

	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}

	public BigDecimal getAcrescimo() {
		return acrescimo;
	}

	public void setAcrescimo(BigDecimal acrescimo) {
		this.acrescimo = acrescimo;
	}
}
