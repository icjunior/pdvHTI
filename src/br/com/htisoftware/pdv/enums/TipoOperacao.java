package br.com.htisoftware.pdv.enums;

public interface TipoOperacao {

	interface DESCRICAO {
		String VENDA = "Venda";
		String SANGRIA = "Sangria";
		String REFORCO = "Reforço";
		String PAGAMENTO = "Pagamento";
		String FUNDO_TROCO = "Fundo";
		String CANCELAMENTO = "Cancelamento";
	}

	interface OPERACAO {
		String VENDA = "+";
		String SANGRIA = "-";
		String REFORCO = "+";
		String PAGAMENTO = "+";
		String FUNDO_TROCO = "+";
		String CANCELAMENTO = "-";
	}
}