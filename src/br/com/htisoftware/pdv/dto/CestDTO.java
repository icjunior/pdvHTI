package br.com.htisoftware.pdv.dto;

import java.io.Serializable;

public class CestDTO implements Serializable {

	private static final long serialVersionUID = 3520977509995415735L;
	private String cest;
	private String descricao;

	public String getCest() {
		return cest;
	}

	public void setCest(String cest) {
		this.cest = cest;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
