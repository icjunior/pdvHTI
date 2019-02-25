package br.com.htisoftware.pdv.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CupomItem implements Serializable {

	private static final long serialVersionUID = 7088895743275225618L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int codigo;
	private int sequencia;
	@ManyToOne
	private Produto produto;
	private BigDecimal valor;
	private BigDecimal quantidade;
	private boolean cancelado;
	private Calendar dataLancamento;
	@ManyToOne
	private Cupom cupom;

	public CupomItem() {
	}

	public CupomItem(int codigo, int sequencia, Produto produto, BigDecimal valor, BigDecimal quantidade,
			Calendar dataLancamento) {
		this.codigo = codigo;
		this.sequencia = sequencia;
		this.produto = produto;
		this.valor = valor;
		this.quantidade = quantidade;
		this.dataLancamento = dataLancamento;
	}

	public Cupom getCupom() {
		return cupom;
	}

	public void setCupom(Cupom cupom) {
		this.cupom = cupom;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	public boolean isCancelado() {
		return cancelado;
	}

	public void setCancelado(boolean cancelado) {
		this.cancelado = cancelado;
	}

	public Calendar getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Calendar dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public BigDecimal getValorTotal() {
		return valor.multiply(quantidade);
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public int getSequencia() {
		return sequencia;
	}

	public void setSequencia(int sequencia) {
		this.sequencia = sequencia;
	}
}
