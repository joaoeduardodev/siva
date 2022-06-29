package br.com.siva.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.siva.domains.Vaga;


public interface VagaRepositorio extends JpaRepository<Vaga, Long> {

	@Query(value = "SELECT * FROM vagas as v ORDER BY v.id DESC LIMIT 30", nativeQuery = true)
	List<Vaga> recentVagas();

	@Query(value = "SELECT * FROM vagas as v LEFT JOIN cidades as c ON v.cidade_id = c.id WHERE c.cep = :cep", nativeQuery=true)
	List<Vaga> findByCep(@Param("cep") String cep);
	
	@Query(value = "SELECT * FROM vagas as v where v.titulo like %:text%", nativeQuery=true)
	List<Vaga> findByText(@Param("text") String text);
	
	@Query(value = "SELECT COUNT(c.id) FROM vagas as c WHERE c.cidade_id = :id", nativeQuery=true)
	Long findByCidade(@Param("id") Long id);
	
	@Query(value = "SELECT COUNT(c.id) FROM empresas as c WHERE c.cidade_id = :id", nativeQuery=true)
	Long findByEmpresas(@Param("id") Long id);
}
