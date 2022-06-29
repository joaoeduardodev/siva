package br.com.siva.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.siva.domains.Cidade;


public interface CidadeRepositorio extends JpaRepository<Cidade, Long> {
	
	@Query(value = "SELECT COUNT(c.id) FROM cidades as c WHERE c.estado_id = :id", nativeQuery=true)
	Long findByEstado(@Param("id") Long id);
	
}
