package br.com.baozi.store.controller;

import br.com.baozi.store.model.Pedido;
import br.com.baozi.store.repository.ClienteRepository;
import br.com.baozi.store.repository.PedidoRepository;
import br.com.baozi.store.repository.ProdutoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;

    public PedidoController(PedidoRepository pedidoRepository, ClienteRepository clienteRepository, ProdutoRepository produtoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Pedido pedido) {
        if (pedido.getQuantidade() == null || pedido.getQuantidade() <= 0)
            return ResponseEntity.badRequest().body("A quantidade deve ser maior que zero.");
        if (!clienteRepository.existsById(pedido.getClienteId()))
            return ResponseEntity.badRequest().body("Cliente informado não existe.");
        if (!produtoRepository.existsById(pedido.getProdutoId()))
            return ResponseEntity.badRequest().body("Produto informado não existe.");
        return ResponseEntity.ok(pedidoRepository.save(pedido));
    }

    @GetMapping
    public List<Pedido> listar() { return pedidoRepository.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscar(@PathVariable Long id) {
        return pedidoRepository.findById(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Pedido dados) {
        if (dados.getQuantidade() == null || dados.getQuantidade() <= 0)
            return ResponseEntity.badRequest().body("A quantidade deve ser maior que zero.");
        if (!clienteRepository.existsById(dados.getClienteId()))
            return ResponseEntity.badRequest().body("Cliente informado não existe.");
        if (!produtoRepository.existsById(dados.getProdutoId()))
            return ResponseEntity.badRequest().body("Produto informado não existe.");
        return pedidoRepository.findById(id).map(pedido -> {
            pedido.setClienteId(dados.getClienteId());
            pedido.setProdutoId(dados.getProdutoId());
            pedido.setQuantidade(dados.getQuantidade());
            return ResponseEntity.ok(pedidoRepository.save(pedido));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (!pedidoRepository.existsById(id)) return ResponseEntity.notFound().build();
        pedidoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
