package br.com.htisoftware.pdv.dao;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.htisoftware.pdv.dto.CupomDTO;
import br.com.htisoftware.pdv.dto.FormaPagamentoDTO;
import br.com.htisoftware.pdv.dto.ResumoOperadorDTO;
import br.com.htisoftware.pdv.enums.TipoOperacao;
import br.com.htisoftware.pdv.modelo.Cliente;
import br.com.htisoftware.pdv.modelo.Cupom;
import br.com.htisoftware.pdv.modelo.CupomItem;
import br.com.htisoftware.pdv.modelo.CupomPagamento;
import br.com.htisoftware.pdv.modelo.Produto;
import br.com.htisoftware.pdv.modelo.Usuario;
import br.com.htisoftware.pdv.util.DataUtils;

public class CupomDAO {

	@Inject
	EntityManager em;
	final static String OPERACAO_VENDA = "Venda";
	final static String OPERACAO_PRE_VENDA = "Pr";

	public void registraItens(List<CupomItem> itens, Cupom cupom) {
		itens.forEach(i -> {
			em.getTransaction().begin();
			i.setValor(i.getProduto().getValor());
			i.setCupom(cupom);
			em.persist(i);
			em.getTransaction().commit();
		});
	}

	public void registraPagamentos(List<CupomPagamento> pagamentos, Cupom cupom) {
		pagamentos.forEach(p -> {
			em.getTransaction().begin();
			p.setCupom(cupom);
			em.persist(p);
			em.getTransaction().commit();
		});
	}

	public void registraCupom(Cupom cupom) {
		em.getTransaction().begin();
		em.persist(cupom);
		em.getTransaction().commit();
	}

	public BigDecimal totalDinheiroEmCaixaPorOperador(Usuario usuario, String formaPagamento) {
		ResumoOperadorDTO entradas;
		ResumoOperadorDTO saidas;

		Calendar dataInicial = Calendar.getInstance();
		dataInicial.set(Calendar.HOUR, 00);
		dataInicial.set(Calendar.MINUTE, 00);
		dataInicial.set(Calendar.SECOND, 00);

		Calendar dataFinal = Calendar.getInstance();
		dataFinal.set(Calendar.HOUR, 23);
		dataFinal.set(Calendar.MINUTE, 59);
		dataFinal.set(Calendar.SECOND, 59);

		String jpql = "SELECT new br.com.htisoftware.pdv.dto.ResumoOperadorDTO(c.formaPagamento.nome, SUM(c.valor - c.troco)) FROM CupomPagamento c WHERE c.cupom.operacao != :pOperacao AND c.cupom.data BETWEEN :pDataInicial AND :pDataFinal AND c.cupom.usuario.codigo = :pUsuario AND c.formaPagamento.nome = :pFormaPagamento GROUP BY c.formaPagamento.nome";
		TypedQuery<ResumoOperadorDTO> query = em.createQuery(jpql, ResumoOperadorDTO.class);
		query.setParameter("pFormaPagamento", formaPagamento);
		query.setParameter("pUsuario", usuario.getCodigo());
		query.setParameter("pDataInicial", dataInicial);
		query.setParameter("pDataFinal", dataFinal);
		query.setParameter("pOperacao", TipoOperacao.DESCRICAO.SANGRIA);
		try {
			entradas = query.getSingleResult();
		} catch (NoResultException e) {
			entradas = new ResumoOperadorDTO("Dinheiro", new BigDecimal(0.00));
		}

		String jpqlSaidas = "SELECT new br.com.htisoftware.pdv.dto.ResumoOperadorDTO(c.formaPagamento.nome, SUM(c.valor - c.troco)) FROM CupomPagamento c WHERE c.cupom.operacao = :pOperacao AND c.cupom.data BETWEEN :pDataInicial AND :pDataFinal AND c.cupom.usuario.codigo = :pUsuario AND c.formaPagamento.nome = :pFormaPagamento GROUP BY c.formaPagamento.nome";
		TypedQuery<ResumoOperadorDTO> querySaidas = em.createQuery(jpqlSaidas, ResumoOperadorDTO.class);
		querySaidas.setParameter("pFormaPagamento", formaPagamento);
		querySaidas.setParameter("pUsuario", usuario.getCodigo());
		querySaidas.setParameter("pDataInicial", dataInicial);
		querySaidas.setParameter("pDataFinal", dataFinal);
		querySaidas.setParameter("pOperacao", TipoOperacao.DESCRICAO.SANGRIA);
		try {
			saidas = querySaidas.getSingleResult();
		} catch (NoResultException e) {
			saidas = new ResumoOperadorDTO("Dinheiro", new BigDecimal(0.00));
		}
		return entradas.getTotal().subtract(saidas.getTotal());
	}

	public List<ResumoOperadorDTO> valoresResumidosOperacoesPorOperador(Usuario usuario, Calendar data) {
		Calendar dataInicial = DataUtils.criaDataInicial(data);
		Calendar dataFinal = DataUtils.criaDataFinal(data);

		String jpql = "SELECT new br.com.htisoftware.pdv.dto.ResumoOperadorDTO(c.operacao, SUM(c.valor)) FROM Cupom c WHERE c.operacao != :pOperacao AND c.data BETWEEN :pDataInicial AND :pDataFinal AND c.usuario.codigo = :pUsuario GROUP BY c.operacao";
		TypedQuery<ResumoOperadorDTO> query = em.createQuery(jpql, ResumoOperadorDTO.class);
		query.setParameter("pOperacao", "Venda");
		query.setParameter("pDataInicial", dataInicial);
		query.setParameter("pDataFinal", dataFinal);
		query.setParameter("pUsuario", usuario.getCodigo());

		List<ResumoOperadorDTO> operacoes = query.getResultList();

		String jpqlFinalizadoras = "SELECT new br.com.htisoftware.pdv.dto.ResumoOperadorDTO(c.formaPagamento.nome, SUM(c.valor - c.troco)) FROM CupomPagamento c WHERE c.cupom.operacao = :pOperacao AND c.cupom.data BETWEEN :pDataInicial AND :pDataFinal AND c.cupom.usuario.codigo = :pUsuario GROUP BY c.formaPagamento.nome";
		TypedQuery<ResumoOperadorDTO> queryFinalizadoras = em.createQuery(jpqlFinalizadoras, ResumoOperadorDTO.class);
		queryFinalizadoras.setParameter("pOperacao", "Venda");
		queryFinalizadoras.setParameter("pDataInicial", dataInicial);
		queryFinalizadoras.setParameter("pDataFinal", dataFinal);
		queryFinalizadoras.setParameter("pUsuario", usuario.getCodigo());

		operacoes.addAll(queryFinalizadoras.getResultList());

		return operacoes;
	}

	public List<Cupom> buscaCupons(int operacao, Calendar dataInicial, Calendar dataFinal, int pdv, Usuario operador) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Cupom> query = builder.createQuery(Cupom.class);
		Root<Cupom> from = query.from(Cupom.class);
		Predicate predicate = builder.and();

		predicate = builder.between(from.get("data"), DataUtils.criaDataInicial(dataInicial),
				DataUtils.criaDataFinal(dataFinal));

		if (pdv != 0) {
			predicate = builder.and(predicate, builder.equal(from.get("terminal"), pdv));
		}

		if (operacao == 1) {
			predicate = builder.and(predicate, builder.equal(from.get("preVenda"), false));
			predicate = builder.and(predicate, builder.equal(from.get("operacao"), TipoOperacao.DESCRICAO.VENDA));
		} else {
			if (operacao == 2) {
				predicate = builder.and(predicate, builder.equal(from.get("preVenda"), true));
			} else {
				predicate = builder.and(predicate, builder.equal(from.get("operacao"), TipoOperacao.DESCRICAO.VENDA));
			}
		}

		// if (operador.getCodigo() != 0) {
		// predicate = builder.equal(from.get("usuario.codigo"), operador.getCodigo());
		// }

		TypedQuery<Cupom> typedQuery = em
				.createQuery(query.select(from).where(predicate).orderBy(builder.asc(from.get("codigo"))));

		return typedQuery.getResultList();
	}

	public List<CupomItem> buscaItens(Calendar dataInicial, Calendar dataFinal, int pdv, int cupom, Cliente cliente,
			Produto produto) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<CupomItem> query = builder.createQuery(CupomItem.class);
		Root<CupomItem> fromItem = query.from(CupomItem.class);
		Predicate predicate = builder.and();
		// List<Predicate> predicates = new ArrayList<>();

		// join
		Join<CupomItem, Cupom> join = fromItem.join("cupom");
		Join<CupomItem, Produto> joinProduto = fromItem.join("produto");
		Join<Cupom, Cliente> joinCliente = join.join("cliente", JoinType.LEFT);

		// where
		predicate = builder.between(join.get("data"), DataUtils.criaDataInicial(dataInicial),
				DataUtils.criaDataFinal(dataFinal));
		// predicates.add(predicate);

		if (pdv != 0) {
			predicate = builder.and(predicate, builder.equal(join.get("terminal"), pdv));
			// predicates.add(predicate);
		}

		if (cupom != 0) {
			predicate = builder.and(predicate, builder.equal(join.get("codigo"), cupom));
			// predicates.add(predicate);
		}

		if (produto != null) {
			predicate = builder.and(predicate, builder.equal(joinProduto.get("ean"), produto.getEan()));
		}

		if (cliente != null) {
			predicate = builder.and(predicate, builder.equal(joinCliente.get("codigo"), cliente.getCodigo()));
		}

		// Predicate[] pr = new Predicate[predicates.size()];
		// predicates.toArray(pr);

		TypedQuery<CupomItem> typedQuery = em
				.createQuery(query.select(fromItem).where(predicate).orderBy(builder.asc(fromItem.get("codigo"))));

		return typedQuery.getResultList();
	}

	public List<CupomDTO> buscaCupomPorFinalizadora(FormaPagamentoDTO formaPagamento, Calendar dataInicial,
			Calendar dataFinal) {
		String jpql = "SELECT new br.com.htisoftware.pdv.dto.CupomDTO(c.cupom.data, c.cupom.terminal, c.cupom.codigo, c.cupom.valor, c.valor - c.troco) FROM CupomPagamento c WHERE c.cupom.operacao = :pOperacao AND c.cupom.data BETWEEN :pDataInicial AND :pDataFinal AND c.formaPagamento.nome = :pFormaPagamento AND c.cupom.terminal = :pPDV";

		TypedQuery<CupomDTO> query = em.createQuery(jpql, CupomDTO.class);
		query.setParameter("pOperacao", "Venda");
		query.setParameter("pDataInicial", DataUtils.criaDataInicial(dataInicial));
		query.setParameter("pDataFinal", DataUtils.criaDataFinal(dataFinal));
		query.setParameter("pFormaPagamento", formaPagamento.getNome());
		query.setParameter("pPDV", formaPagamento.getPdv());

		return query.getResultList();
	}
}