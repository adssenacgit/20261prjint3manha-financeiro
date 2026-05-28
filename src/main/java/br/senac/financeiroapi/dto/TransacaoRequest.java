package br.senac.financeiroapi.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransacaoRequest(
        @Size(max = 300, message = "A descrição deve ter no máximo 300 caracteres")
        String descricao,

        @NotNull(message = "O valor da transação é obrigatório")
        @DecimalMin(value = "0.01", message = "O valor da transação deve ser maior que zero")
        BigDecimal valor,

        @NotNull(message = "A data da transação é obrigatória")
        LocalDate data,

        Integer categoriaId,

        @NotNull(message = "O ID do usuário é obrigatório")
        Integer usuarioId,

        Integer contaId
) {
}
