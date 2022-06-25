package br.com.siva.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.siva.domains.Usuario;


public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

}