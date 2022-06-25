package br.com.siva.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.siva.domains.Empresa;


public interface EmpresaRepositorio extends JpaRepository<Empresa, Long> {

}
