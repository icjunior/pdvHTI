package br.com.htisoftware.pdv.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
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
public class NotaCabecalho implements Serializable {

	private static final long serialVersionUID = -6500254677244365018L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nota_cabecalho_sequence")
	@SequenceGenerator(name = "nota_cabecalho_sequence", sequenceName = "nota_cabecalho_sequence")
	private int codigo;
	@ManyToOne
	Cliente cliente;
	String numero;
	String serie;
	BigDecimal valor;
	@Temporal(TemporalType.DATE)
	Calendar emissao;
	@Temporal(TemporalType.DATE)
	Calendar lancamento;
	@Temporal(TemporalType.TIMESTAMP)
	Calendar inclusao;
	@OneToMany(mappedBy = "notaCabecalho")
	List<NotaItem> itens;
	@OneToMany(mappedBy = "notaCabecalho")
	List<Financeiro> financeiros;

	public void adicionaItem(NotaItem item) {
		if (itens == null) {
			itens = new ArrayList<>();
		}
		itens.add(item);
	}

	public void excluiItem(NotaItem item) {
		itens.remove(item);
	}

	public BigDecimal getTotalItens() {
		if (itens != null) {
			return itens.stream().filter(item -> item.getValorTotal() != null).map(item -> item.getValorTotal())
					.reduce(BigDecimal.ZERO, BigDecimal::add);
		}
		return BigDecimal.ZERO;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getSerie() {
		return serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Calendar getEmissao() {
		return emissao;
	}

	public void setEmissao(Calendar emissao) {
		this.emissao = emissao;
	}

	public Calendar getLancamento() {
		return lancamento;
	}

	public void setLancamento(Calendar lancamento) {
		this.lancamento = lancamento;
	}

	public Calendar getInclusao() {
		return inclusao;
	}

	public void setInclusao(Calendar inclusao) {
		this.inclusao = inclusao;
	}

	public List<NotaItem> getItens() {
		return itens;
	}

	public void setItens(List<NotaItem> itens) {
		this.itens = itens;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((emissao == null) ? 0 : emissao.hashCode());
		result = prime * result + ((inclusao == null) ? 0 : inclusao.hashCode());
		result = prime * result + ((itens == null) ? 0 : itens.hashCode());
		result = prime * result + ((lancamento == null) ? 0 : lancamento.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result + ((serie == null) ? 0 : serie.hashCode());
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
		NotaCabecalho other = (NotaCabecalho) obj;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (emissao == null) {
			if (other.emissao != null)
				return false;
		} else if (!emissao.equals(other.emissao))
			return false;
		if (inclusao == null) {
			if (other.inclusao != null)
				return false;
		} else if (!inclusao.equals(other.inclusao))
			return false;
		if (itens == null) {
			if (other.itens != null)
				return false;
		} else if (!itens.equals(other.itens))
			return false;
		if (lancamento == null) {
			if (other.lancamento != null)
				return false;
		} else if (!lancamento.equals(other.lancamento))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (serie == null) {
			if (other.serie != null)
				return false;
		} else if (!serie.equals(other.serie))
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}

	public void adicionaFinanceiro() {
		if (financeiros == null) {
			financeiros = new ArrayList<>();
		}
		financeiros.add(new Financeiro());
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public List<Financeiro> getFinanceiros() {
		return financeiros;
	}

	public void setFinanceiros(List<Financeiro> financeiros) {
		this.financeiros = financeiros;
	}

	public void excluiFinanceiro(Financeiro financeiro) {
		financeiros.remove(financeiro);
	}
}
