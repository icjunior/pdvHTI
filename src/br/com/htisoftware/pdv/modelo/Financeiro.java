package br.com.htisoftware.pdv.modelo;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import br.com.htisoftware.pdv.enums.StatusPagamentoFinanceiro;
import br.com.htisoftware.pdv.enums.TipoDirecaoFinanceiro;

@Entity
public class Financeiro {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nota_financeiro_sequence")
	@SequenceGenerator(name = "nota_financeiro_sequence", sequenceName = "nota_financeiro_sequence")
	private int codigo;
	@ManyToOne
	NotaCabecalho notaCabecalho;
	private String atribuicao;
	@Temporal(TemporalType.DATE)
	@NotNull(message = "Vencimento não pode ser em branco")
	private Calendar vencimento;
	@NotNull(message = "Valor não pode ser em branco")
	private BigDecimal valor;
	private String codBarras;
	private BigDecimal desconto = new BigDecimal(0.00);
	private BigDecimal acrescimo = new BigDecimal(0.00);
	@Enumerated(EnumType.STRING)
	StatusPagamentoFinanceiro statusPagamentoFinanceiro = StatusPagamentoFinanceiro.ABERTO;
	@Enumerated(EnumType.STRING)
	TipoDirecaoFinanceiro tipoDirecaoFinanceiro;
	private String observacao;
	private boolean excluido;
	@ManyToOne
	TipoPagamentoFinanceiro tipoPagamentoFinanceiro;
	@ManyToMany
	List<Financeiro> financeirosRelacionados;

	public Financeiro() {

	}

	public Financeiro(NotaCabecalho notaCabecalho, String atribuicao, Calendar vencimento, BigDecimal valor,
			String codBarras, BigDecimal desconto, BigDecimal acrescimo,
			StatusPagamentoFinanceiro statusPagamentoFinanceiro, TipoDirecaoFinanceiro tipoDirecaoFinanceiro,
			String observacao, boolean excluido, TipoPagamentoFinanceiro tipoPagamentoFinanceiro,
			List<Financeiro> financeirosRelacionados) {
		super();
		this.notaCabecalho = notaCabecalho;
		this.atribuicao = atribuicao;
		this.vencimento = vencimento;
		this.valor = valor;
		this.codBarras = codBarras;
		this.desconto = desconto;
		this.acrescimo = acrescimo;
		this.statusPagamentoFinanceiro = statusPagamentoFinanceiro;
		this.tipoDirecaoFinanceiro = tipoDirecaoFinanceiro;
		this.observacao = observacao;
		this.excluido = excluido;
		this.tipoPagamentoFinanceiro = tipoPagamentoFinanceiro;
		this.financeirosRelacionados = financeirosRelacionados;
	}

	public BigDecimal getTotalLiquido() {
		return valor.add(acrescimo).subtract(desconto);
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public NotaCabecalho getNotaCabecalho() {
		return notaCabecalho;
	}

	public void setNotaCabecalho(NotaCabecalho notaCabecalho) {
		this.notaCabecalho = notaCabecalho;
	}

	public String getAtribuicao() {
		return atribuicao;
	}

	public void setAtribuicao(String atribuicao) {
		this.atribuicao = atribuicao;
	}

	public Calendar getVencimento() {
		return vencimento;
	}

	public void setVencimento(Calendar vencimento) {
		this.vencimento = vencimento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getCodBarras() {
		return codBarras;
	}

	public void setCodBarras(String codBarras) {
		this.codBarras = codBarras;
	}

	public BigDecimal getDesconto() {
		return desconto;
	}

	public TipoDirecaoFinanceiro getTipoDirecaoFinanceiro() {
		return tipoDirecaoFinanceiro;
	}

	public void setTipoDirecaoFinanceiro(TipoDirecaoFinanceiro tipoDirecaoFinanceiro) {
		this.tipoDirecaoFinanceiro = tipoDirecaoFinanceiro;
	}

	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}

	public BigDecimal getAcrescimo() {
		return acrescimo;
	}

	public void setAcrescimo(BigDecimal acrescimo) {
		this.acrescimo = acrescimo;
	}

	public StatusPagamentoFinanceiro getStatusPagamentoFinanceiro() {
		return statusPagamentoFinanceiro;
	}

	public void setStatusPagamentoFinanceiro(StatusPagamentoFinanceiro statusPagamentoFinanceiro) {
		this.statusPagamentoFinanceiro = statusPagamentoFinanceiro;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public boolean isExcluido() {
		return excluido;
	}

	public void setExcluido(boolean excluido) {
		this.excluido = excluido;
	}

	public TipoPagamentoFinanceiro getTipoPagamentoFinanceiro() {
		return tipoPagamentoFinanceiro;
	}

	public void setTipoPagamentoFinanceiro(TipoPagamentoFinanceiro tipoPagamentoFinanceiro) {
		this.tipoPagamentoFinanceiro = tipoPagamentoFinanceiro;
	}

	public List<Financeiro> getFinanceirosRelacionados() {
		return financeirosRelacionados;
	}

	public void setFinanceirosRelacionados(List<Financeiro> financeirosRelacionados) {
		this.financeirosRelacionados = financeirosRelacionados;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((acrescimo == null) ? 0 : acrescimo.hashCode());
		result = prime * result + ((atribuicao == null) ? 0 : atribuicao.hashCode());
		result = prime * result + ((codBarras == null) ? 0 : codBarras.hashCode());
		result = prime * result + codigo;
		result = prime * result + ((desconto == null) ? 0 : desconto.hashCode());
		result = prime * result + ((notaCabecalho == null) ? 0 : notaCabecalho.hashCode());
		result = prime * result + ((statusPagamentoFinanceiro == null) ? 0 : statusPagamentoFinanceiro.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
		result = prime * result + ((vencimento == null) ? 0 : vencimento.hashCode());
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
		Financeiro other = (Financeiro) obj;
		if (acrescimo == null) {
			if (other.acrescimo != null)
				return false;
		} else if (!acrescimo.equals(other.acrescimo))
			return false;
		if (atribuicao == null) {
			if (other.atribuicao != null)
				return false;
		} else if (!atribuicao.equals(other.atribuicao))
			return false;
		if (codBarras == null) {
			if (other.codBarras != null)
				return false;
		} else if (!codBarras.equals(other.codBarras))
			return false;
		if (codigo != other.codigo)
			return false;
		if (desconto == null) {
			if (other.desconto != null)
				return false;
		} else if (!desconto.equals(other.desconto))
			return false;
		if (notaCabecalho == null) {
			if (other.notaCabecalho != null)
				return false;
		} else if (!notaCabecalho.equals(other.notaCabecalho))
			return false;
		if (statusPagamentoFinanceiro != other.statusPagamentoFinanceiro)
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		if (vencimento == null) {
			if (other.vencimento != null)
				return false;
		} else if (!vencimento.equals(other.vencimento))
			return false;
		return true;
	}
}
