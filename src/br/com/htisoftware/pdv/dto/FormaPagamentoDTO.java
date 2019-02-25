package br.com.htisoftware.pdv.dto;

import java.math.BigDecimal;

public class FormaPagamentoDTO {

	private String nome;
	private BigDecimal valor;
	private long quantidade;
	private int pdv;

	public FormaPagamentoDTO(String nome, BigDecimal valor, long quantidade, int pdv) {
		super();
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.pdv = pdv;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(long quantidade) {
		this.quantidade = quantidade;
	}

	public int getPdv() {
		return pdv;
	}

	public void setPdv(int pdv) {
		this.pdv = pdv;
	}
}
