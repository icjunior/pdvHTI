package br.com.htisoftware.pdv.util;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import br.com.htisoftware.pdv.enums.StatusPedido;
import br.com.htisoftware.pdv.modelo.Pedido;
import br.com.htisoftware.pdv.modelo.PedidoItem;
import br.com.htisoftware.pdv.modelo.UsuarioLogado;

public class PedidoUtils {

	public static BigDecimal getSubTotal(List<PedidoItem> itens) {
		return itens.stream().filter(item -> item.getQuantidade() != null)
				.map(item -> item.getValor().multiply(item.getQuantidade())).reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public static Pedido montarCabecalhoPedido(UsuarioLogado usuarioLogado) {
		Pedido pedido = new Pedido();
		pedido.setDataInclusao(Calendar.getInstance());
		//pedido.setUsuario(usuarioLogado.getUsuario());
		pedido.setStatusPedido(StatusPedido.Aberto);
		return pedido;
	}

	public static boolean itensSemQuantidade(List<PedidoItem> itens) {
		for (PedidoItem i : itens) {
			if (i.getQuantidade() == null) {
				return true;
			}

			if (i.getQuantidade().compareTo(new BigDecimal(0)) != 1) {
				return true;
			}
		}
		return false;
	}
}