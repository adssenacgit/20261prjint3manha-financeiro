package br.senac.financeiroapi.dto;

import br.senac.financeiroapi.enums.ContaTipo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record ContaResponse(
        Integer id,
        String descricao,
        BigDecimal valor,
        BigDecimal valorPago,
        ContaTipo tipo,
        Integer status,
        LocalDate dataVencimento,
        LocalDate dataPagamento,
        LocalDateTime criadaEm,
        Integer categoriaId,
        String categoriaNome,
        Integer usuarioId,
        String usuarioNome
) {
}
