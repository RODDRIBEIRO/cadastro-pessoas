
package com.example.cadastro.service;

import com.example.cadastro.dto.v1.PessoaRequest;
import com.example.cadastro.dto.v1.PessoaResponse;
import com.example.cadastro.model.Pessoa;
import com.example.cadastro.repository.PessoaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class PessoaServiceTest {

    @Mock
    private PessoaRepository pessoaRepository;

    @InjectMocks
    private PessoaService pessoaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveSalvarPessoaComRequest() {
        PessoaRequest request = new PessoaRequest();
        request.setNome("João");
        request.setCpf("006.600.122-60");
        request.setEmail("joao@email.com");
        request.setDataNascimento(LocalDate.of(1990, 1, 1));
        request.setNaturalidade("SP");
        request.setNacionalidade("Brasileiro");
        request.setSexo("M");

        when(pessoaRepository.existsByCpf(any())).thenReturn(false);
        when(pessoaRepository.save(any())).thenAnswer(i -> i.getArguments()[0]);

        PessoaResponse resultado = pessoaService.criar(request);

        assertNotNull(resultado);
        assertEquals("João", resultado.getNome());
    }

    @Test
    void deveBuscarPessoaPorId() {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(1L);
        pessoa.setNome("Maria");

        when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));

        PessoaResponse response = pessoaService.buscarPorId(1L);

        assertNotNull(response);
        assertEquals("Maria", response.getNome());
    }

    @Test
    void deveLancarExcecaoQuandoPessoaNaoEncontradaPorId() {
        when(pessoaRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> pessoaService.buscarPorId(99L));
    }

    @Test
    void deveListarPessoas() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Ana");

        when(pessoaRepository.findAll()).thenReturn(List.of(pessoa));

        List<PessoaResponse> lista = pessoaService.listar();

        assertFalse(lista.isEmpty());
        assertEquals("Ana", lista.get(0).getNome());
    }

    @Test
    void deveAtualizarPessoa() {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(1L);
        pessoa.setCpf("006.600.122-60");

        PessoaRequest request = new PessoaRequest();
        request.setNome("Atualizado");
        request.setCpf("006.600.122-60");
        request.setEmail("atualizado@email.com");
        request.setDataNascimento(LocalDate.of(1990, 1, 1));
        request.setNaturalidade("SP");
        request.setNacionalidade("Brasileiro");
        request.setSexo("M");

        when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));
        when(pessoaRepository.save(any())).thenReturn(pessoa);

        PessoaResponse response = pessoaService.atualizar(1L, request);

        assertNotNull(response);
    }

    @Test
    void deveDeletarPessoa() {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(1L);

        when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));

        pessoaService.deletar(1L);

        verify(pessoaRepository, times(1)).delete(pessoa);
    }
}
