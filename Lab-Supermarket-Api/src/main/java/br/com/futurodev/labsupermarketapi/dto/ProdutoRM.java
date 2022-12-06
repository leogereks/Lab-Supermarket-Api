package br.com.futurodev.labsupermarketapi.dto;

import br.com.futurodev.labsupermarketapi.model.Categoria;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoRM {

    private Long id;
    private Categoria categoria;
    private String nome;
    private Double valor;
    private boolean status;
}
