package br.com.siva.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.siva.domains.Estado;


public interface EstadoRepositorio extends JpaRepository<Estado, Long> {

}
