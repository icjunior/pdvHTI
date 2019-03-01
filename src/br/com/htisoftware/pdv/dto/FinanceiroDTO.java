package br.com.htisoftware.pdv.dto;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Calendar;

import br.com.htisoftware.pdv.enums.StatusPagamentoFinanceiro;
import br.com.htisoftware.pdv.enums.TipoMovimentacaoEstoque;
import br.com.htisoftware.pdv.modelo.Cliente;

public class FinanceiroDTO implements Serializable {

	private static final long serialVersionUID = -8764617726778239528L;
	private Calendar emissao;
	private Calendar lancamento;
	private Calendar vencimento;
	private StatusPagamentoFinanceiro statusFinanceiro;
	private Cliente fornecedor;
	private TipoMovimentacaoEstoque tipoMovimentacaoEstoque;
	private BigInteger nf;

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

	public StatusPagamentoFinanceiro getStatusFinanceiro() {
		return statusFinanceiro;
	}

	public void setStatusFinanceiro(StatusPagamentoFinanceiro statusFinanceiro) {
		this.statusFinanceiro = statusFinanceiro;
	}

	public Cliente getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Cliente fornecedor) {
		this.fornecedor = fornecedor;
	}

	public TipoMovimentacaoEstoque getTipoMovimentacaoEstoque() {
		return tipoMovimentacaoEstoque;
	}

	public void setTipoMovimentacaoEstoque(TipoMovimentacaoEstoque tipoMovimentacaoEstoque) {
		this.tipoMovimentacaoEstoque = tipoMovimentacaoEstoque;
	}

	public BigInteger getNf() {
		return nf;
	}

	public void setNf(BigInteger nf) {
		this.nf = nf;
	}

	public Calendar getVencimento() {
		return vencimento;
	}

	public void setVencimento(Calendar vencimento) {
		this.vencimento = vencimento;
	}
}
