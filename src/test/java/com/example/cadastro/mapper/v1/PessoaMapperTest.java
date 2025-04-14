
package com.example.cadastro.mapper.v1;

import com.example.cadastro.dto.v1.PessoaRequest;
import com.example.cadastro.model.Pessoa;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PessoaMapperTest {

    @Test
    void deveMapearPessoaRequestParaPessoa() {
        PessoaRequest request = new PessoaRequest();
        request.setNome("Maria");
        request.setCpf("12345678900");
        request.setEmail("maria@email.com");
        request.setDataNascimento(LocalDate.of(1990, 1, 1));
        request.setNaturalidade("SP");
        request.setNacionalidade("Brasileira");
        request.setSexo("F");

        Pessoa pessoa = PessoaMapper.toEntity(request);

        assertEquals("Maria", pessoa.getNome());
    }
}
