package br.senac.financeiroapi.service;

import br.senac.financeiroapi.dto.TransacaoRequest;
import br.senac.financeiroapi.dto.TransacaoResponse;
import br.senac.financeiroapi.entity.Categoria;
import br.senac.financeiroapi.entity.Conta;
import br.senac.financeiroapi.entity.Transacao;
import br.senac.financeiroapi.entity.Usuario;
import br.senac.financeiroapi.exception.ResourceNotFoundException;
import br.senac.financeiroapi.repository.TransacaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;
    private final CategoriaService categoriaService;
    private final UsuarioService usuarioService;
    private final ContaService contaService;

    public TransacaoService(
            TransacaoRepository transacaoRepository,
            CategoriaService categoriaService,
            UsuarioService usuarioService,
            ContaService contaService
    ) {
        this.transacaoRepository = transacaoRepository;
        this.categoriaService = categoriaService;
        this.usuarioService = usuarioService;
        this.contaService = contaService;
    }

    @Transactional(readOnly = true)
    public List<TransacaoResponse> listar() {
        return transacaoRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public TransacaoResponse buscarPorId(Integer id) {
        return toResponse(buscarEntidadePorId(id));
    }

    @Transactional
    public TransacaoResponse criar(TransacaoRequest request) {
        Transacao transacao = new Transacao();
        aplicarDados(transacao, request);
        return toResponse(transacaoRepository.save(transacao));
    }

    @Transactional
    public TransacaoResponse atualizar(Integer id, TransacaoRequest request) {
        Transacao transacao = buscarEntidadePorId(id);
        aplicarDados(transacao, request);
        return toResponse(transacaoRepository.save(transacao));
    }

    @Transactional
    public void excluir(Integer id) {
        Transacao transacao = buscarEntidadePorId(id);
        transacaoRepository.delete(transacao);
    }

    public Transacao buscarEntidadePorId(Integer id) {
        return transacaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transação não encontrada com ID: " + id));
    }

    private void aplicarDados(Transacao transacao, TransacaoRequest request) {
        Categoria categoria = categoriaService.buscarEntidadeOpcional(request.categoriaId());
        Usuario usuario = usuarioService.buscarEntidadePorId(request.usuarioId());
        Conta conta = contaService.buscarEntidadeOpcional(request.contaId());
        transacao.setDescricao(request.descricao());
        transacao.setValor(request.valor());
        transacao.setData(request.data());
        transacao.setCategoria(categoria);
        transacao.setUsuario(usuario);
        transacao.setConta(conta);
    }

    private TransacaoResponse toResponse(Transacao transacao) {
        Categoria categoria = transacao.getCategoria();
        Usuario usuario = transacao.getUsuario();
        Conta conta = transacao.getConta();
        return new TransacaoResponse(
                transacao.getId(),
                transacao.getDescricao(),
                transacao.getValor(),
                transacao.getData(),
                transacao.getCriadaEm(),
                categoria != null ? categoria.getId() : null,
                categoria != null ? categoria.getNome() : null,
                usuario.getId(),
                usuario.getNome(),
                conta != null ? conta.getId() : null,
                conta != null ? conta.getDescricao() : null
        );
    }
}
