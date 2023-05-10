package br.com.gerenciamentodesalastcc.repositorio;

import br.com.gerenciamentodesalastcc.modelo.Usuario;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;

@EnableJpaRepositories
public interface UsuarioRepositorio extends CrudRepository<Usuario, Long> {

    Usuario findByUser(String user);

}
