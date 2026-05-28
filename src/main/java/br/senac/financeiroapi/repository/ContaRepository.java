package br.senac.financeiroapi.repository;

import br.senac.financeiroapi.entity.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, Integer> {
}
