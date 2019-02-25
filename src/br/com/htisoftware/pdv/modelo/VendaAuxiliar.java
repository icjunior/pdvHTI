package br.com.htisoftware.pdv.modelo;

import java.io.Serializable;
import java.math.BigDecimal;

public class VendaAuxiliar implements Serializable {

	private static final long serialVersionUID = -3960893903109673035L;
	private BigDecimal valorRecebido;
	int posicaoItem;
	private BigDecimal troco = new BigDecimal(0.00);
	private String cpfCnpj;
	private int numeroPedido;
	private BigDecimal fundo;
	int sequencia;
	boolean preVenda;

	public BigDecimal getValorRecebido() {
		return valorRecebido;
	}

	public void setValorRecebido(BigDecimal valorRecebido) {
		this.valorRecebido = valorRecebido;
	}

	public int getPosicaoItem() {
		return posicaoItem;
	}

	public void setPosicaoItem(int posicaoItem) {
		this.posicaoItem = posicaoItem;
	}

	public BigDecimal getTroco() {
		return troco;
	}

	public void setTroco(BigDecimal troco) {
		this.troco = troco;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public int getNumeroPedido() {
		return numeroPedido;
	}

	public void setNumeroPedido(int numeroPedido) {
		this.numeroPedido = numeroPedido;
	}

	public BigDecimal getFundo() {
		return fundo;
	}

	public void setFundo(BigDecimal fundo) {
		this.fundo = fundo;
	}

	public int getSequencia() {
		return sequencia;
	}

	public void setSequencia(int sequencia) {
		this.sequencia = sequencia;
	}

	public boolean isPreVenda() {
		return preVenda;
	}

	public void setPreVenda(boolean preVenda) {
		this.preVenda = preVenda;
	}
}
