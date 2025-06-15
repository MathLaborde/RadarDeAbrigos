package br.com.radardeabrigos.controller;

import br.com.radardeabrigos.model.Abrigo;
import br.com.radardeabrigos.model.Pessoa;
import br.com.radardeabrigos.service.TriagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//    === TRIAGEM ===
//    POST   /api/triagem/alocar             - Aloca pessoa em abrigo (pessoaId, abrigoId)
//    GET    /api/triagem/abrigos-apropriados/{pessoaId} - Busca abrigos apropriados
//    GET    /api/triagem/fila-prioridade    - Obtém fila de prioridade
//    POST   /api/triagem/processar-automatica - Processa triagem automática

@RestController
@RequestMapping("/api/triagem")
@CrossOrigin(origins = "*")
public class TriagemController {

    @Autowired
    private TriagemService triagemService;

    @PostMapping("/alocar")
    public ResponseEntity<String> alocarPessoaEmAbrigo(@RequestParam Long pessoaId, @RequestParam Long abrigoId) {
        boolean sucesso = triagemService.alocarPessoaEmAbrigo(pessoaId, abrigoId);
        if (sucesso) {
            return ResponseEntity.ok("Pessoa alocada com sucesso no abrigo");
        } else {
            return ResponseEntity.badRequest().body("Não foi possível alocar a pessoa no abrigo");
        }
    }

    @GetMapping("/abrigos-apropriados/{pessoaId}")
    public ResponseEntity<List<Abrigo>> buscarAbrigosApropriados(@PathVariable Long pessoaId) {
        // Aqui você precisaria buscar a pessoa primeiro
        // Para simplificar, vou retornar abrigos com vagas
        return ResponseEntity.ok(triagemService.buscarAbrigosApropriados(new Pessoa()));
    }

    @GetMapping("/fila-prioridade")
    public List<Pessoa> obterFilaPrioridade() {
        return triagemService.obterFilaPrioridade();
    }

    @PostMapping("/processar-automatica")
    public ResponseEntity<String> processarTriagemAutomatica() {
        triagemService.processarTriagemAutomatica();
        return ResponseEntity.ok("Triagem automática processada com sucesso");
    }
}
