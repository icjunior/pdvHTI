package br.com.htisoftware.pdv.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import br.com.htisoftware.pdv.enums.TipoMovimentacaoEstoque;

@Entity
public class CFOP implements Serializable {

	private static final long serialVersionUID = -450126633255836455L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cfop_sequence")
	@SequenceGenerator(name = "cfop_sequence", sequenceName = "cfop_sequence")
	private int codigo;
	private String cfop;
	private String descricao;
	@Enumerated(EnumType.STRING)
	private TipoMovimentacaoEstoque tipoMovimentacaoEstoque;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getCfop() {
		return cfop;
	}

	public void setCfop(String cfop) {
		this.cfop = cfop;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TipoMovimentacaoEstoque getTipoMovimentacaoEstoque() {
		return tipoMovimentacaoEstoque;
	}

	public void setTipoMovimentacaoEstoque(TipoMovimentacaoEstoque tipoMovimentacaoEstoque) {
		this.tipoMovimentacaoEstoque = tipoMovimentacaoEstoque;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cfop == null) ? 0 : cfop.hashCode());
		result = prime * result + codigo;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((tipoMovimentacaoEstoque == null) ? 0 : tipoMovimentacaoEstoque.hashCode());
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
		CFOP other = (CFOP) obj;
		if (cfop == null) {
			if (other.cfop != null)
				return false;
		} else if (!cfop.equals(other.cfop))
			return false;
		if (codigo != other.codigo)
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (tipoMovimentacaoEstoque != other.tipoMovimentacaoEstoque)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return cfop + " - " + descricao;
	}
}
