package br.senac.financeiroapi.dto;

import br.senac.financeiroapi.enums.ContaTipo;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ContaRequest(
        @NotBlank(message = "A descrição da conta é obrigatória")
        @Size(max = 255, message = "A descrição deve ter no máximo 255 caracteres")
        String descricao,

        @NotNull(message = "O valor da conta é obrigatório")
        @DecimalMin(value = "0.01", message = "O valor da conta deve ser maior que zero")
        BigDecimal valor,

        @DecimalMin(value = "0.00", message = "O valor pago não pode ser negativo")
        BigDecimal valorPago,

        @NotNull(message = "O tipo da conta é obrigatório")
        ContaTipo tipo,

        Integer status,

        @NotNull(message = "A data de vencimento é obrigatória")
        LocalDate dataVencimento,

        LocalDate dataPagamento,

        Integer categoriaId,

        @NotNull(message = "O ID do usuário é obrigatório")
        Integer usuarioId
) {
}
