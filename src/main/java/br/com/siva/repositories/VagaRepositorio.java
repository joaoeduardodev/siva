package br.com.siva.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.siva.domains.Vaga;


public interface VagaRepositorio extends JpaRepository<Vaga, Long> {

}
