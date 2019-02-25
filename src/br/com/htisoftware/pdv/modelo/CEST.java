package br.com.htisoftware.pdv.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class CEST implements Serializable {

	private static final long serialVersionUID = 6449724912612197287L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cest_sequence")
	@SequenceGenerator(name = "cest_sequence", sequenceName = "cest_sequence")
	private int codigo;
	private String cest;
	private String descricao;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cest == null) ? 0 : cest.hashCode());
		result = prime * result + codigo;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CEST other = (CEST) obj;
		if (cest == null) {
			if (other.cest != null)
				return false;
		} else if (!cest.equals(other.cest))
			return false;
		if (codigo != other.codigo)
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return cest;
	}
}