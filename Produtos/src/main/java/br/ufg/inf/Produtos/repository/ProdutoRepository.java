package br.ufg.inf.Produtos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufg.inf.Produtos.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}