package br.com.htisoftware.pdv.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.com.htisoftware.pdv.dto.FinanceiroDTO;
import br.com.htisoftware.pdv.dto.FinanceiroMassaDTO;
import br.com.htisoftware.pdv.enums.StatusPagamentoFinanceiro;
import br.com.htisoftware.pdv.enums.TipoMovimentacaoEstoque;
import br.com.htisoftware.pdv.modelo.Cliente;
import br.com.htisoftware.pdv.modelo.Financeiro;
import br.com.htisoftware.pdv.service.FinanceiroService;
import br.com.htisoftware.pdv.util.ERPUtils;
import br.com.htisoftware.pdv.util.PdvUtils;

@Named
@ViewScoped
public class FinanceiroBean implements Serializable {

	private static final long serialVersionUID = -5820993407639910870L;
	@Inject
	FinanceiroDTO financeiroDTO;
	@Inject
	FinanceiroService financeiroService;
	List<Financeiro> financeiros;
	List<Financeiro> financeirosSelecionados;
	List<Financeiro> financeiroDesmembrado;
	private int numeroParcelasDesmembramento;
	@Inject
	FinanceiroMassaDTO financeiroMassa;

	public void buscar() {
		financeiros = financeiroService.buscar(financeiroDTO);
	}

	public void incluirCliente() {
		PdvUtils.abreDialog("/dialog/cliente_consulta", false, "100%", "85vh");
	}

	public void excluir() {
		financeiroService.excluir(financeirosSelecionados);
	}

	public void baixar() {
		financeiroService.baixar(financeirosSelecionados);
	}

	public String novoLancamento() {
		FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().put("mostraItens", false);
		return "/pages/lancamento_nota_entrada?faces-redirect=true";
	}

	public String detalhes() {
		FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().put("financeiro",
				financeirosSelecionados.get(0));
		return "/pages/financeiro_detalhes?faces-redirect=true";
	}

	public void desmembrar() {
		financeiroService.desmembrar(financeiroDesmembrado, financeirosSelecionados);
		financeiroDesmembrado = new ArrayList<>();
	}

	public void calculaDesmembramento() {
		financeiroDesmembrado = financeiroService.calculaDesmembramento(financeirosSelecionados,
				numeroParcelasDesmembramento);
	}

	public void edicaoMassa() {
		financeiroService.edicaoMassa(financeiroMassa, financeirosSelecionados);
	}
	
	public void edicaoIndividual() {
		financeiroService.edicaoIndividual(financeirosSelecionados);
	}

	public void clienteSelecionado(SelectEvent event) {
		Cliente cliente = (Cliente) event.getObject();
		financeiroDTO.setFornecedor(cliente);
	}

	public void excluirDesmembramento(Financeiro financeiro) {
		financeiroDesmembrado.remove(financeiro);
	}

	public List<StatusPagamentoFinanceiro> getStatusFinanceiro() {
		return Arrays.asList(StatusPagamentoFinanceiro.values());
	}

	public BigDecimal getTotalBruto() {
		return ERPUtils.financeiroTotalBruto(financeiros);
	}

	public BigDecimal getTotalLiquido() {
		return ERPUtils.financeiroTotalLiquido(financeiros);
	}

	public BigDecimal getTotalDescontos() {
		return ERPUtils.financeiroTotalDescontos(financeiros);
	}

	public BigDecimal getTotalAcrescimos() {
		return ERPUtils.financeiroTotalAcrescimo(financeiros);
	}

	public List<TipoMovimentacaoEstoque> getTipoMovimentacoesFinanceiro() {
		return Arrays.asList(TipoMovimentacaoEstoque.values());
	}

	public FinanceiroDTO getFinanceiroDTO() {
		return financeiroDTO;
	}

	public void setFinanceiroDTO(FinanceiroDTO financeiroDTO) {
		this.financeiroDTO = financeiroDTO;
	}

	public List<Financeiro> getFinanceiros() {
		return financeiros;
	}

	public void setFinanceiros(List<Financeiro> financeiros) {
		this.financeiros = financeiros;
	}

	public List<Financeiro> getFinanceirosSelecionados() {
		return financeirosSelecionados;
	}

	public void setFinanceirosSelecionados(List<Financeiro> financeirosSelecionados) {
		this.financeirosSelecionados = financeirosSelecionados;
	}

	public int getNumeroParcelasDesmembramento() {
		return numeroParcelasDesmembramento;
	}

	public void setNumeroParcelasDesmembramento(int numeroParcelasDesmembramento) {
		this.numeroParcelasDesmembramento = numeroParcelasDesmembramento;
	}

	public List<Financeiro> getFinanceiroDesmembrado() {
		return financeiroDesmembrado;
	}

	public void setFinanceiroDesmembrado(List<Financeiro> financeiroDesmembrado) {
		this.financeiroDesmembrado = financeiroDesmembrado;
	}

	public FinanceiroMassaDTO getFinanceiroMassa() {
		return financeiroMassa;
	}

	public void setFinanceiroMassa(FinanceiroMassaDTO financeiroMassa) {
		this.financeiroMassa = financeiroMassa;
	}
}
