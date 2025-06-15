package br.com.radardeabrigos.controller;

import br.com.radardeabrigos.model.Pessoa;
import br.com.radardeabrigos.enums.StatusPessoa;
import br.com.radardeabrigos.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//    === PESSOAS ===
//    GET    /api/pessoas                    - Lista todas as pessoas
//    GET    /api/pessoas/status/{status}    - Busca pessoas por status
//    GET    /api/pessoas/com-deficiencia    - Lista pessoas com deficiência
//    GET    /api/pessoas/{id}               - Busca pessoa por ID
//    POST   /api/pessoas                    - Cadastra nova pessoa
//    PUT    /api/pessoas/{id}               - Atualiza pessoa
//    PUT    /api/pessoas/{id}/saida         - Marca saída da pessoa do abrigo
//    GET    /api/pessoas/contador/{status}  - Conta pessoas por status

@RestController
@RequestMapping("/api/pessoas")
@CrossOrigin(origins = "*")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public List<Pessoa> listarTodas() {
        return pessoaService.listarTodas();
    }

    @GetMapping("/status/{status}")
    public List<Pessoa> buscarPorStatus(@PathVariable StatusPessoa status) {
        return pessoaService.buscarPorStatus(status);
    }

    @GetMapping("/com-deficiencia")
    public List<Pessoa> buscarPessoasComDeficiencia() {
        return pessoaService.buscarPessoasComDeficiencia();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> buscarPorId(@PathVariable Long id) {
        return pessoaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Pessoa cadastrarPessoa(@RequestBody Pessoa pessoa) {
        return pessoaService.cadastrarPessoa(pessoa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> atualizarPessoa(@PathVariable Long id, @RequestBody Pessoa pessoa) {
        if (!pessoaService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        pessoa.setId(id);
        return ResponseEntity.ok(pessoaService.atualizarPessoa(pessoa));
    }

    @PutMapping("/{id}/saida")
    public ResponseEntity<Void> marcarSaidaDoAbrigo(@PathVariable Long id) {
        pessoaService.marcarSaidaDoAbrigo(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/contador/{status}")
    public long contarPorStatus(@PathVariable StatusPessoa status) {
        return pessoaService.contarPorStatus(status);
    }
}
