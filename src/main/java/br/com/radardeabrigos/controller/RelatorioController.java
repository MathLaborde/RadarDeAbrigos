package br.com.radardeabrigos.controller;

import br.com.radardeabrigos.service.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

//    === RELATÓRIOS ===
//    GET    /api/relatorios/geral           - Gera relatório geral do sistema

@RestController
@RequestMapping("/api/relatorios")
@CrossOrigin(origins = "*")
public class RelatorioController {

    @Autowired
    private RelatorioService relatorioService;

    @GetMapping("/geral")
    public Map<String, Object> gerarRelatorioGeral() {
        return relatorioService.gerarRelatorioGeral();
    }
}

