package br.api.gestaoalunos.repository;

import br.api.gestaoalunos.models.AlunoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AlunoRepository extends JpaRepository<AlunoModel, UUID> {

    List<AlunoModel> findByNomeContainingIgnoreCase(String nome);

    List<AlunoModel> findByCursoContainingIgnoreCase(String curso);
}