package br.ufg.inf.backend.spring.controller;

import br.ufg.inf.backend.spring.dto.ProdutoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufg.inf.backend.spring.model.Produto;
import br.ufg.inf.backend.spring.service.ProdutoService;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public List<Produto> listarProdutos() {
        return produtoService.listarProdutos();
    }

    @PostMapping()
    public ProdutoDTO adicionarProduto(@RequestBody ProdutoDTO produtoDTO) {
        produtoService.salvarProduto(produtoDTO);
        return produtoDTO;
    }

    @DeleteMapping("/{id}")
    public void deletarProduto(@PathVariable Long id) {
        produtoService.deletarProduto(id);
    }

    @GetMapping("/{id}")
    public Produto getProduto(@PathVariable Long id){
       return produtoService.obterProduto(id);
    }

    @PutMapping()
    public Produto editarProduto(@RequestBody ProdutoDTO produtoDTO) {
        return produtoService.salvarProduto(produtoDTO);
    }

}
