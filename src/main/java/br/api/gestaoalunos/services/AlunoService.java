package br.api.gestaoalunos.services;

import br.api.gestaoalunos.dtos.AlunoDto;
import br.api.gestaoalunos.models.AlunoModel;
import br.api.gestaoalunos.repository.AlunoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AlunoService {
    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    @Transactional
    public AlunoModel create(AlunoDto dto) {
        AlunoModel aluno = new AlunoModel();
        this.mapDtoToModel(dto, aluno);
        return alunoRepository.save(aluno);
    }

    @Transactional
    public AlunoModel atualizar(UUID id, AlunoDto dto) {
        AlunoModel alunoExistente = this.buscar(id);
        this.mapDtoToModel(dto, alunoExistente);
        return alunoRepository.save(alunoExistente);
    }

    @Transactional
    public void apagar(UUID id) {
        AlunoModel aluno = this.buscar(id);
        alunoRepository.delete(aluno);
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public List<AlunoModel> listar() {
        return alunoRepository.findAll();
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public AlunoModel buscar(UUID id) {
        return alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado com o ID: " + id));
    }


    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public List<AlunoModel> buscarPorNome(String nomeBusca) {
        List<AlunoModel> alunos = alunoRepository.findByNomeContainingIgnoreCase(nomeBusca);
        if (alunos.isEmpty()) {
            throw new RuntimeException("Nenhum aluno encontrado com o nome: " + nomeBusca);
        }
        return alunos;
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public List<AlunoModel> buscarPorCurso(String cursoBusca) {
        List<AlunoModel> alunos = alunoRepository.findByCursoContainingIgnoreCase(cursoBusca);
        if (alunos.isEmpty()) {
            throw new RuntimeException("Nenhum aluno encontrado no curso: " + cursoBusca);
        }
        return alunos;
    }

    private void mapDtoToModel(AlunoDto dto, AlunoModel model) {
        model.setNome(dto.getNome());
        model.setCurso(dto.getCurso());
        model.setTelefone(dto.getTelefone());
    }
}