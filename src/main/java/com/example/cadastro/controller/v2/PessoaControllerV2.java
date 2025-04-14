package com.example.cadastro.controller.v2;

import com.example.cadastro.dto.v1.PessoaResponse;
import com.example.cadastro.dto.v2.PessoaRequest;
import com.example.cadastro.mapper.v2.PessoaMapper;
import com.example.cadastro.service.PessoaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/pessoas")
@RequiredArgsConstructor
public class PessoaControllerV2 {

    private final PessoaService pessoaService;

    @PostMapping
    public ResponseEntity<PessoaResponse> criar(@RequestBody @Valid PessoaRequest request) {
        return ResponseEntity.ok(pessoaService.criar(PessoaMapper.toEntity(request)));
    }
}