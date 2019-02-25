package br.com.htisoftware.pdv.service;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import br.com.htisoftware.pdv.dao.PedidoDAO;
import br.com.htisoftware.pdv.enums.StatusPedido;
import br.com.htisoftware.pdv.modelo.Pedido;
import br.com.htisoftware.pdv.modelo.PedidoItem;
import br.com.htisoftware.pdv.modelo.UsuarioLogado;
import br.com.htisoftware.pdv.util.PedidoUtils;

public class PedidoService implements Serializable {

	private static final long serialVersionUID = 2950680538174865227L;
	@Inject
	PedidoDAO dao;

	public int gravar(UsuarioLogado usuarioLogado, List<PedidoItem> itensPedido) {
		Pedido pedido = PedidoUtils.montarCabecalhoPedido(usuarioLogado);
		int codigoPedido = dao.gravar(pedido, itensPedido);
		return codigoPedido;
	}

	public void cancelar(Pedido pedido) {
		pedido.setStatusPedido(StatusPedido.Cancelado);
		dao.cancelar(pedido);
	}

	public List<Pedido> find(int numeroPedido, Calendar dataInicial, Calendar dataFinal) {
		return dao.find(numeroPedido, dataInicial, dataFinal);
	}

	public Pedido findById(int numeroPedido) {
		return dao.findById(numeroPedido);
	}
}
