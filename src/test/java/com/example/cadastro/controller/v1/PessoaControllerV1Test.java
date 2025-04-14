
package com.example.cadastro.controller.v1;

import com.example.cadastro.config.SecurityConfig;
import com.example.cadastro.dto.v1.PessoaRequest;
import com.example.cadastro.dto.v1.PessoaResponse;
import com.example.cadastro.service.PessoaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PessoaControllerV1.class)
@Import(SecurityConfig.class)
class PessoaControllerV1Test {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PessoaService pessoaService;

    @Test
    void deveRetornarStatus200ParaListagem() throws Exception {
        PessoaResponse pessoa = new PessoaResponse();
        pessoa.setId(1L);
        pessoa.setNome("João");

        when(pessoaService.listar()).thenReturn(List.of(pessoa));

        mockMvc.perform(get("/api/v1/pessoas")
                        .with(httpBasic("admin", "senha123")))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void deveRetornarStatus200AoBuscarPorId() throws Exception {
        PessoaResponse pessoa = new PessoaResponse();
        pessoa.setId(1L);
        pessoa.setNome("João");

        when(pessoaService.buscarPorId(1L)).thenReturn(pessoa);

        mockMvc.perform(get("/api/v1/pessoas/1")
                        .with(httpBasic("admin", "senha123")))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

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

        PessoaResponse response = new PessoaResponse();
        response.setId(1L);
        response.setNome("João");

        when(pessoaService.criar((PessoaRequest) any())).thenReturn(response);

        mockMvc.perform(post("/api/v1/pessoas")
                        .with(httpBasic("admin", "senha123"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void deveRetornarStatus200AoAtualizarPessoa() throws Exception {
        PessoaRequest request = new PessoaRequest();
        request.setNome("João");
        request.setCpf("006.600.122-60");
        request.setEmail("joao@email.com");
        request.setDataNascimento(LocalDate.of(1990, 1, 1));
        request.setNaturalidade("SP");
        request.setNacionalidade("Brasileiro");
        request.setSexo("M");

        PessoaResponse response = new PessoaResponse();
        response.setId(1L);
        response.setNome("João");

        when(pessoaService.atualizar(any(), any())).thenReturn(response);

        mockMvc.perform(put("/api/v1/pessoas/1")
                        .with(httpBasic("admin", "senha123"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void deveRetornarStatus204AoDeletarPessoa() throws Exception {
        mockMvc.perform(delete("/api/v1/pessoas/1")
                        .with(httpBasic("admin", "senha123")))
                .andExpect(status().isNoContent());
    }
}
