package br.com.htisoftware.pdv.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

import br.com.htisoftware.pdv.enums.TipoOperacao;
import br.com.htisoftware.pdv.modelo.Cliente;
import br.com.htisoftware.pdv.modelo.ClienteProduto;
import br.com.htisoftware.pdv.modelo.Configuracao;
import br.com.htisoftware.pdv.modelo.ConfiguracaoAtiva;
import br.com.htisoftware.pdv.modelo.CupomItem;
import br.com.htisoftware.pdv.modelo.CupomPagamento;
import br.com.htisoftware.pdv.modelo.FormaPagamento;
import br.com.htisoftware.pdv.modelo.Pedido;
import br.com.htisoftware.pdv.modelo.Produto;
import br.com.htisoftware.pdv.modelo.Sessao;
import br.com.htisoftware.pdv.modelo.UsuarioLogado;
import br.com.htisoftware.pdv.modelo.VendaAuxiliar;
import br.com.htisoftware.pdv.service.ConfiguracaoService;
import br.com.htisoftware.pdv.service.FormaPagamentoService;
import br.com.htisoftware.pdv.service.PedidoService;
import br.com.htisoftware.pdv.service.ProdutoService;
import br.com.htisoftware.pdv.service.SessaoService;
import br.com.htisoftware.pdv.service.TabelaClienteService;
import br.com.htisoftware.pdv.service.UsuarioService;
import br.com.htisoftware.pdv.service.VendaService;
import br.com.htisoftware.pdv.util.PdvUtils;

@Named
@ViewScoped
public class VendaBean extends VendaAuxiliar implements Serializable {

	private static final long serialVersionUID = 4251632617131716491L;
	@Inject
	CupomItem cupomItem;
	@Inject
	Produto produto;
	@Inject
	CupomPagamento cupomPagamento;
	@Inject
	UsuarioLogado usuarioLogado;
	@Inject
	ConfiguracaoAtiva config;
	@Inject
	Configuracao configuracao;
	@Inject
	Cliente cliente;
	int sequencia;
	private int funcao;

	@Inject
	PedidoService pedidoService;
	@Inject
	ConfiguracaoService configuracaoService;
	@Inject
	FormaPagamentoService formaPagamentoService;
	@Inject
	VendaService vendaService;
	@Inject
	ProdutoService produtoService;
	@Inject
	SessaoService sessaoService;
	@Inject
	UsuarioService usuarioService;
	@Inject
	CupomPagamento pagamentoSelecionado;
	@Inject
	TabelaClienteService tabelaClienteService;

	List<CupomItem> itens;
	List<CupomPagamento> pagamentos;
	private int numeroPedido;

	@PostConstruct
	private void init() {
		configuracao = configuracaoService.buscar();
		config.carrega(configuracao);

		if (config.getConfiguracao() == null) {
			PrimeFaces.current().executeScript("PF('configDialog').show()");
			PdvUtils.mensagem(FacesMessage.SEVERITY_ERROR, "Erro", "Terminal não configurado.");
			return;
		}

		Sessao sessao = sessaoService.sessaoAberta();

		if (sessao != null) {
			usuarioLogado.loga(sessao.getUsuario());
			if (sessao.getEntrada().get(Calendar.DATE) != Calendar.getInstance().get(Calendar.DATE)) {
				usuarioService.logout();
				PdvUtils.mensagem(FacesMessage.SEVERITY_INFO, "Acesso de usuário", "O operador "
						+ sessao.getUsuario().getNome()
						+ " estava conectado desde o último dia de operação. Sua saída foi feita de forma automática. Um novo operador deverá ser iniciado.");
				return;
			}
		}

		if (!usuarioLogado.isLogado()) {
			PrimeFaces.current().executeScript("PF('operadorLogin').show()");
			return;
		}
		PrimeFaces.current().executeScript("PF('cpfCnpjDialog').show()");
	}

	public void validaProduto() {
		this.produto = produtoService.findByEAN(produto);

		if (this.produto == null) {
			PdvUtils.mensagem(FacesMessage.SEVERITY_WARN, "Erro", "Produto sem cadastro");
			this.produto = new Produto();
			this.cupomItem = new CupomItem();
			return;
		}
		if (cliente != null) {
			ClienteProduto itemTabelado = tabelaClienteService.findByCustomerAndProductValids(cliente, produto);
			if (itemTabelado != null) {
				cupomItem.setValor(itemTabelado.getValor());
				return;
			}
		}

		cupomItem.setValor(produto.getValor());
	}

	public void gravarItem() {
		if (!usuarioLogado.isLogado()) {
			PrimeFaces.current().executeScript("PF('operadorLogin').show()");
			return;
		}

		if (itens == null) {
			this.itens = new ArrayList<>();
		}

		if (this.cupomItem.getValor() == null) {
			this.cupomItem.setValor(this.produto.getValor());
		}
		sequencia++;
		this.cupomItem.setDataLancamento(Calendar.getInstance());
		this.cupomItem.setProduto(this.produto);
		this.cupomItem.setSequencia(sequencia);
		this.itens.add(cupomItem);

		this.produto = new Produto();
		this.cupomItem = new CupomItem();
	}

	public BigDecimal getSubTotal() {
		return PdvUtils.getSubTotal(itens);
	}

	public BigDecimal getTotalPago() {
		if (pagamentos == null) {
			return new BigDecimal(0.00);
		}
		return PdvUtils.getTotalPago(pagamentos);
	}

	public void cancelaItem() {
		int posicaoNaLista = getPosicaoItem() - 1;
		try {
			itens.remove(posicaoNaLista);
		} catch (IndexOutOfBoundsException e) {
			PdvUtils.mensagem(FacesMessage.SEVERITY_ERROR, "Exclusão de material",
					"A posição " + posicaoNaLista + " não existe no cupom fiscal. Tente novamente");
			throw new FacesException("A posição " + posicaoNaLista + " não existe no cupom fiscal. Tente novamente");
		}
		PdvUtils.reorganizarSequenciaRegistro(itens);
	}

	public void produtoSelecionado(SelectEvent event) {
		Produto item = (Produto) event.getObject();
		if (item != null) {
			produto = item;
		}
	}

	public void registrar() {
		if (PdvUtils.isCupomZerado(itens)) {
			PdvUtils.mensagem(FacesMessage.SEVERITY_ERROR, "Erro",
					"Valor do cupom igual a zero. Impossível continuar.");
			return;
		}
		if (this.pagamentos == null) {
			PdvUtils.mensagem(FacesMessage.SEVERITY_ERROR, "Erro", "Cupom sem formas de pagamento.");
			return;
		}

		if (getSubTotal().compareTo(PdvUtils.getTotalPago(pagamentos)) == 1) {
			PdvUtils.mensagem(FacesMessage.SEVERITY_ERROR, "Finalizar cupom",
					"Valor pago é menor do que o subTotal. Impossível registrar cupom.");
			return;
		}

		vendaService.registrar(TipoOperacao.DESCRICAO.VENDA, this.itens, this.pagamentos, this.usuarioLogado,
				this.config, getTroco(), getCpfCnpj(), null, isPreVenda(), false, null);
	}

	public void limpar() {
		this.pagamentos = null;
		this.itens = null;
		this.setTroco(null);
		this.setValorRecebido(null);
		this.setPosicaoItem(0);
		this.setCpfCnpj(null);
	}

	public void confirmaPreVenda() {
		if (isPreVenda()) {
			setPreVenda(false);
			return;
		}
		setPreVenda(true);
	}

	public List<FormaPagamento> getFinalizadora() {
		return formaPagamentoService.findAll();
	}

	public void incluirPagamento() {
		BigDecimal subTotal = PdvUtils.getSubTotal(itens);

		if (subTotal.compareTo(new BigDecimal("0")) == 0) {
			PdvUtils.mensagem(FacesMessage.SEVERITY_WARN, "Inclusão de pagamentos",
					"O cupom não tem nenhum item registrado, portanto, incluir finalizadoras é impossível.");
			return;
		}

		if (pagamentos == null) {
			pagamentos = new ArrayList<>();
		}

		BigDecimal totalPago = PdvUtils.getTotalPago(pagamentos);
		BigDecimal pagamentoRestante = PdvUtils.getValorRestante(subTotal, totalPago);

		if (cupomPagamento.getValor().compareTo(pagamentoRestante) == 1
				&& !cupomPagamento.getFormaPagamento().isPermiteTroco()) {
			PdvUtils.mensagem(FacesMessage.SEVERITY_ERROR, "Erro", "A finalizadora "
					+ cupomPagamento.getFormaPagamento().getNome() + " não possui a função de troco.");
			return;
		}

		if (cupomPagamento.getValor().compareTo(pagamentoRestante) == 1
				&& cupomPagamento.getFormaPagamento().isPermiteTroco()) {
			BigDecimal troco = PdvUtils.getTroco(pagamentoRestante, cupomPagamento);
			setTroco(troco);
			cupomPagamento.setTroco(troco);
		}

		pagamentos.add(cupomPagamento);
		cupomPagamento = new CupomPagamento();
	}

	public void registrarCancelamentoCupom() {
		if (PdvUtils.isCupomZerado(itens)) {
			PdvUtils.mensagem(FacesMessage.SEVERITY_ERROR, "Erro", "Valor do cupom igual a zero. Impossível continuar");
			return;
		}
		vendaService.registrar(TipoOperacao.DESCRICAO.CANCELAMENTO, this.itens, null, this.usuarioLogado, this.config,
				getTroco(), getCpfCnpj(), null, isPreVenda(), true, null);
		this.itens = new ArrayList<>();
		this.pagamentos = new ArrayList<>();
	}

	public void consultaCliente() {
		if (this.itens == null) {
			PdvUtils.abreDialog("dialog/cliente_consulta", true, "100%", "85vh");
			return;
		}
	}

	public void clienteSelecionado(SelectEvent event) {
		cliente = (Cliente) event.getObject();
		PrimeFaces.current().executeScript("$(function(){PrimeFaces.focus('formLancamento:ean');});");
	}

	public void pedidoSelecionado(SelectEvent event) {
		Pedido pedido = (Pedido) event.getObject();
		cliente = pedido.getCliente();
		itens = PdvUtils.montaItemCupom(pedido.getPedidoItens());
	}

	public void loginOperador() {
		PdvUtils.abreDialog("operador_login", false, "100%", "85vh");
	}

	public void logoutOperador() {
		PdvUtils.abreDialog("operador_logout", true, "100%", "85vh");
	}

	public void removePagamento() {
		pagamentos.remove(pagamentoSelecionado);
	}

	public CupomItem getCupomItem() {
		return cupomItem;
	}

	public void setCupomItem(CupomItem cupomItem) {
		this.cupomItem = cupomItem;
	}

	public List<CupomItem> getItens() {
		return itens;
	}

	public void setItens(List<CupomItem> itens) {
		this.itens = itens;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public CupomPagamento getCupomPagamento() {
		return cupomPagamento;
	}

	public void setCupomPagamento(CupomPagamento cupomPagamento) {
		this.cupomPagamento = cupomPagamento;
	}

	public List<CupomPagamento> getPagamentos() {
		return pagamentos;
	}

	public void setPagamentos(List<CupomPagamento> pagamentos) {
		this.pagamentos = pagamentos;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public int getFuncao() {
		return funcao;
	}

	public void setFuncao(int funcao) {
		this.funcao = funcao;
	}

	public CupomPagamento getPagamentoSelecionado() {
		return pagamentoSelecionado;
	}

	public void setPagamentoSelecionado(CupomPagamento pagamentoSelecionado) {
		this.pagamentoSelecionado = pagamentoSelecionado;
	}

	public ConfiguracaoAtiva getConfig() {
		return config;
	}

	public int getNumeroPedido() {
		return numeroPedido;
	}

	public void setNumeroPedido(int numeroPedido) {
		this.numeroPedido = numeroPedido;
	}
}