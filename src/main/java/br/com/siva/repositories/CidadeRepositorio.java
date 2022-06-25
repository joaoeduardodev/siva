package br.com.siva.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.siva.domains.Cidade;


public interface CidadeRepositorio extends JpaRepository<Cidade, Long> {

}
