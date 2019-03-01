package br.com.htisoftware.pdv.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class TipoPagamentoFinanceiro {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipo_pagamento_financeiro")
	@SequenceGenerator(name = "tipo_pagamento_financeiro", sequenceName = "tipo_pagamento_financeiro")
	private int codigo;
	private String descricao;
	private boolean excluido;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isExcluido() {
		return excluido;
	}

	public void setExcluido(boolean excluido) {
		this.excluido = excluido;
	}
}
