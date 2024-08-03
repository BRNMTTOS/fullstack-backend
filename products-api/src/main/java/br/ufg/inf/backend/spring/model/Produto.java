package br.ufg.inf.backend.spring.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private double preco;

	@ManyToOne
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;

	@ManyToMany
	@JoinTable(
			name = "produto_tag", 
			joinColumns = @JoinColumn(name = "produto_id"), 
			inverseJoinColumns = @JoinColumn(name = "tag_id")
	)
	private List<Tag> tags;

	public Produto(Long id, String nome, double preco) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
	}

	public Produto() {
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

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	
}
