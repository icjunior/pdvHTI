package br.com.htisoftware.pdv.dao;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.htisoftware.pdv.dto.FinanceiroDTO;
import br.com.htisoftware.pdv.modelo.Cliente;
import br.com.htisoftware.pdv.modelo.Financeiro;
import br.com.htisoftware.pdv.modelo.NotaCabecalho;

public class FinanceiroDAO {

	@Inject
	EntityManager em;

	public List<Financeiro> buscar(FinanceiroDTO financeiroDTO) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Financeiro> query = builder.createQuery(Financeiro.class);
		Root<Financeiro> from = query.from(Financeiro.class);
		Predicate predicate = builder.and();

		Join<Financeiro, NotaCabecalho> joinNota = from.join("notaCabecalho");
		Join<Financeiro, Cliente> joinCliente = from.join("cliente");

		if (financeiroDTO.getTipoMovimentacaoEstoque() != null) {
			predicate = builder.and(predicate,
					builder.equal(from.get("tipoMovimentacaoEstoque"), financeiroDTO.getTipoMovimentacaoEstoque()));
		}

		if (financeiroDTO.getFornecedor() != null) {
			predicate = builder.and(predicate,
					builder.equal(joinCliente.get("codigo"), financeiroDTO.getFornecedor().getCodigo()));
		}

		if (financeiroDTO.getEmissao() != null) {
			predicate = builder.and(predicate, builder.equal(joinNota.get("emissao"), financeiroDTO.getEmissao()));
		}

		if (financeiroDTO.getLancamento() != null) {
			predicate = builder.and(predicate,
					builder.equal(joinNota.get("lancamento"), financeiroDTO.getLancamento()));
		}

		if (financeiroDTO.getStatusFinanceiro() != null) {
			predicate = builder.and(predicate,
					builder.equal(from.get("statusFinanceiro"), financeiroDTO.getStatusFinanceiro()));
		}

		if (financeiroDTO.getNf() != null) {
			predicate = builder.and(predicate, builder.equal(from.get("emissao"), financeiroDTO.getEmissao()));
		}

		if (financeiroDTO.getVencimento() != null) {
			predicate = builder.and(predicate, builder.equal(from.get("vencimento"), financeiroDTO.getVencimento()));
		}

		TypedQuery<Financeiro> typedQuery = em
				.createQuery(query.select(from).where(predicate).orderBy(builder.asc(from.get("codigo"))));

		return typedQuery.getResultList();
	}
}
