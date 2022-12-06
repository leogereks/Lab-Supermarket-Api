package br.com.futurodev.labsupermarketapi.input;

import br.com.futurodev.labsupermarketapi.model.Categoria;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoInput {

    private Long id;
    private String nomeProduto;
    private Double valorProduto;
    private boolean statusProduto;
    private Categoria categoriaProduto;

}
