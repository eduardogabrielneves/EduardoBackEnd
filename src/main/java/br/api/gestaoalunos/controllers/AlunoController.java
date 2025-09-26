package br.api.gestaoalunos.controllers;

import br.api.gestaoalunos.dtos.AlunoDto;
import br.api.gestaoalunos.models.AlunoModel;
import br.api.gestaoalunos.services.AlunoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/alunos")
@CrossOrigin(origins = "http://localhost:4200")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @PostMapping("/salvar")
    public ResponseEntity<?> salvar(@RequestBody @Valid AlunoDto dto) {
        AlunoModel alunoSalvo = alunoService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoSalvo);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<AlunoModel>> listar() {
        List<AlunoModel> alunos = alunoService.listar();
        return ResponseEntity.ok(alunos);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable(value = "id") UUID id) {
        try {
            AlunoModel alunoEncontrado = alunoService.buscar(id);
            return ResponseEntity.ok(alunoEncontrado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro ao buscar: " + e.getMessage());
        }
    }

    @GetMapping("/buscar-por-nome")
    public ResponseEntity<?> buscarPorNome(@RequestParam String nomeBusca) {
        try {
            List<AlunoModel> alunosEncontrados = alunoService.buscarPorNome(nomeBusca);
            return ResponseEntity.ok(alunosEncontrados);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro ao buscar por nome: " + e.getMessage());
        }
    }

    @GetMapping("/buscar-por-curso")
    public ResponseEntity<?> buscarPorCurso(@RequestParam String cursoBusca) {
        try {
            List<AlunoModel> alunosEncontrados = alunoService.buscarPorCurso(cursoBusca);
            return ResponseEntity.ok(alunosEncontrados);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro ao buscar por curso: " + e.getMessage());
        }
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editar(
            @PathVariable UUID id,
            @RequestBody @Valid AlunoDto dto) {
        try {
            AlunoModel alunoAtualizado = alunoService.atualizar(dto, id);
            return ResponseEntity.status(HttpStatus.CREATED).body(alunoAtualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao editar: " + e.getMessage());
        }
    }

    @DeleteMapping("/apagar/{id}")
    public ResponseEntity<?> apagar(@PathVariable(value = "id") UUID id) {
        try {
            alunoService.apagar(id);
            return ResponseEntity.ok("Aluno apagado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro ao apagar: " + e.getMessage());
        }
    }
}