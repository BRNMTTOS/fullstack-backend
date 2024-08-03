package br.ufg.inf.backend.spring.service;

import java.util.List;
import java.util.Objects;

import br.ufg.inf.backend.spring.dto.ProdutoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufg.inf.backend.spring.model.Produto;
import br.ufg.inf.backend.spring.repository.ProdutoRepository;

@Service
public class ProdutoService {

   @Autowired
   private ProdutoRepository produtoRepository;

   public List<Produto> listarProdutos() {
       return produtoRepository.findAll();
   }

   public Produto salvarProduto(ProdutoDTO produtoDTO) {
       Produto produto = new Produto();
       if(Objects.nonNull(produtoDTO.getId())){
           produto.setId(produtoDTO.getId());
       }
       produto.setNome(produtoDTO.getNome());
       produto.setPreco(produtoDTO.getPreco());
       return produtoRepository.save(produto);
   }

   public Produto obterProduto(Long id) {
       return produtoRepository.findById(id).orElse(null);
   }

   public void deletarProduto(Long id){
       produtoRepository.deleteById(id);
   }
}
