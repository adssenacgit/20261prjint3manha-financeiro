package br.senac.financeiroapi.repository;

import br.senac.financeiroapi.entity.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, Integer> {
}
