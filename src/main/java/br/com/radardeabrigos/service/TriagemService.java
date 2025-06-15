package br.com.radardeabrigos.service;

import br.com.radardeabrigos.enums.StatusAbrigo;
import br.com.radardeabrigos.enums.StatusPessoa;
import br.com.radardeabrigos.model.*;
import br.com.radardeabrigos.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TriagemService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private AbrigoRepository abrigoRepository;

    @Autowired
    private NotificacaoService notificacaoService;

    @Transactional
    public boolean alocarPessoaEmAbrigo(Long pessoaId, Long abrigoId) {
        Optional<Pessoa> pessoaOpt = pessoaRepository.findById(pessoaId);
        Optional<Abrigo> abrigoOpt = abrigoRepository.findById(abrigoId);

        if (pessoaOpt.isEmpty() || abrigoOpt.isEmpty()) {
            return false;
        }

        Pessoa pessoa = pessoaOpt.get();
        Abrigo abrigo = abrigoOpt.get();

        // Verifica se há vagas
        if (!abrigo.temVagasDisponiveis()) {
            return false;
        }

        // Verifica se pessoa com deficiência pode ser alocada
        if (pessoa.isPossuiDeficiencia() && !abrigo.isAcessivel()) {
            return false;
        }

        // Realiza a alocação
        pessoa.setAbrigoAtual(abrigo);
        pessoa.setStatus(StatusPessoa.ABRIGADA);
        pessoaRepository.save(pessoa);

        // Atualiza status do abrigo se lotou
        if (!abrigo.temVagasDisponiveis()) {
            abrigo.setStatus(StatusAbrigo.LOTADO);
            abrigoRepository.save(abrigo);
            notificacaoService.notificarAbrigoLotado(abrigo);
        }

        return true;
    }

    public List<Abrigo> buscarAbrigosApropriados(Pessoa pessoa) {
        if (pessoa.isPossuiDeficiencia()) {
            return abrigoRepository.findAbrigosAcessiveisComVagas();
        } else {
            return abrigoRepository.findAbrigosComVagas();
        }
    }

    public List<Pessoa> obterFilaPrioridade() {
        return pessoaRepository.findPessoasAguardandoPorPrioridade();
    }

    @Transactional
    public void processarTriagemAutomatica() {
        List<Pessoa> filaPrioridade = obterFilaPrioridade();

        for (Pessoa pessoa : filaPrioridade) {
            List<Abrigo> abrigosDisponiveis = buscarAbrigosApropriados(pessoa);

            if (!abrigosDisponiveis.isEmpty()) {
                Abrigo abrigoEscolhido = abrigosDisponiveis.get(0); // Primeiro disponível
                alocarPessoaEmAbrigo(pessoa.getId(), abrigoEscolhido.getId());
            }
        }
    }
}
