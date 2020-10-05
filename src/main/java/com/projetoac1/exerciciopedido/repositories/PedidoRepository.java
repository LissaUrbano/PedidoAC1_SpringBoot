package com.projetoac1.exerciciopedido.repositories;

import com.projetoac1.exerciciopedido.models.Pedido;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PedidoRepository {
    private int codigoAtual = 1;
    private List<Pedido> pedidos = new ArrayList<>();

    public Pedido get(int codigo) {
        for (Pedido pedido : pedidos) {
            if (pedido.getCodigo() == codigo) {
                return pedido;
            }
        }

        return null;
    }

    public List<Pedido> getAll() {
        return pedidos;
    }

    public int gerarProximoCodigo() {
        return codigoAtual++;
    }

    public void insert(Pedido pedido) {
        this.pedidos.add(pedido);
    }

    public void update(Pedido pedido) {
        var pedidoExistente = get(pedido.getCodigo());

        if (pedidoExistente != null) {
            pedidoExistente.setValor(pedido.getValor());
            pedidoExistente.setDescricao(pedido.getDescricao());
        }
    }

    public boolean delete(Pedido pedido) {
        return this.pedidos.remove(pedido);
    }
}
