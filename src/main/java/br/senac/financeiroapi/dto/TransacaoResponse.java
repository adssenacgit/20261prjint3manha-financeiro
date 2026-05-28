package br.senac.financeiroapi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record TransacaoResponse(
        Integer id,
        String descricao,
        BigDecimal valor,
        LocalDate data,
        LocalDateTime criadaEm,
        Integer categoriaId,
        String categoriaNome,
        Integer usuarioId,
        String usuarioNome,
        Integer contaId,
        String contaDescricao
) {
}
