package br.senac.financeiroapi.controller;

import br.senac.financeiroapi.dto.TransacaoRequest;
import br.senac.financeiroapi.dto.TransacaoResponse;
import br.senac.financeiroapi.service.TransacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RestController
@RequestMapping("/api/transacoes")
@Tag(name = "Transações", description = "CRUD de transações financeiras")
@CrossOrigin("*")    
public class TransacaoController {

    private final TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @GetMapping
    @Operation(summary = "Lista todas as transações")
    public ResponseEntity<List<TransacaoResponse>> listar() {
        return ResponseEntity.ok(transacaoService.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca uma transação por ID")
    public ResponseEntity<TransacaoResponse> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(transacaoService.buscarPorId(id));
    }

    @PostMapping
    @Operation(summary = "Cria uma transação")
    public ResponseEntity<TransacaoResponse> criar(@Valid @RequestBody TransacaoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(transacaoService.criar(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza uma transação")
    public ResponseEntity<TransacaoResponse> atualizar(@PathVariable Integer id, @Valid @RequestBody TransacaoRequest request) {
        return ResponseEntity.ok(transacaoService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclui uma transação")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        transacaoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
