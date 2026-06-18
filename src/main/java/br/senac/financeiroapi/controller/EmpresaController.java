package br.senac.financeiroapi.controller;

import br.senac.financeiroapi.dto.EmpresaRequest;
import br.senac.financeiroapi.dto.EmpresaResponse;
import br.senac.financeiroapi.service.EmpresaService;
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
@RequestMapping("/api/empresas")
@Tag(name = "Empresas", description = "CRUD de empresas")
@CrossOrigin("*")    
public class EmpresaController {

    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @GetMapping
    @Operation(summary = "Lista todas as empresas")
    public ResponseEntity<List<EmpresaResponse>> listar() {
        return ResponseEntity.ok(empresaService.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca uma empresa por ID")
    public ResponseEntity<EmpresaResponse> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(empresaService.buscarPorId(id));
    }

    @PostMapping
    @Operation(summary = "Cria uma empresa")
    public ResponseEntity<EmpresaResponse> criar(@Valid @RequestBody EmpresaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(empresaService.criar(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza uma empresa")
    public ResponseEntity<EmpresaResponse> atualizar(@PathVariable Integer id, @Valid @RequestBody EmpresaRequest request) {
        return ResponseEntity.ok(empresaService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclui uma empresa")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        empresaService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
