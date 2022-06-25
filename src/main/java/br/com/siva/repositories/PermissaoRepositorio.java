package br.com.siva.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.siva.domains.Permissao;


public interface PermissaoRepositorio extends JpaRepository<Permissao, Long> {

}

