package br.com.radardeabrigos.repository;

import br.com.radardeabrigos.model.Abrigo;
import br.com.radardeabrigos.model.Pessoa;
import br.com.radardeabrigos.enums.StatusPessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    List<Pessoa> findByStatus(StatusPessoa status);
    List<Pessoa> findByPossuiDeficiencia(boolean possuiDeficiencia);

    @Query("SELECT p FROM Pessoa p WHERE p.status = 'AGUARDANDO_ABRIGO' ORDER BY p.possuiDeficiencia DESC, p.idade DESC")
    List<Pessoa> findPessoasAguardandoPorPrioridade();

    long countByStatus(StatusPessoa status);

    List<Pessoa> findByAbrigoAtual(Abrigo abrigoAtual);
}
