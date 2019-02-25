package br.com.htisoftware.pdv.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import br.com.htisoftware.pdv.enums.TipoCST;

@Entity
public class CST implements Serializable {

	private static final long serialVersionUID = 4421648378746314319L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cst_sequence")
	@SequenceGenerator(name = "cst_sequence", sequenceName = "cst_sequence")
	private int codigo;
	private String cst;
	private String descricao;
	@Enumerated(EnumType.STRING)
	private TipoCST tipoCST;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getCst() {
		return cst;
	}

	public void setCst(String cst) {
		this.cst = cst;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TipoCST getTipoCST() {
		return tipoCST;
	}

	public void setTipoCST(TipoCST tipoCST) {
		this.tipoCST = tipoCST;
	}

	@Override
	public String toString() {
		return cst + " - " + descricao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		result = prime * result + ((cst == null) ? 0 : cst.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((tipoCST == null) ? 0 : tipoCST.hashCode());
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
		CST other = (CST) obj;
		if (codigo != other.codigo)
			return false;
		if (cst == null) {
			if (other.cst != null)
				return false;
		} else if (!cst.equals(other.cst))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (tipoCST != other.tipoCST)
			return false;
		return true;
	}
}
