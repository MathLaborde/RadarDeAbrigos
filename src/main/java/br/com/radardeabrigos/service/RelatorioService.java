package br.com.radardeabrigos.service;

import br.com.radardeabrigos.enums.StatusAbrigo;
import br.com.radardeabrigos.enums.StatusPessoa;
import br.com.radardeabrigos.model.*;
import br.com.radardeabrigos.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class RelatorioService {

    @Autowired
    private AbrigoRepository abrigoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Map<String, Object> gerarRelatorioGeral() {
        Map<String, Object> relatorio = new HashMap<>();

        // Estatísticas de abrigos
        long totalAbrigos = abrigoRepository.count();
        long abrigosAtivos = abrigoRepository.findByStatus(StatusAbrigo.ATIVO).size();
        long abrigosLotados = abrigoRepository.findByStatus(StatusAbrigo.LOTADO).size();
        long abrigosAcessiveis = abrigoRepository.findAbrigosAcessiveisComVagas().size();

        // Estatísticas de pessoas
        long totalPessoas = pessoaRepository.count();
        long pessoasAbrigadas = pessoaRepository.countByStatus(StatusPessoa.ABRIGADA);
        long pessoasAguardando = pessoaRepository.countByStatus(StatusPessoa.AGUARDANDO_ABRIGO);
        long pessoasComDeficiencia = pessoaRepository.findByPossuiDeficiencia(true).size();

        // Capacidade total
        long capacidadeTotal = abrigoRepository.findAll().stream()
                .mapToInt(Abrigo::getCapacidadeTotal)
                .sum();

        long vagasDisponiveis = capacidadeTotal - pessoasAbrigadas;

        relatorio.put("totalAbrigos", totalAbrigos);
        relatorio.put("abrigosAtivos", abrigosAtivos);
        relatorio.put("abrigosLotados", abrigosLotados);
        relatorio.put("abrigosAcessiveis", abrigosAcessiveis);

        relatorio.put("totalPessoas", totalPessoas);
        relatorio.put("pessoasAbrigadas", pessoasAbrigadas);
        relatorio.put("pessoasAguardando", pessoasAguardando);
        relatorio.put("pessoasComDeficiencia", pessoasComDeficiencia);

        relatorio.put("capacidadeTotal", capacidadeTotal);
        relatorio.put("vagasOcupadas", pessoasAbrigadas);
        relatorio.put("vagasDisponiveis", vagasDisponiveis);
        relatorio.put("taxaOcupacao", capacidadeTotal > 0 ? (double) pessoasAbrigadas / capacidadeTotal * 100 : 0);

        return relatorio;
    }
}
