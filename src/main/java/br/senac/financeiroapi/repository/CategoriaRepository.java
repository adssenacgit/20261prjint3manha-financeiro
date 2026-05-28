package br.senac.financeiroapi.repository;

import br.senac.financeiroapi.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}
