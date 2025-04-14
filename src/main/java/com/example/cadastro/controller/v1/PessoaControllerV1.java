package com.example.cadastro.controller.v1;

import com.example.cadastro.dto.v1.PessoaRequest;
import com.example.cadastro.dto.v1.PessoaResponse;
import com.example.cadastro.service.PessoaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pessoas")
@RequiredArgsConstructor
public class PessoaControllerV1 {

    private final PessoaService pessoaService;

    @PostMapping
    public ResponseEntity<PessoaResponse> criar(@RequestBody @Valid PessoaRequest request) {
        return ResponseEntity.ok(pessoaService.criar(request));
    }

    @GetMapping
    public ResponseEntity<List<PessoaResponse>> listar() {
        return ResponseEntity.ok(pessoaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaResponse> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pessoaService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaResponse> atualizar(@PathVariable Long id, @RequestBody @Valid PessoaRequest request) {
        return ResponseEntity.ok(pessoaService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        pessoaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}