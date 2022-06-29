package br.com.siva.domains.dto;

import br.com.siva.domains.Cidade;
import br.com.siva.domains.Empresa;

public class VagaDTO {

	public VagaDTO(String titulo, String descricao, Cidade cidade, Empresa empresa) {
		this.titulo = titulo;
		this.descricao = descricao;
		this.cidade = cidade;
		this.empresa = empresa;
	}

	private String titulo;

	private String descricao;

	private Cidade cidade;
	
	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	private Empresa empresa;
	

}

	
	
