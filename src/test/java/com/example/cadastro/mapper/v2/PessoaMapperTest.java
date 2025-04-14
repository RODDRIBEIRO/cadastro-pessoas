
package com.example.cadastro.mapper.v2;

import com.example.cadastro.dto.v2.EnderecoRequest;
import com.example.cadastro.dto.v2.PessoaRequest;
import com.example.cadastro.model.Pessoa;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PessoaMapperTest {

    @Test
    void deveMapearPessoaRequestParaPessoa() {
        PessoaRequest request = new PessoaRequest();
        request.setNome("Pedro");
        request.setCpf("98765432100");
        request.setEmail("pedro@email.com");
        request.setDataNascimento(LocalDate.of(1985, 5, 5));
        request.setNaturalidade("RJ");
        request.setNacionalidade("Brasileira");
        request.setSexo("M");
        EnderecoRequest enderecoRequest = new EnderecoRequest();
        enderecoRequest.setCidade("São Paulo");
        enderecoRequest.setEstado("SP");
        request.setEndereco(enderecoRequest);

        Pessoa pessoa = PessoaMapper.toEntity(request);

        assertEquals("Pedro", pessoa.getNome());
    }
}
