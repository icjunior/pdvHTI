package br.com.htisoftware.pdv.service;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import br.com.htisoftware.pdv.dao.CupomDAO;
import br.com.htisoftware.pdv.dto.CupomDTO;
import br.com.htisoftware.pdv.dto.FormaPagamentoDTO;
import br.com.htisoftware.pdv.modelo.Cupom;
import br.com.htisoftware.pdv.modelo.Usuario;

public class CupomService implements Serializable {

	private static final long serialVersionUID = -276486896204934866L;
	@Inject
	CupomDAO dao;

	public List<Cupom> buscaCupons(int operacao, Calendar dataInicial, Calendar dataFinal, int pdv, Usuario operador) {
		return dao.buscaCupons(operacao, dataInicial, dataFinal, pdv, operador);
	}

	public List<CupomDTO> buscaCupomPorFinalizadora(FormaPagamentoDTO formaPagamento, Calendar dataInicial,
			Calendar dataFinal) {
		return dao.buscaCupomPorFinalizadora(formaPagamento, dataInicial, dataFinal);
	}
}
