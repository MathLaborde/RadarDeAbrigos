package br.com.radardeabrigos.repository;

import br.com.radardeabrigos.model.Abrigo;
import br.com.radardeabrigos.enums.StatusAbrigo;
import br.com.radardeabrigos.enums.TipoAbrigo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AbrigoRepository extends JpaRepository<Abrigo, Long> {
    List<Abrigo> findByCidade(String cidade);
    List<Abrigo> findByBairro(String bairro);
    List<Abrigo> findByStatus(StatusAbrigo status);
    List<Abrigo> findByTipo(TipoAbrigo tipo);
    List<Abrigo> findByAcessivel(boolean acessivel);

    @Query("SELECT a FROM Abrigo a WHERE a.cidade = :cidade AND a.status = 'ATIVO'")
    List<Abrigo> findAbrigosAtivosPorCidade(@Param("cidade") String cidade);

    @Query("SELECT a FROM Abrigo a WHERE a.capacidadeTotal > SIZE(a.pessoasAbrigadas) AND a.status = 'ATIVO'")
    List<Abrigo> findAbrigosComVagas();

    @Query("SELECT a FROM Abrigo a WHERE a.acessivel = true AND a.capacidadeTotal > SIZE(a.pessoasAbrigadas) AND a.status = 'ATIVO'")
    List<Abrigo> findAbrigosAcessiveisComVagas();
}

