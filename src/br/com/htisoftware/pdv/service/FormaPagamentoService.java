package br.com.htisoftware.pdv.service;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import br.com.htisoftware.pdv.dao.FormaPagamentoDAO;
import br.com.htisoftware.pdv.dto.FormaPagamentoDTO;
import br.com.htisoftware.pdv.modelo.FormaPagamento;

public class FormaPagamentoService implements Serializable {

	private static final long serialVersionUID = -3240350304383304464L;
	@Inject
	FormaPagamentoDAO dao;

	public List<FormaPagamento> findAll() {
		return dao.findAll();
	}

	public List<FormaPagamentoDTO> buscarPorDataEPDV(Calendar dataInicial, Calendar dataFinal, int pdv) {
		return dao.buscarPorDataEPDV(dataInicial, dataFinal, pdv);
	}
}