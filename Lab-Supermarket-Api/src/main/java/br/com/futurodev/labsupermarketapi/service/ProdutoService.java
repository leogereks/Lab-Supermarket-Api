package br.com.futurodev.labsupermarketapi.service;

import br.com.futurodev.labsupermarketapi.model.Produto;
import br.com.futurodev.labsupermarketapi.repository.ProdutoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepo produtoRepo;

    public List<Produto> ListarProdutos() {
        return produtoRepo.findAll();
    }
    @Transactional
    public Produto salva(Produto produto) {
        return produtoRepo.save(produto);
    }
    @Transactional
    public void deleta(Long idProduto) {
        produtoRepo.deleteById(idProduto);
    }

    public Produto busca(Long idProduto) {
        return produtoRepo.findById(idProduto).get();
    }

    public List<Produto> buscaNome(String nome) {
        return produtoRepo.buscaNome(nome);
    }

    public Double totalCompras() {
        return produtoRepo.totalComprado();
    }
}
