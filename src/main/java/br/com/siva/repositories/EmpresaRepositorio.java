package br.com.siva.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.siva.domains.Empresa;


public interface EmpresaRepositorio extends JpaRepository<Empresa, Long> {
	
	@Query(value = "SELECT COUNT(c.id) FROM empresas as c WHERE c.cidade_id = :id", nativeQuery=true)
	Long findByCidade(@Param("id") Long id);

}
