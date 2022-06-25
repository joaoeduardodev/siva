package br.com.siva.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.siva.domains.Role;


public interface RoleRepositorio extends JpaRepository<Role, Long> {

}
