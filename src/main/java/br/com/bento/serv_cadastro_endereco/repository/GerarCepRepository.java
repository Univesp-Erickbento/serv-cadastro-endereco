package br.com.bento.serv_cadastro_endereco.repository;

import br.com.bento.serv_cadastro_endereco.domain.model.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GerarCepRepository extends JpaRepository<Endereco,Long> {

}
