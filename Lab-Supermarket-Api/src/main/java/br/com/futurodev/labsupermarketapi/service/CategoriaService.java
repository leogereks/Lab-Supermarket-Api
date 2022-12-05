package br.com.futurodev.labsupermarketapi.service;

import br.com.futurodev.labsupermarketapi.model.Categoria;
import br.com.futurodev.labsupermarketapi.repository.CategoriaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepo categoriaRepo;

    public List<Categoria>ListaCategorias() {
        return categoriaRepo.findAll();
    }

    public Categoria salva(Categoria categoria) {
        return categoriaRepo.save(categoria);
    }
    public void delete(Long idCategoria) {
        categoriaRepo.deleteById(idCategoria);
    }

    public Categoria busca(Long idCategoria) {
        return categoriaRepo.findById(idCategoria).get();
    }

    public List<Categoria>buscaDescricao(String descricao){
        return categoriaRepo.buscaDescricao(descricao);
    }
}
