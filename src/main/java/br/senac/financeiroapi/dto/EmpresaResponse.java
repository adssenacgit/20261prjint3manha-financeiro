package br.senac.financeiroapi.dto;

import java.time.LocalDateTime;

public record EmpresaResponse(
        Integer id,
        String nome,
        String cnpj,
        Boolean status,
        LocalDateTime criadaEm
) {
}
