package com.example.classificador_texto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import java.util.*;

@SpringBootApplication
public class ClassificadorTextoApplication {
	public static void main(String[] args) {
		SpringApplication.run(ClassificadorTextoApplication.class, args);
	}
}

// --- CONTROLLER (Recebe as requisições da API) ---
@RestController
@RequestMapping("/api/classificar")
class ClassificadorController {
    
    private final ClassificadorService service;

    public ClassificadorController(ClassificadorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> classificar(@RequestBody Map<String, String> body) {
        String textoOriginal = body.get("conteudo");
        String resultado = service.analisar(textoOriginal);
        
        Map<String, String> resposta = new HashMap<>();
        resposta.put("texto_analisado", textoOriginal);
        resposta.put("veredito", resultado);
        
        return ResponseEntity.ok(resposta);
    }
}

// --- SERVICE (Lógica de detecção de Spam/Ofensivo) ---
@Service
class ClassificadorService {
    // Lista de palavras que bloqueiam o texto
    private final List<String> blacklist = Arrays.asList(
        "spam", "ganhe dinheiro", "clique aqui", "ofensivo", "compre agora", "vencendo"
    );

    public String analisar(String texto) {
        if (texto == null || texto.isBlank()) {
            return "TEXTO VAZIO";
        }

        String textoParaAnalise = texto.toLowerCase();

        for (String termo : blacklist) {
            if (textoParaAnalise.contains(termo)) {
                return "BLOQUEADO: CONTEÚDO IMPRÓPRIO OU SPAM";
            }
        }

        return "APROVADO: TEXTO SEGURO";
    }
}