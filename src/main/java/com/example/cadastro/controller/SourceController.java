package com.example.cadastro.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class SourceController {

    @GetMapping("/source")
    public Map<String, String> getSource() {
        return Map.of("github", "https://github.com/roddribeiro/cadastro-pessoas");
    }
}