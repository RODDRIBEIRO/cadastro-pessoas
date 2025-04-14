package com.example.cadastro.mapper.v2;

import com.example.cadastro.dto.v2.EnderecoRequest;
import com.example.cadastro.dto.v2.PessoaRequest;
import com.example.cadastro.model.Endereco;
import com.example.cadastro.model.Pessoa;

public class PessoaMapper {

    public static Pessoa toEntity(PessoaRequest request) {
        EnderecoRequest er = request.getEndereco();
        Endereco endereco = Endereco.builder()
                .rua(er.getRua())
                .cidade(er.getCidade())
                .estado(er.getEstado())
                .cep(er.getCep())
                .build();

        return Pessoa.builder()
                .nome(request.getNome())
                .sexo(request.getSexo())
                .email(request.getEmail())
                .dataNascimento(request.getDataNascimento())
                .naturalidade(request.getNaturalidade())
                .nacionalidade(request.getNacionalidade())
                .cpf(request.getCpf())
                .endereco(endereco)
                .build();
    }
}