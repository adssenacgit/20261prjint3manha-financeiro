package br.senac.financeiroapi.service;

import br.senac.financeiroapi.dto.UsuarioRequest;
import br.senac.financeiroapi.dto.UsuarioResponse;
import br.senac.financeiroapi.entity.Empresa;
import br.senac.financeiroapi.entity.Usuario;
import br.senac.financeiroapi.exception.ResourceNotFoundException;
import br.senac.financeiroapi.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final EmpresaService empresaService;

    public UsuarioService(UsuarioRepository usuarioRepository, EmpresaService empresaService) {
        this.usuarioRepository = usuarioRepository;
        this.empresaService = empresaService;
    }

    @Transactional(readOnly = true)
    public List<UsuarioResponse> listar() {
        return usuarioRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public UsuarioResponse buscarPorId(Integer id) {
        return toResponse(buscarEntidadePorId(id));
    }

    @Transactional
    public UsuarioResponse criar(UsuarioRequest request) {
        Usuario usuario = new Usuario();
        aplicarDados(usuario, request);
        return toResponse(usuarioRepository.save(usuario));
    }

    @Transactional
    public UsuarioResponse atualizar(Integer id, UsuarioRequest request) {
        Usuario usuario = buscarEntidadePorId(id);
        aplicarDados(usuario, request);
        return toResponse(usuarioRepository.save(usuario));
    }

    @Transactional
    public void excluir(Integer id) {
        Usuario usuario = buscarEntidadePorId(id);
        usuarioRepository.delete(usuario);
    }

    public Usuario buscarEntidadePorId(Integer id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com ID: " + id));
    }

    private void aplicarDados(Usuario usuario, UsuarioRequest request) {
        Empresa empresa = empresaService.buscarEntidadePorId(request.empresaId());
        usuario.setNome(request.nome());
        usuario.setEmail(request.email());
        usuario.setSenha(request.senha());
        usuario.setStatus(request.status() != null ? request.status() : true);
        usuario.setFoto(request.foto());
        usuario.setEmpresa(empresa);
    }

    private UsuarioResponse toResponse(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getStatus(),
                usuario.getFoto(),
                usuario.getDataCriacao(),
                usuario.getEmpresa().getId(),
                usuario.getEmpresa().getNome()
        );
    }
}
