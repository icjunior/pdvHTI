package br.com.htisoftware.pdv.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class ProdutoAjusteEstoque implements Serializable {

	private static final long serialVersionUID = -3096362625317191996L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto_ajuste_estoque_sequence")
	@SequenceGenerator(name = "produto_ajuste_estoque_sequence", sequenceName = "produto_ajuste_estoque_sequence")
	private int codigo;
	@ManyToOne
	private Produto produto;
	private BigDecimal quantidade;
	private Calendar data = Calendar.getInstance();
	private String observacao;
	@ManyToOne
	TipoAjuste tipoAjuste;

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

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public TipoAjuste getTipoAjuste() {
		return tipoAjuste;
	}

	public void setTipoAjuste(TipoAjuste tipoAjuste) {
		this.tipoAjuste = tipoAjuste;
	}
}
