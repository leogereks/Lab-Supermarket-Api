package br.com.futurodev.labsupermarketapi.repository;

import br.com.futurodev.labsupermarketapi.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface CategoriaRepo extends JpaRepository<Categoria, Long> {
    @Query(value = "from Categoria c where upper(c.descricaoCategoria) like %?1%")
    ArrayList<Categoria>buscaDescricao(String descricao);
}
