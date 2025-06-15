package br.com.radardeabrigos.service;

import br.com.radardeabrigos.enums.StatusPessoa;
import br.com.radardeabrigos.model.*;
import br.com.radardeabrigos.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public List<Pessoa> listarTodas() {
        return pessoaRepository.findAll();
    }

    public List<Pessoa> buscarPorStatus(StatusPessoa status) {
        return pessoaRepository.findByStatus(status);
    }

    public List<Pessoa> buscarPessoasComDeficiencia() {
        return pessoaRepository.findByPossuiDeficiencia(true);
    }

    @Transactional
    public Pessoa cadastrarPessoa(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public Optional<Pessoa> buscarPorId(Long id) {
        return pessoaRepository.findById(id);
    }

    public List<Pessoa> buscarPorAbrigo(Abrigo abrigo) {
        return pessoaRepository.findByAbrigoAtual(abrigo);
    }

    @Transactional
    public Pessoa atualizarPessoa(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    @Transactional
    public void marcarSaidaDoAbrigo(Long pessoaId) {
        Optional<Pessoa> pessoaOpt = pessoaRepository.findById(pessoaId);
        if (pessoaOpt.isPresent()) {
            Pessoa pessoa = pessoaOpt.get();
            pessoa.setStatus(StatusPessoa.SAIU_DO_ABRIGO);
            pessoa.setAbrigoAtual(null);
            pessoaRepository.save(pessoa);
        }
    }

    public long contarPorStatus(StatusPessoa status) {
        return pessoaRepository.countByStatus(status);
    }

    public long contarPessoasCadastradas() {
        return pessoaRepository.count();
    }
}
