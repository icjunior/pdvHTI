package br.com.htisoftware.pdv.modelo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class TabelaCliente implements Serializable {

	private static final long serialVersionUID = -7223499247611524735L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tabela_cliente_sequence")
	@SequenceGenerator(name = "tabela_cliente_sequence", sequenceName = "tabela_cliente_sequence")
	private int codigo;
	@Temporal(TemporalType.DATE)
	private Calendar dataInicial;
	@Temporal(TemporalType.DATE)
	private Calendar dataFinal;
	@ManyToOne
	private Cliente cliente;
	@OneToMany(mappedBy = "tabelaCliente")
	private List<ClienteProduto> produtos;
	private boolean ativo;
	private Calendar dataEliminacao;

	public TabelaCliente() {

	}

	public TabelaCliente(Calendar dataInicial, Calendar dataFinal, Cliente cliente, boolean ativo) {
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
		this.cliente = cliente;
		this.ativo = ativo;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Calendar getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Calendar dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Calendar getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Calendar dataFinal) {
		this.dataFinal = dataFinal;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ClienteProduto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<ClienteProduto> produtos) {
		this.produtos = produtos;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Calendar getDataEliminacao() {
		return dataEliminacao;
	}

	public void setDataEliminacao(Calendar dataEliminacao) {
		this.dataEliminacao = dataEliminacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (ativo ? 1231 : 1237);
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + codigo;
		result = prime * result + ((dataEliminacao == null) ? 0 : dataEliminacao.hashCode());
		result = prime * result + ((dataFinal == null) ? 0 : dataFinal.hashCode());
		result = prime * result + ((dataInicial == null) ? 0 : dataInicial.hashCode());
		result = prime * result + ((produtos == null) ? 0 : produtos.hashCode());
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
		TabelaCliente other = (TabelaCliente) obj;
		if (ativo != other.ativo)
			return false;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (codigo != other.codigo)
			return false;
		if (dataEliminacao == null) {
			if (other.dataEliminacao != null)
				return false;
		} else if (!dataEliminacao.equals(other.dataEliminacao))
			return false;
		if (dataFinal == null) {
			if (other.dataFinal != null)
				return false;
		} else if (!dataFinal.equals(other.dataFinal))
			return false;
		if (dataInicial == null) {
			if (other.dataInicial != null)
				return false;
		} else if (!dataInicial.equals(other.dataInicial))
			return false;
		if (produtos == null) {
			if (other.produtos != null)
				return false;
		} else if (!produtos.equals(other.produtos))
			return false;
		return true;
	}
}
