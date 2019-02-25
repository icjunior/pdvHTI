package br.com.htisoftware.pdv.modelo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Aliquota implements Serializable {

	private static final long serialVersionUID = -8919126119935316345L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "aliquota_sequence")
	@SequenceGenerator(name = "aliquota_sequence", sequenceName = "aliquota_sequence")
	private int codigo;
	@ManyToOne
	private CST cst;
	private BigDecimal aliquota;
	private BigDecimal reducao;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public BigDecimal getAliquota() {
		return aliquota;
	}

	public void setAliquota(BigDecimal aliquota) {
		this.aliquota = aliquota;
	}

	public BigDecimal getReducao() {
		return reducao;
	}

	public void setReducao(BigDecimal reducao) {
		this.reducao = reducao;
	}

	public CST getCst() {
		return cst;
	}

	public void setCst(CST cst) {
		this.cst = cst;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aliquota == null) ? 0 : aliquota.hashCode());
		result = prime * result + codigo;
		result = prime * result + ((cst == null) ? 0 : cst.hashCode());
		result = prime * result + ((reducao == null) ? 0 : reducao.hashCode());
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
		Aliquota other = (Aliquota) obj;
		if (aliquota == null) {
			if (other.aliquota != null)
				return false;
		} else if (!aliquota.equals(other.aliquota))
			return false;
		if (codigo != other.codigo)
			return false;
		if (cst == null) {
			if (other.cst != null)
				return false;
		} else if (!cst.equals(other.cst))
			return false;
		if (reducao == null) {
			if (other.reducao != null)
				return false;
		} else if (!reducao.equals(other.reducao))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CST=" + cst + ", aliquota=" + aliquota + "%, reducao=" + reducao + "%";
	}
}
