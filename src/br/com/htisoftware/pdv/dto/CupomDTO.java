package br.com.htisoftware.pdv.dto;

import java.math.BigDecimal;
import java.util.Calendar;

public class CupomDTO {

	private Calendar data;
	private int pdv;
	private int cupom;
	private BigDecimal valorCupom;
	private BigDecimal valorFormaPagamento;

	public CupomDTO(Calendar data, int pdv, int cupom, BigDecimal valorCupom, BigDecimal valorFormaPagamento) {
		super();
		this.data = data;
		this.pdv = pdv;
		this.cupom = cupom;
		this.valorCupom = valorCupom;
		this.valorFormaPagamento = valorFormaPagamento;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public int getPdv() {
		return pdv;
	}

	public void setPdv(int pdv) {
		this.pdv = pdv;
	}

	public int getCupom() {
		return cupom;
	}

	public void setCupom(int cupom) {
		this.cupom = cupom;
	}

	public BigDecimal getValorCupom() {
		return valorCupom;
	}

	public void setValorCupom(BigDecimal valorCupom) {
		this.valorCupom = valorCupom;
	}

	public BigDecimal getValorFormaPagamento() {
		return valorFormaPagamento;
	}

	public void setValorFormaPagamento(BigDecimal valorFormaPagamento) {
		this.valorFormaPagamento = valorFormaPagamento;
	}
}
