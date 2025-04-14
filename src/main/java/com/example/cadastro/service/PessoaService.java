package com.example.cadastro.service;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;
import com.example.cadastro.dto.v1.PessoaRequest;
import com.example.cadastro.dto.v1.PessoaResponse;
import com.example.cadastro.mapper.v1.PessoaMapper;
import com.example.cadastro.model.Pessoa;
import com.example.cadastro.repository.PessoaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository repository;

    @Transactional
    public PessoaResponse criar(PessoaRequest request) {
        String cpfNumerico = request.getCpf().replaceAll("[^\\d]", "");
        validarCpf(cpfNumerico);

        if (repository.existsByCpf(request.getCpf())) {
            throw new IllegalArgumentException("CPF já cadastrado.");
        }

        Pessoa pessoa = PessoaMapper.toEntity(request);
        pessoa.setCpf(cpfNumerico);

        return PessoaMapper.toResponse(repository.save(pessoa));
    }

    public PessoaResponse criar(Pessoa pessoa) {
        String cpfNumerico = pessoa.getCpf().replaceAll("[^\\d]", "");
        validarCpf(cpfNumerico);

        if (repository.existsByCpf(pessoa.getCpf())) {
            throw new IllegalArgumentException("CPF já cadastrado.");
        }
        pessoa.setCpf(cpfNumerico);

        return PessoaMapper.toResponse(repository.save(pessoa));
    }

    public List<PessoaResponse> listar() {
        return repository.findAll().stream()
                .map(PessoaMapper::toResponse)
                .collect(Collectors.toList());
    }

    public PessoaResponse buscarPorId(Long id) {
        Pessoa pessoa = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pessoa com ID " + id + " não encontrada"));
        return PessoaMapper.toResponse(pessoa);
    }

    @Transactional
    public PessoaResponse atualizar(Long id, PessoaRequest request) {
        Pessoa pessoa = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pessoa com ID " + id + " não encontrada"));

        if (!pessoa.getCpf().equals(request.getCpf()) && repository.existsByCpf(request.getCpf())) {
            throw new IllegalArgumentException("CPF já cadastrado.");
        }

        pessoa.setNome(request.getNome());
        pessoa.setSexo(request.getSexo());
        pessoa.setEmail(request.getEmail());
        pessoa.setDataNascimento(request.getDataNascimento());
        pessoa.setNaturalidade(request.getNaturalidade());
        pessoa.setNacionalidade(request.getNacionalidade());
        pessoa.setCpf(request.getCpf());

        return PessoaMapper.toResponse(repository.save(pessoa));
    }

    @Transactional
    public void deletar(Long id) {
        Pessoa pessoa = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrada"));
        repository.delete(pessoa);
    }

    private void validarCpf(String cpf) {
        CPFValidator validator = new CPFValidator();
        try {
            validator.assertValid(cpf);
        } catch (InvalidStateException e) {
            throw new IllegalArgumentException("CPF inválido.");
        }
    }
}