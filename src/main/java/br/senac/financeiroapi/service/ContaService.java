package br.senac.financeiroapi.service;

import br.senac.financeiroapi.dto.ContaRequest;
import br.senac.financeiroapi.dto.ContaResponse;
import br.senac.financeiroapi.entity.Categoria;
import br.senac.financeiroapi.entity.Conta;
import br.senac.financeiroapi.entity.Usuario;
import br.senac.financeiroapi.exception.ResourceNotFoundException;
import br.senac.financeiroapi.repository.ContaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContaService {

    private final ContaRepository contaRepository;
    private final CategoriaService categoriaService;
    private final UsuarioService usuarioService;

    public ContaService(ContaRepository contaRepository, CategoriaService categoriaService, UsuarioService usuarioService) {
        this.contaRepository = contaRepository;
        this.categoriaService = categoriaService;
        this.usuarioService = usuarioService;
    }

    @Transactional(readOnly = true)
    public List<ContaResponse> listar() {
        return contaRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public ContaResponse buscarPorId(Integer id) {
        return toResponse(buscarEntidadePorId(id));
    }

    @Transactional
    public ContaResponse criar(ContaRequest request) {
        Conta conta = new Conta();
        aplicarDados(conta, request);
        return toResponse(contaRepository.save(conta));
    }

    @Transactional
    public ContaResponse atualizar(Integer id, ContaRequest request) {
        Conta conta = buscarEntidadePorId(id);
        aplicarDados(conta, request);
        return toResponse(contaRepository.save(conta));
    }

    @Transactional
    public void excluir(Integer id) {
        Conta conta = buscarEntidadePorId(id);
        conta.setStatus(-1);
        contaRepository.delete(conta);
    }

    public Conta buscarEntidadePorId(Integer id) {
        return contaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Conta não encontrada com ID: " + id));
    }

    public Conta buscarEntidadeOpcional(Integer id) {
        if (id == null) {
            return null;
        }
        return buscarEntidadePorId(id);
    }

    private void aplicarDados(Conta conta, ContaRequest request) {
        Categoria categoria = categoriaService.buscarEntidadeOpcional(request.categoriaId());
        Usuario usuario = usuarioService.buscarEntidadePorId(request.usuarioId());
        conta.setDescricao(request.descricao());
        conta.setValor(request.valor());
        conta.setValorPago(request.valorPago());
        conta.setTipo(request.tipo());
        conta.setStatus(request.status());
        conta.setDataVencimento(request.dataVencimento());
        conta.setDataPagamento(request.dataPagamento());
        conta.setCategoria(categoria);
        conta.setUsuario(usuario);
    }

    private ContaResponse toResponse(Conta conta) {
        Categoria categoria = conta.getCategoria();
        Usuario usuario = conta.getUsuario();
        return new ContaResponse(
                conta.getId(),
                conta.getDescricao(),
                conta.getValor(),
                conta.getValorPago(),
                conta.getTipo(),
                conta.getStatus(),
                conta.getDataVencimento(),
                conta.getDataPagamento(),
                conta.getCriadaEm(),
                categoria != null ? categoria.getId() : null,
                categoria != null ? categoria.getNome() : null,
                usuario.getId(),
                usuario.getNome()
        );
    }
}
