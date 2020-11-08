package br.upe.piii.mvc.verdinhas.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Local {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;
	public String nome;
	public String descricao;

	@OneToMany
	public List<Verdinha> verdinhas;

	public Local() {
		
	}
	
	public Local(String nome, String descricao) {
		this();
		this.nome = nome;
		this.descricao = descricao;
	}
	
	public List<Verdinha> getVerdinhas() {
		return verdinhas;
	}

	public void setVerdinhas(List<Verdinha> verdinhas) {
		this.verdinhas = verdinhas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public boolean equals(Object objeto) {
		boolean iguais = this == objeto;
		
		if (!iguais) {
			if (objeto == null || getClass() != objeto.getClass()) {
				iguais= false;
			} else {
				Local local = (Local) objeto;
				iguais = Objects.equals(local.getNome(), this.nome);
			}
		}
		
		return iguais;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(nome);
	}
}
