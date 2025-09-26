package br.api.gestaoalunos.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AlunoDto {

    @NotBlank(message = "Nome obrigatório")
    @Size(min = 3, message = "Deve ter pelo menos 3 caracteres")
    private String nome;

    @NotBlank(message = "Curso obrigatório")
    @Size(min = 3, message = "Deve ter pelo menos 3 caracteres")
    private String curso;

    private String telefone;
}