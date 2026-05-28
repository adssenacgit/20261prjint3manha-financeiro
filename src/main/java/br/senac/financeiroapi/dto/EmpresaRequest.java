package br.senac.financeiroapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EmpresaRequest(
        @NotBlank(message = "O nome da empresa é obrigatório")
        @Size(max = 200, message = "O nome da empresa deve ter no máximo 200 caracteres")
        String nome,

        @NotBlank(message = "O CNPJ da empresa é obrigatório")
        @Size(max = 20, message = "O CNPJ deve ter no máximo 20 caracteres")
        String cnpj,

        Boolean status
) {
}
