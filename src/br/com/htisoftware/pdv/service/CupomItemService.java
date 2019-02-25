package br.com.htisoftware.pdv.service;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import br.com.htisoftware.pdv.dao.CupomDAO;
import br.com.htisoftware.pdv.modelo.Cliente;
import br.com.htisoftware.pdv.modelo.CupomItem;
import br.com.htisoftware.pdv.modelo.Produto;

public class CupomItemService implements Serializable {

	private static final long serialVersionUID = -5897133893622343922L;
	@Inject
	CupomDAO dao;

	public List<CupomItem> buscar(Calendar dataInicial, Calendar dataFinal, int pdv, int cupom, Cliente cliente,
			Produto produto) {
		return dao.buscaItens(dataInicial, dataFinal, pdv, cupom, cliente, produto);
	}
}
