package br.senac.financeiroapi.controller;

import br.senac.financeiroapi.dto.CategoriaRequest;
import br.senac.financeiroapi.dto.CategoriaResponse;
import br.senac.financeiroapi.service.CategoriaService;
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
@RequestMapping("/api/categorias")
@Tag(name = "Categorias", description = "CRUD de categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    @Operation(summary = "Lista todas as categorias")
    public ResponseEntity<List<CategoriaResponse>> listar() {
        return ResponseEntity.ok(categoriaService.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca uma categoria por ID")
    public ResponseEntity<CategoriaResponse> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(categoriaService.buscarPorId(id));
    }

    @PostMapping
    @Operation(summary = "Cria uma categoria")
    public ResponseEntity<CategoriaResponse> criar(@Valid @RequestBody CategoriaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.criar(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza uma categoria")
    public ResponseEntity<CategoriaResponse> atualizar(@PathVariable Integer id, @Valid @RequestBody CategoriaRequest request) {
        return ResponseEntity.ok(categoriaService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclui uma categoria")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        categoriaService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
