package br.com.futurodev.labsupermarketapi.repository;

import br.com.futurodev.labsupermarketapi.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface ProdutoRepo extends JpaRepository<Produto, Long> {

    @Query(value = "select p from Produto p where p.nome like %?1%")
    ArrayList<Produto> buscaNome (String nome);

    @Query(value = "select SUM(valor) from Produto p where p.status = true")
    Double totalComprado();
}
