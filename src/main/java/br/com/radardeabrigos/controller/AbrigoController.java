package br.com.radardeabrigos.controller;

import br.com.radardeabrigos.model.Abrigo;
import br.com.radardeabrigos.service.AbrigoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//    === ABRIGOS ===
//    GET    /api/abrigos                    - Lista todos os abrigos
//    GET    /api/abrigos/cidade/{cidade}    - Busca abrigos por cidade
//    GET    /api/abrigos/bairro/{bairro}    - Busca abrigos por bairro
//    GET    /api/abrigos/com-vagas          - Lista abrigos com vagas disponíveis
//    GET    /api/abrigos/acessiveis         - Lista abrigos acessíveis
//    GET    /api/abrigos/{id}               - Busca abrigo por ID
//    POST   /api/abrigos                    - Cadastra novo abrigo
//    PUT    /api/abrigos/{id}               - Atualiza abrigo
//    DELETE /api/abrigos/{abrigoId}/pessoas/{pessoaId} - Remove pessoa do abrigo

@RestController
@RequestMapping("/api/abrigos")
@CrossOrigin(origins = "*")
public class AbrigoController {

    @Autowired
    private AbrigoService abrigoService;

    @GetMapping
    public List<Abrigo> listarTodos() {
        return abrigoService.listarTodos();
    }

    @GetMapping("/cidade/{cidade}")
    public List<Abrigo> buscarPorCidade(@PathVariable String cidade) {
        return abrigoService.buscarPorCidade(cidade);
    }

    @GetMapping("/bairro/{bairro}")
    public List<Abrigo> buscarPorBairro(@PathVariable String bairro) {
        return abrigoService.buscarPorBairro(bairro);
    }

    @GetMapping("/com-vagas")
    public List<Abrigo> buscarComVagas() {
        return abrigoService.buscarComVagas();
    }

    @GetMapping("/acessiveis")
    public List<Abrigo> buscarAcessiveis() {
        return abrigoService.buscarAcessiveis();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Abrigo> buscarPorId(@PathVariable Long id) {
        return abrigoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Abrigo cadastrarAbrigo(@RequestBody Abrigo abrigo) {
        return abrigoService.cadastrarAbrigo(abrigo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Abrigo> atualizarAbrigo(@PathVariable Long id, @RequestBody Abrigo abrigo) {
        if (!abrigoService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        abrigo.setId(id);
        return ResponseEntity.ok(abrigoService.atualizarAbrigo(abrigo));
    }

    @DeleteMapping("/{abrigoId}/pessoas/{pessoaId}")
    public ResponseEntity<Void> removerPessoaDoAbrigo(@PathVariable Long abrigoId, @PathVariable Long pessoaId) {
        abrigoService.removerPessoaDoAbrigo(abrigoId, pessoaId);
        return ResponseEntity.ok().build();
    }
}
