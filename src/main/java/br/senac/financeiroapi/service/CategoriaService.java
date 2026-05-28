package br.senac.financeiroapi.service;

import br.senac.financeiroapi.dto.CategoriaRequest;
import br.senac.financeiroapi.dto.CategoriaResponse;
import br.senac.financeiroapi.entity.Categoria;
import br.senac.financeiroapi.entity.Usuario;
import br.senac.financeiroapi.exception.ResourceNotFoundException;
import br.senac.financeiroapi.repository.CategoriaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final UsuarioService usuarioService;

    public CategoriaService(CategoriaRepository categoriaRepository, UsuarioService usuarioService) {
        this.categoriaRepository = categoriaRepository;
        this.usuarioService = usuarioService;
    }

    @Transactional(readOnly = true)
    public List<CategoriaResponse> listar() {
        return categoriaRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public CategoriaResponse buscarPorId(Integer id) {
        return toResponse(buscarEntidadePorId(id));
    }

    @Transactional
    public CategoriaResponse criar(CategoriaRequest request) {
        Categoria categoria = new Categoria();
        aplicarDados(categoria, request);
        return toResponse(categoriaRepository.save(categoria));
    }

    @Transactional
    public CategoriaResponse atualizar(Integer id, CategoriaRequest request) {
        Categoria categoria = buscarEntidadePorId(id);
        aplicarDados(categoria, request);
        return toResponse(categoriaRepository.save(categoria));
    }

    @Transactional
    public void excluir(Integer id) {
        Categoria categoria = buscarEntidadePorId(id);
        categoria.setStatus(-1);
        categoriaRepository.delete(categoria);
    }

    public Categoria buscarEntidadePorId(Integer id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada com ID: " + id));
    }

    public Categoria buscarEntidadeOpcional(Integer id) {
        if (id == null) {
            return null;
        }
        return buscarEntidadePorId(id);
    }

    private void aplicarDados(Categoria categoria, CategoriaRequest request) {
        Usuario usuario = usuarioService.buscarEntidadePorId(request.usuarioId());
        categoria.setNome(request.nome());
        categoria.setTipo(request.tipo());
        categoria.setUsuario(usuario);
    }

    private CategoriaResponse toResponse(Categoria categoria) {
        return new CategoriaResponse(
                categoria.getId(),
                categoria.getNome(),
                categoria.getTipo(),
                categoria.getUsuario().getId(),
                categoria.getUsuario().getNome()
        );
    }
}
