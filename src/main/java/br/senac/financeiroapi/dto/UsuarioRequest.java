package br.senac.financeiroapi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UsuarioRequest(
        @NotBlank(message = "O nome do usuário é obrigatório")
        @Size(max = 200, message = "O nome do usuário deve ter no máximo 200 caracteres")
        String nome,

        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "O e-mail deve ser válido")
        @Size(max = 200, message = "O e-mail deve ter no máximo 200 caracteres")
        String email,

        @NotBlank(message = "A senha é obrigatória")
        @Size(max = 255, message = "A senha deve ter no máximo 255 caracteres")
        String senha,

        Boolean status,

        @Size(max = 255, message = "A foto deve ter no máximo 255 caracteres")
        String foto,

        @NotNull(message = "O ID da empresa é obrigatório")
        Integer empresaId
) {
}
