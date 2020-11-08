package br.upe.piii.mvc.verdinhas.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Verdinha {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String nome;

	@Lob
	private byte[] foto;

	@ManyToOne
	public Local local;


	public Verdinha() {

	}

	public Verdinha(String nome, Local local, byte[] foto) {
		this();
		this.nome = nome;
		this.local = local;
		this.foto = foto;
	}
	
	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
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

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	
	@Override
	public boolean equals(Object objeto) {
		boolean iguais = this == objeto;
		
		if (!iguais) {
			if (objeto == null || getClass() != objeto.getClass()) {
				iguais= false;
			} else {
				Verdinha verdinha = (Verdinha) objeto;
				iguais = Objects.equals(verdinha.getNome(), this.nome);
			}
		}
		
		return iguais;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(nome);
	}

}
