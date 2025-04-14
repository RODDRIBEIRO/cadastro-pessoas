package com.example.cadastro.controller.v2;

import com.example.cadastro.dto.v1.PessoaResponse;
import com.example.cadastro.dto.v2.EnderecoRequest;
import com.example.cadastro.dto.v2.PessoaRequest;
import com.example.cadastro.service.PessoaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PessoaControllerV2.class)
@Import(com.example.cadastro.config.SecurityConfig.class)
class PessoaControllerV2Test {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PessoaService pessoaService;

    @Test
    void deveRetornarStatus200AoCriarPessoa() throws Exception {
        PessoaRequest request = new PessoaRequest();
        request.setNome("João");
        request.setCpf("006.600.122-60");
        request.setEmail("joao@email.com");
        request.setDataNascimento(LocalDate.of(1990, 1, 1));
        request.setNaturalidade("SP");
        request.setNacionalidade("Brasileiro");
        request.setSexo("M");

        EnderecoRequest endereco = new EnderecoRequest();
        endereco.setCidade("São Paulo");
        endereco.setEstado("SP");
        request.setEndereco(endereco); // ✅ necessário para passar pela validação

        PessoaResponse response = new PessoaResponse();
        response.setId(1L);
        response.setNome("João");

        when(pessoaService.criar((com.example.cadastro.model.Pessoa) any())).thenReturn(response);

        mockMvc.perform(post("/api/v2/pessoas")
                        .with(httpBasic("admin", "senha123"))
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON));
    }
}
