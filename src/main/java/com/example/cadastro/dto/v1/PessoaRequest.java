package com.example.cadastro.dto.v1;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PessoaRequest {
    @NotBlank
    private String nome;
    private String sexo;

    @Email
    private String email;

    @NotNull
    private LocalDate dataNascimento;

    private String naturalidade;
    private String nacionalidade;

    @NotBlank
    private String cpf;
}