package br.com.htisoftware.pdv.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.htisoftware.pdv.dao.CfopDAO;
import br.com.htisoftware.pdv.modelo.CFOP;

public class CfopService implements Serializable {

	private static final long serialVersionUID = -3224476767228243399L;
	@Inject
	CfopDAO dao;

	public List<CFOP> findAll() {
		return dao.findAll();
	}
}
