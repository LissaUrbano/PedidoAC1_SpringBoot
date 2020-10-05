package com.projetoac1.exerciciopedido.controllers;

import com.projetoac1.exerciciopedido.models.Pedido;
import com.projetoac1.exerciciopedido.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidosController {
    @Autowired
    private PedidoRepository repositorio;

    @GetMapping()
    public List<Pedido> get() {
        return repositorio.getAll();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Pedido> get(@PathVariable int codigo) {
        var pedido = repositorio.get(codigo);

        if (pedido == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(pedido);
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody Pedido pedido) {
        var novoCodigo = repositorio.gerarProximoCodigo();
        var cliente = pedido.getCliente();
        var valor = pedido.getValor();
        var descricao = pedido.getDescricao();

        var novoPedido = new Pedido(novoCodigo, cliente, valor, descricao);

        repositorio.insert(novoPedido);

        var uri = URI.create("http://localhost:8080/pedidos/" + novoCodigo);

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{codigo}")
    public ResponseEntity put(@PathVariable int codigo, @RequestBody Pedido pedido) {
        var pedidoExistente = repositorio.get(codigo);

        if (pedidoExistente == null) {
            return ResponseEntity.notFound().build();
        }

        pedidoExistente.setValor(pedido.getValor());
        pedidoExistente.setDescricao(pedido.getDescricao());

        repositorio.update(pedidoExistente);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity delete(@PathVariable int codigo) {
        var pedidoExistente = repositorio.get(codigo);

        if (pedidoExistente == null) {
            return ResponseEntity.notFound().build();
        }

        repositorio.delete(pedidoExistente);

        return ResponseEntity.noContent().build();
    }
}
