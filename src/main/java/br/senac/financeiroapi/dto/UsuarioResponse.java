package br.senac.financeiroapi.dto;

import java.time.LocalDateTime;

public record UsuarioResponse(
        Integer id,
        String nome,
        String email,
        Boolean status,
        String foto,
        LocalDateTime dataCriacao,
        Integer empresaId,
        String empresaNome
) {
}
