package com.example.cadastro.mapper.v1;

import com.example.cadastro.dto.v1.PessoaRequest;
import com.example.cadastro.dto.v1.PessoaResponse;
import com.example.cadastro.model.Pessoa;

public class PessoaMapper {

    public static Pessoa toEntity(PessoaRequest request) {
        return Pessoa.builder()
                .nome(request.getNome())
                .sexo(request.getSexo())
                .email(request.getEmail())
                .dataNascimento(request.getDataNascimento())
                .naturalidade(request.getNaturalidade())
                .nacionalidade(request.getNacionalidade())
                .cpf(request.getCpf())
                .build();
    }

    public static PessoaResponse toResponse(Pessoa pessoa) {
        return PessoaResponse.builder()
                .id(pessoa.getId())
                .nome(pessoa.getNome())
                .sexo(pessoa.getSexo())
                .email(pessoa.getEmail())
                .dataNascimento(pessoa.getDataNascimento())
                .naturalidade(pessoa.getNaturalidade())
                .nacionalidade(pessoa.getNacionalidade())
                .cpf(pessoa.getCpf())
                .dataCadastro(pessoa.getDataCadastro())
                .dataAtualizacao(pessoa.getDataAtualizacao())
                .build();
    }
}