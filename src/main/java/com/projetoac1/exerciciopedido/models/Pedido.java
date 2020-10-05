package com.projetoac1.exerciciopedido.models;

import java.time.LocalDateTime;
import java.util.Date;

public class Pedido {
    private int codigo;
    private double valor;
    private String descricao;
    private String cliente;
    private LocalDateTime data;

    public int getCodigo() {
        return codigo;
    }

    public double getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getCliente() {
        return cliente;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Pedido(int codigo, String cliente, double valor, String descricao) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.data = LocalDateTime.now();

        setValor(valor);
        setDescricao(descricao);
    }
}
