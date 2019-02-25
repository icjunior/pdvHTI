package br.com.htisoftware.pdv.modelo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import br.com.htisoftware.pdv.enums.StatusPedido;

@Entity
public class Pedido implements Serializable {

	private static final long serialVersionUID = -6522012535453737117L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pedido_sequence")
	@SequenceGenerator(name = "pedido_sequence", sequenceName = "pedido_sequence")
	private int codigo;
	private Calendar dataInclusao;
	private Calendar dataAlteracao;
	@ManyToOne
	private Usuario usuario;
	@ManyToOne
	private Loja loja;
	@Enumerated(EnumType.STRING)
	private StatusPedido statusPedido;
	@OneToMany(mappedBy = "pedido")
	List<PedidoItem> pedidoItens;
	@ManyToOne
	private Cliente cliente;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Calendar getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Calendar dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public Calendar getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(Calendar dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Loja getLoja() {
		return loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}

	public StatusPedido getStatusPedido() {
		return statusPedido;
	}

	public void setStatusPedido(StatusPedido statusPedido) {
		this.statusPedido = statusPedido;
	}

	public List<PedidoItem> getPedidoItens() {
		return pedidoItens;
	}

	public void setPedidoItens(List<PedidoItem> pedidoItens) {
		this.pedidoItens = pedidoItens;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
