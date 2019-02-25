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
public class ClienteProduto implements Serializable {

	private static final long serialVersionUID = 916792558890873578L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cliente_produto_sequence")
	@SequenceGenerator(name = "cliente_produto_sequence", sequenceName = "cliente_produto_sequence")
	private int codigo;
	@ManyToOne
	private Produto produto;
	private BigDecimal valor;
	private Calendar data;
	@ManyToOne
	TabelaCliente tabelaCliente;

	public ClienteProduto(Produto produto, BigDecimal valor) {
		this.produto = produto;
		this.valor = valor;
		this.data = Calendar.getInstance();
	}

	public ClienteProduto() {
		super();
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

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public TabelaCliente getTabelaCliente() {
		return tabelaCliente;
	}

	public void setTabelaCliente(TabelaCliente tabelaCliente) {
		this.tabelaCliente = tabelaCliente;
	}
}
