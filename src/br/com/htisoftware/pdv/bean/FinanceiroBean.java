package br.com.htisoftware.pdv.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.com.htisoftware.pdv.dto.FinanceiroDTO;
import br.com.htisoftware.pdv.enums.StatusPagamentoFinanceiro;
import br.com.htisoftware.pdv.enums.TipoMovimentacaoEstoque;
import br.com.htisoftware.pdv.modelo.Cliente;
import br.com.htisoftware.pdv.modelo.Financeiro;
import br.com.htisoftware.pdv.service.FinanceiroService;
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

	public void buscar() {
		financeiros = financeiroService.buscar(financeiroDTO);
	}

	public void incluirCliente() {
		PdvUtils.abreDialog("/dialog/cliente_consulta", false, "100%", "85vh");
	}

	public void clienteSelecionado(SelectEvent event) {
		Cliente cliente = (Cliente) event.getObject();
		financeiroDTO.setFornecedor(cliente);
	}

	public List<StatusPagamentoFinanceiro> getStatusFinanceiro() {
		return Arrays.asList(StatusPagamentoFinanceiro.values());
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
}
