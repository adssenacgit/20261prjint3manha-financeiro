package br.senac.financeiroapi.dto;

import br.senac.financeiroapi.enums.CategoriaTipo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CategoriaRequest(
        @NotBlank(message = "O nome da categoria é obrigatório")
        @Size(max = 200, message = "O nome da categoria deve ter no máximo 200 caracteres")
        String nome,

        @NotNull(message = "O tipo da categoria é obrigatório")
        CategoriaTipo tipo,

        @NotNull(message = "O ID do usuário é obrigatório")
        Integer usuarioId
) {
}
