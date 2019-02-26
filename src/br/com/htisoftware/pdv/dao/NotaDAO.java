package br.com.htisoftware.pdv.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.htisoftware.pdv.modelo.NotaCabecalho;
import br.com.htisoftware.pdv.modelo.NotaItem;

public class NotaDAO {

	@Inject
	EntityManager em;

	public void gravar(NotaCabecalho nota) {
		List<NotaItem> itens = nota.getItens();
		em.getTransaction().begin();
		em.persist(nota);
		itens.forEach(item -> {
			item.setNotaCabecalho(nota);
			em.persist(item);
		});
		em.getTransaction().commit();
	}
}
