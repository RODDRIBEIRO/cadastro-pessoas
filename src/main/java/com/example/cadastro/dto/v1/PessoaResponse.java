package com.example.cadastro.dto.v1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PessoaResponse {
    private Long id;
    private String nome;
    private String sexo;
    private String email;
    private LocalDate dataNascimento;
    private String naturalidade;
    private String nacionalidade;
    private String cpf;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataAtualizacao;
}