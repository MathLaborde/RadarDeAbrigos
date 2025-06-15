package br.com.radardeabrigos.repository;

import br.com.radardeabrigos.model.Usuario;
import br.com.radardeabrigos.enums.TipoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
    List<Usuario> findByTipo(TipoUsuario tipo);
    List<Usuario> findByAtivo(boolean ativo);
}
