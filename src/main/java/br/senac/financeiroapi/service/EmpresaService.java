package br.senac.financeiroapi.service;

import br.senac.financeiroapi.dto.EmpresaRequest;
import br.senac.financeiroapi.dto.EmpresaResponse;
import br.senac.financeiroapi.entity.Empresa;
import br.senac.financeiroapi.exception.ResourceNotFoundException;
import br.senac.financeiroapi.repository.EmpresaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    @Transactional(readOnly = true)
    public List<EmpresaResponse> listar() {
        return empresaRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public EmpresaResponse buscarPorId(Integer id) {
        return toResponse(buscarEntidadePorId(id));
    }

    @Transactional
    public EmpresaResponse criar(EmpresaRequest request) {
        Empresa empresa = new Empresa();
        aplicarDados(empresa, request);
        return toResponse(empresaRepository.save(empresa));
    }

    @Transactional
    public EmpresaResponse atualizar(Integer id, EmpresaRequest request) {
        Empresa empresa = buscarEntidadePorId(id);
        aplicarDados(empresa, request);
        return toResponse(empresaRepository.save(empresa));
    }

    @Transactional
    public void excluir(Integer id) {
        Empresa empresa = buscarEntidadePorId(id);
        empresaRepository.delete(empresa);
    }

    public Empresa buscarEntidadePorId(Integer id) {
        return empresaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empresa não encontrada com ID: " + id));
    }

    private void aplicarDados(Empresa empresa, EmpresaRequest request) {
        empresa.setNome(request.nome());
        empresa.setCnpj(request.cnpj());
        empresa.setStatus(request.status() != null ? request.status() : true);
    }

    private EmpresaResponse toResponse(Empresa empresa) {
        return new EmpresaResponse(
                empresa.getId(),
                empresa.getNome(),
                empresa.getCnpj(),
                empresa.getStatus(),
                empresa.getCriadaEm()
        );
    }
}
