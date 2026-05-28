package br.senac.financeiroapi.dto;

import br.senac.financeiroapi.enums.CategoriaTipo;

public record CategoriaResponse(
        Integer id,
        String nome,
        CategoriaTipo tipo,
        Integer usuarioId,
        String usuarioNome
) {
}
