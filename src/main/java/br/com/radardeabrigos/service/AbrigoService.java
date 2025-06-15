package br.com.radardeabrigos.service;

import br.com.radardeabrigos.enums.StatusAbrigo;
import br.com.radardeabrigos.model.*;
import br.com.radardeabrigos.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AbrigoService {

    @Autowired
    private AbrigoRepository abrigoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Abrigo> listarTodos() {
        return abrigoRepository.findAll();
    }

    public List<Abrigo> buscarPorCidade(String cidade) {
        return abrigoRepository.findAbrigosAtivosPorCidade(cidade);
    }

    public List<Abrigo> buscarPorBairro(String bairro) {
        return abrigoRepository.findByBairro(bairro);
    }

    public long ContarPorStatus(StatusAbrigo status) {
        return abrigoRepository.findByStatus(status).size();
    }

    public List<Abrigo> buscarComVagas() {
        return abrigoRepository.findAbrigosComVagas();
    }

    public List<Abrigo> buscarAcessiveis() {
        return abrigoRepository.findByAcessivel(true);
    }

    @Transactional
    public Abrigo cadastrarAbrigo(Abrigo abrigo) {
        return abrigoRepository.save(abrigo);
    }

    public Optional<Abrigo> buscarPorId(Long id) {
        return abrigoRepository.findById(id);
    }

    @Transactional
    public Abrigo atualizarAbrigo(Abrigo abrigo) {
        return abrigoRepository.save(abrigo);
    }

    @Transactional
    public void removerPessoaDoAbrigo(Long abrigoId, Long pessoaId) {
        Optional<Abrigo> abrigoOpt = abrigoRepository.findById(abrigoId);
        if (abrigoOpt.isPresent()) {
            Abrigo abrigo = abrigoOpt.get();
            abrigo.getPessoasAbrigadas().removeIf(p -> p.getId().equals(pessoaId));

            // Se abrigo estava lotado e agora tem vaga, atualiza status
            if (abrigo.getStatus() == StatusAbrigo.LOTADO && abrigo.temVagasDisponiveis()) {
                abrigo.setStatus(StatusAbrigo.ATIVO);
            }

            abrigoRepository.save(abrigo);
        }
    }
}
