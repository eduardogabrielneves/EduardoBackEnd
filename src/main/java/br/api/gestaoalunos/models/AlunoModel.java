package br.api.gestaoalunos.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Entity
@Table(name = "tbAluno")
@Getter
@Setter
@ToString(of = {"id", "nome", "curso"})
@EqualsAndHashCode(of = "id")
public class AlunoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 3, message = "Nome deve ter pelo menos 3 caracteres")
    @Column(nullable = false)
    private String nome;

    @NotBlank(message = "Curso é obrigatório")
    @Size(min = 3, message = "Curso deve ter pelo menos 3 caracteres")
    @Column(nullable = false)
    private String curso;

    @Column
    private String telefone;
}