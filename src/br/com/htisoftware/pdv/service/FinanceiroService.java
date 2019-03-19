package br.com.htisoftware.pdv.service;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.inject.Inject;

import br.com.htisoftware.pdv.dao.FinanceiroDAO;
import br.com.htisoftware.pdv.dto.FinanceiroDTO;
import br.com.htisoftware.pdv.dto.FinanceiroMassaDTO;
import br.com.htisoftware.pdv.enums.StatusPagamentoFinanceiro;
import br.com.htisoftware.pdv.modelo.Financeiro;
import br.com.htisoftware.pdv.util.ERPUtils;
import br.com.htisoftware.pdv.util.PdvUtils;

public class FinanceiroService implements Serializable {

	private static final long serialVersionUID = -9128067928161035739L;
	@Inject
	FinanceiroDAO dao;

	public List<Financeiro> buscar(FinanceiroDTO financeiroDTO) {
		return dao.buscar(financeiroDTO);
	}

	public void excluir(List<Financeiro> financeirosSelecionados) {
		dao.excluir(financeirosSelecionados);
		PdvUtils.mensagem(FacesMessage.SEVERITY_INFO, "Financeiro", "Lançamentos excluídos com sucesso");
	}

	public void baixar(List<Financeiro> financeirosSelecionados) {
		dao.baixar(financeirosSelecionados);
		PdvUtils.mensagem(FacesMessage.SEVERITY_INFO, "Financeiro", "Lançamentos baixados com sucesso");
	}

	public void desmembrar(List<Financeiro> financeiros, List<Financeiro> financeirosSelecionados) {
		try {
			financeiros.forEach(financeiro -> dao.gravar(financeiro));
			financeirosSelecionados.forEach(financeiro -> {
				financeiro.setStatusPagamentoFinanceiro(StatusPagamentoFinanceiro.DESMEMBRADO);
				financeiro.setFinanceirosRelacionados(financeiros);
				dao.alterar(financeiro);
			});
			PdvUtils.mensagem(FacesMessage.SEVERITY_INFO, "Desmembramento de título",
					"Desmembramento criado com sucesso.");

		} catch (Exception e) {
			PdvUtils.mensagem(FacesMessage.SEVERITY_ERROR, "Desmembramento de título",
					"Falha ao tentar desmembrar título. Clique no botão CALCULAR e tente novamente.");
		}

	}

	public List<Financeiro> calculaDesmembramento(List<Financeiro> financeirosSelecionados, int numeroParcelas) {
		return ERPUtils.calculaDesmembramento(financeirosSelecionados.get(0), numeroParcelas);
	}

	public void edicaoMassa(FinanceiroMassaDTO financeiroMassa, List<Financeiro> financeirosSelecionados) {
		financeirosSelecionados.forEach(financeiro -> {
			financeiro.edicaoMassa(financeiroMassa);
			dao.alterar(financeiro);
		});
		PdvUtils.closeDialog("financeiroEdicaoMassaDialog");
		PdvUtils.mensagem(FacesMessage.SEVERITY_INFO, "Alteração em massa", "Registros alterados com sucesso.");
	}

	public void edicaoIndividual(List<Financeiro> financeiros) {
		dao.alterar(financeiros.get(0));
		PdvUtils.closeDialog("financeiroEdicaoIndividualDialog");
		PdvUtils.mensagem(FacesMessage.SEVERITY_INFO, "Alteração", "Registro alterado com sucesso.");
	}
}
