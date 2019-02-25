package br.com.htisoftware.pdv.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.inject.Inject;

import org.primefaces.PrimeFaces;

import br.com.htisoftware.pdv.dao.CupomDAO;
import br.com.htisoftware.pdv.dto.ResumoOperadorDTO;
import br.com.htisoftware.pdv.enums.TipoOperacao;
import br.com.htisoftware.pdv.modelo.Configuracao;
import br.com.htisoftware.pdv.modelo.Cupom;
import br.com.htisoftware.pdv.modelo.CupomItem;
import br.com.htisoftware.pdv.modelo.CupomPagamento;
import br.com.htisoftware.pdv.modelo.FormaPagamento;
import br.com.htisoftware.pdv.modelo.PedidoItem;
import br.com.htisoftware.pdv.modelo.Sessao;
import br.com.htisoftware.pdv.modelo.Usuario;
import br.com.htisoftware.pdv.modelo.UsuarioLogado;
import br.com.htisoftware.pdv.service.SessaoService;

public class PdvUtils {

	@Inject
	SessaoService sessaoService;
	@Inject
	CupomDAO cupomDAO;

	public static BigDecimal getSubTotal(List<CupomItem> itens) {
		BigDecimal subTotal = new BigDecimal(0.00);
		if (itens != null) {
			for (CupomItem item : itens) {
				subTotal = subTotal.add(item.getValor().multiply(item.getQuantidade()));
			}
		}
		return subTotal;
	}

	public static void mensagem(Severity tipoMensagem, String cabecalho, String texto) {
		FacesMessage mensagem = new FacesMessage(tipoMensagem, cabecalho, texto);
		PrimeFaces.current().dialog().showMessageDynamic(mensagem);
	}

	public static void openDialog(String id) {
		PrimeFaces.current().executeScript("PF('" + id + "').show()");
	}

	public static void closeDialog(String id) {
		PrimeFaces.current().executeScript("PF('" + id + "').hide()");
	}

	public static boolean isCupomZerado(List<CupomItem> itens) {
		return getSubTotal(itens).compareTo(new BigDecimal("0")) == 0;
	}

	public static Cupom montaCupomCabecalho(UsuarioLogado usuarioLogado, List<CupomItem> itens, boolean cancelado,
			Configuracao configuracao, BigDecimal troco, String cpfCnpj, String operacao, BigDecimal valor,
			boolean preVenda, String observacao) {
		return new Cupom(Calendar.getInstance(),
				(TipoOperacao.DESCRICAO.VENDA.equals(operacao)) ? PdvUtils.getSubTotal(itens) : valor,
				usuarioLogado.getUsuario(), cancelado, configuracao.getTerminal(), troco, cpfCnpj, operacao,
				usuarioLogado.getUsuario().getLoja(), preVenda, observacao);
	}

	public static List<CupomPagamento> montaCupomPagamento(FormaPagamento formaPagamento, BigDecimal valor) {
		List<CupomPagamento> pagamentos = new ArrayList<>();

		CupomPagamento pagamento = new CupomPagamento();
		pagamento.setFormaPagamento(formaPagamento);
		pagamento.setValor(valor);

		pagamentos.add(pagamento);

		return pagamentos;
	}

	public static BigDecimal getTroco(BigDecimal subTotal, CupomPagamento pagamento) {
		return pagamento.getValor().subtract(subTotal);
	}

	public static BigDecimal getTotalPago(List<CupomPagamento> pagamentos) {
		return pagamentos.stream().map(p -> p.getValor()).reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public static BigDecimal getValorRestante(BigDecimal subTotal, BigDecimal totalPago) {
		return subTotal.subtract(totalPago).abs();
	}

	public static void abreDialog(String dialog, boolean fecharDialog, String largura, String altura) {
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("width", largura);
		options.put("height", altura);
		options.put("contentWidth", "100%");
		options.put("resizable", false);
		options.put("modal", true);
		options.put("closeOnEscape", fecharDialog);
		PrimeFaces.current().dialog().openDynamic(dialog, options, null);
	}

	public static Sessao loginSesssaoOperador(Usuario usuario) {
		Sessao sessao = new Sessao();
		sessao.setEntrada(Calendar.getInstance());
		sessao.setUsuario(usuario);
		return sessao;
	}

	public Sessao logoutSessaoOperador() {
		Sessao sessaoAberta = sessaoService.sessaoAberta();
		sessaoAberta.setSaida(Calendar.getInstance());
		return sessaoAberta;
	}

	public BigDecimal totalDinheiroPorOperador(UsuarioLogado usuarioLogado) {
		return cupomDAO.totalDinheiroEmCaixaPorOperador(usuarioLogado.getUsuario(), "Dinheiro");
	}

	public List<ResumoOperadorDTO> totalPorOperador(Usuario usuario, Calendar data) {
		return cupomDAO.valoresResumidosOperacoesPorOperador(usuario, data);
	}

	public String preparaCupomSAT(String nfp, List<CupomItem> itens, List<CupomPagamento> pagamentos) {
		StringBuilder cupom = new StringBuilder();
		DecimalFormat format = new DecimalFormat("###.0000");
		DecimalFormat formatValor = new DecimalFormat("###.00");
		DecimalFormat formataCodigo = new DecimalFormat("00");

		// cabeçalho
		cupom.append("<dest>");
		if (!nfp.equals("")) {
			cupom.append("<CPF>" + nfp + "</CPF>");
		}
		cupom.append("</dest>");

		// itens
		itens.forEach(item -> {

			cupom.append("<prod>" + "<cProd>" + item.getProduto().getEan() + "</cProd>" + "<xProd>"
					+ item.getProduto().getDescricao() + "</xProd>" + "<NCM>" + item.getProduto().getNcm() + "</NCM>"
					+ "<CFOP>5001</CFOP>" + "<uCom>un</uCom>" + "<qCom>"
					+ format.format(item.getQuantidade()).replace(",", ".") + "</qCom>" + "<vUnCom>"
					+ formatValor.format(item.getValor()).replace(",", ".") + "</vUnCom>" + "<indRegra>A</indRegra>"
					+ "</prod>");

			String imposto = criaTagImposto(item);
			cupom.append(imposto);
		});

		// totalizador
		cupom.append("<total>" + "<DescAcrEntr>" + "<vDescSubtot>0.00</vDescSubtot>" + "</DescAcrEntr>"
				+ "<vCFeLei12741>" + PdvUtils.getSubTotal(itens) + "</vCFeLei12741>" + "</total>");

		// pagamentos
		pagamentos.forEach(pagamento -> {
			cupom.append("<MP>" + "<cMP>" + formataCodigo.format(pagamento.getFormaPagamento().getCodigo()) + "</cMP>"
					+ "<vMP>" + formatValor.format(pagamento.getValor()).replace(",", ".") + "</vMP>" + "</MP>");
		});

		// informações complementares
		cupom.append("<infAdic>" + "<infCpl>Obrigada e volte sempre!</infCpl>" + "</infAdic>");

		return cupom.toString();
	}

	private String criaTagImposto(CupomItem item) {
		StringBuilder imposto = new StringBuilder("<imposto><ICMS>");
		// try {
		// if (item.getProduto().getCstIcms().equals("00") ||
		// item.getProduto().getCstIcms().equals("20")
		// || item.getProduto().getCstIcms().equals("90")) {
		// imposto.append("<ICMS00><Orig>0</Orig><CST>" + item.getProduto().getCstIcms()
		// + "</CST><pICMS>5.00</pICMS></ICMS00></ICMS><PIS><PISAliq><CST>" +
		// item.getProduto().getCstPis()
		// + "</CST><vBC>1.00</vBC>" + "<pPIS>" + item.getProduto().getPis()
		// + "</pPIS></PISAliq></PIS><COFINS><COFINSAliq><CST>" +
		// item.getProduto().getCstCofins()
		// + "</CST><vBC>1.00</vBC><pCOFINS>" + item.getProduto().getCofins() +
		// "</pCOFINS>"
		// + "</COFINSAliq></COFINS>");
		// } else {
		// imposto.append("<ICMS40><Orig>0</Orig><CST>40</CST></ICMS40>");
		// }
		//
		// imposto.append("<PIS>");
		// if (item.getProduto().getCstPis().equals("01") ||
		// item.getProduto().getCstPis().equals("02")
		// || item.getProduto().getCstPis().equals("05")) {
		// imposto.append("<PISAliq><CST>" + item.getProduto().getCstPis() +
		// "</CST><vBC>1.00</vBC><pPIS>"
		// + item.getProduto().getPis() + "</pPIS></PISAliq>");
		// } else {
		// if (item.getProduto().getCstPis().equals("03")) {
		// imposto.append(
		// "<PISQtde><CST>" + item.getProduto().getCstPis() + "</CST><qBCProd>" +
		// item.getQuantidade()
		// + "</qBCProd><vAliqProd>" + item.getValor() + "</vAliqProd></PISQtde>");
		// } else {
		// if (item.getProduto().getCstPis().equals("04") ||
		// item.getProduto().getCstPis().equals("06")
		// || item.getProduto().getCstPis().equals("07") ||
		// item.getProduto().getCstPis().equals("08")
		// || item.getProduto().getCstPis().equals("09")) {
		// imposto.append("<PISNT><CST>" + item.getProduto().getCstPis() +
		// "</CST></PISNT>");
		// } else {
		// if (item.getProduto().getCstPis().equals("49")) {
		// imposto.append("<PISSN><CST>" + item.getProduto().getCstPis() +
		// "</CST></PISSN>");
		// } else {
		// if (item.getProduto().getCstPis().equals("99")) {
		// imposto.append("<PISOutr><CST>" + item.getProduto().getCstPis() + "</CST>" +
		// "<vBC>"
		// + item.getValorTotal() + "</vBC>" + "<pPIS>" + item.getProduto().getPis()
		// + "</pPIS>" + "<qBCProd>" + item.getQuantidade() + "</qBCProd>" +
		// "<vAliqProd>"
		// + item.getProduto().getPis() + "</vAliqProd>" + "</pPIS>" + "</PISOutr>");
		// }
		// }
		// }
		// }
		// }
		// imposto.append("</PIS>");
		//
		// imposto.append("<COFINS>");
		// if (item.getProduto().getCstCofins().equals("01") ||
		// item.getProduto().getCstCofins().equals("02")
		// || item.getProduto().getCstCofins().equals("05")) {
		// imposto.append(
		// "<COFINSAliq><CST>" + item.getProduto().getCstCofins() + "</CST><vBC>" +
		// item.getValorTotal()
		// + "</vBC><pCOFINS>" + item.getProduto().getCofins() +
		// "</pCOFINS></COFINSAliq>");
		// } else {
		// if (item.getProduto().getCstCofins().equals("03")) {
		// imposto.append("<COFINSQtde><CST>" + item.getProduto().getCstCofins() +
		// "</CST><qBCProd>"
		// + item.getQuantidade() + "</qBCProd></COFINSQtde>");
		// } else {
		// if (item.getProduto().getCstCofins().equals("04") ||
		// item.getProduto().getCstCofins().equals("06")
		// || item.getProduto().getCstCofins().equals("07")
		// || item.getProduto().getCstCofins().equals("08")
		// || item.getProduto().getCstCofins().equals("09")) {
		// imposto.append("<COFINSNT><CST>" + item.getProduto().getCstCofins() +
		// "</CST></COFINSNT>");
		// } else {
		// if (item.getProduto().getCstCofins().equals("49")) {
		// imposto.append("<COFINSSN><CST>" + item.getProduto().getCstCofins() +
		// "</CST></COFINSSN>");
		// } else {
		// if (item.getProduto().getCstCofins().equals("99")) {
		// imposto.append("<COFINSOutr><CST>" + item.getProduto().getCstCofins() +
		// "</CST><vBC>"
		// + item.getValorTotal() + "</vBC><pCOFINS>" + item.getProduto().getCofins()
		// + "</pCOFINS><qBCProd>" + item.getQuantidade() + "</qBCProd><vAliqProd>"
		// + item.getProduto().getCofins() + "</vAliqProd></COFINSOutr>");
		// }
		// }
		// }
		// }
		// }
		// imposto.append("</COFINS>");
		//
		// imposto.append("</imposto>");
		// return imposto.toString();
		// } catch (Exception e) {
		// PdvUtils.mensagem(FacesMessage.SEVERITY_ERROR, "Erro ao buscar impostos",
		// "Materiais sem impostos definidos. Revise o cadastro do produto e tente
		// finalizar novamente.");
		// throw new FacesException(
		// "Materiais sem impostos definidos. Revise o cadastro do produto e tente
		// finalizar novamente.");
		// }
		return null;
	}

	public String preparaCupomPreVenda(String cliente, List<CupomItem> itens, List<CupomPagamento> pagamentos) {
		StringBuilder preVenda = new StringBuilder();
		preVenda.append("<ce>Armazem do cimento</ce>\n");
		preVenda.append("<ce>Av. Presidente Vargas, 1723 - Jd. Cerejeiras</ce>\n");
		preVenda.append("<ce>Atibaia/SP - CEP: 12951-000</ce>\n");
		preVenda.append("<ce>Telefone: (11) 4412-7234</ce>\n");
		preVenda.append("<tc>-</tc>\n");
		preVenda.append("Pedido: 910506 - <dti></dti> <hri></hri>\n");
		preVenda.append("00001<sp>1</sp>Cimento CPII Holcim 50kg<sp>1</sp>1.000<sp>1</sp>16,00<sp>1</sp>16,00\n");
		preVenda.append("<tc>-</tc>\n");
		preVenda.append("SubTotal<ad>16,00</ad>\n");
		preVenda.append("Desconto<ad>0,00</ad>\n");
		preVenda.append("Total<ad>0,00</ad>\n");
		preVenda.append("Dinheiro<ad>0,00</ad>\n");
		preVenda.append("Troco<ad>0,00</ad>\n");
		preVenda.append("<sl>5</sl><gui></gui>");
		return preVenda.toString();
	}

	public static List<CupomItem> montaItemCupom(List<PedidoItem> itensPedido) {
		List<CupomItem> itens = new ArrayList<>();
		itensPedido.forEach(i -> itens.add(new CupomItem(i.getCodigo(), 1, i.getProduto(), i.getValor(),
				i.getQuantidade(), i.getDataLancamento())));
		return itens;
	}

	public static void reorganizarSequenciaRegistro(List<CupomItem> itens) {
		int sequencia = 0;
		for (CupomItem item : itens) {
			sequencia++;
			item.setSequencia(sequencia);
		}
	}
}