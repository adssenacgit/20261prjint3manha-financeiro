package br.senac.financeiroapi.controller;

import br.senac.financeiroapi.dto.ContaRequest;
import br.senac.financeiroapi.dto.ContaResponse;
import br.senac.financeiroapi.service.ContaService;
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

import java.util.List;

@RestController
@RequestMapping("/api/contas")
@Tag(name = "Contas", description = "CRUD de contas a pagar e receber")
public class ContaController {

    private final ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @GetMapping
    @Operation(summary = "Lista todas as contas")
    public ResponseEntity<List<ContaResponse>> listar() {
        return ResponseEntity.ok(contaService.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca uma conta por ID")
    public ResponseEntity<ContaResponse> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(contaService.buscarPorId(id));
    }

    @PostMapping
    @Operation(summary = "Cria uma conta")
    public ResponseEntity<ContaResponse> criar(@Valid @RequestBody ContaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contaService.criar(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza uma conta")
    public ResponseEntity<ContaResponse> atualizar(@PathVariable Integer id, @Valid @RequestBody ContaRequest request) {
        return ResponseEntity.ok(contaService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclui uma conta")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        contaService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
