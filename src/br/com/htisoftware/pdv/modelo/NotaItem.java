package br.com.htisoftware.pdv.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class NotaItem implements Serializable {

	private static final long serialVersionUID = 8906968391759796011L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nota_item_sequence")
	@SequenceGenerator(name = "nota_item_sequence", sequenceName = "nota_item_sequence")
	private int codigo;
	@ManyToOne
	Produto produto;
	BigInteger embalagem;
	BigDecimal quantidade;
	BigDecimal valor;
	@ManyToOne
	NotaCabecalho notaCabecalho;
	@ManyToOne
	CFOP cfop;

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public BigInteger getEmbalagem() {
		return embalagem;
	}

	public void setEmbalagem(BigInteger embalagem) {
		this.embalagem = embalagem;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public NotaCabecalho getNotaCabecalho() {
		return notaCabecalho;
	}

	public void setNotaCabecalho(NotaCabecalho notaCabecalho) {
		this.notaCabecalho = notaCabecalho;
	}

	public CFOP getCfop() {
		return cfop;
	}

	public void setCfop(CFOP cfop) {
		this.cfop = cfop;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((embalagem == null) ? 0 : embalagem.hashCode());
		result = prime * result + ((notaCabecalho == null) ? 0 : notaCabecalho.hashCode());
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		result = prime * result + ((quantidade == null) ? 0 : quantidade.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
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
		NotaItem other = (NotaItem) obj;
		if (embalagem == null) {
			if (other.embalagem != null)
				return false;
		} else if (!embalagem.equals(other.embalagem))
			return false;
		if (notaCabecalho == null) {
			if (other.notaCabecalho != null)
				return false;
		} else if (!notaCabecalho.equals(other.notaCabecalho))
			return false;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		if (quantidade == null) {
			if (other.quantidade != null)
				return false;
		} else if (!quantidade.equals(other.quantidade))
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}
}
