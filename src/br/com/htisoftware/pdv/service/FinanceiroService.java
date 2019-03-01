package br.com.htisoftware.pdv.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.htisoftware.pdv.dao.FinanceiroDAO;
import br.com.htisoftware.pdv.dto.FinanceiroDTO;
import br.com.htisoftware.pdv.modelo.Financeiro;

public class FinanceiroService implements Serializable {

	private static final long serialVersionUID = -9128067928161035739L;
	@Inject
	FinanceiroDAO dao;

	public List<Financeiro> buscar(FinanceiroDTO financeiroDTO) {
		return dao.buscar(financeiroDTO);
	}

}
