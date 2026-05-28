package br.senac.financeiroapi.repository;

import br.senac.financeiroapi.entity.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {
}
