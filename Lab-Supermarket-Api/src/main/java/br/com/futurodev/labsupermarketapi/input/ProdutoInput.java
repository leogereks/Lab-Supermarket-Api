package br.com.futurodev.labsupermarketapi.input;

import br.com.futurodev.labsupermarketapi.model.Categoria;
import lombok.Getter;
import lombok.Setter;

//@Getter
//@Setter
public class ProdutoInput {

    private Long id;
    private String nomeInput;
    private Double valorInput;
    private boolean status;
    private Categoria categoria;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeInput() {
        return nomeInput;
    }

    public void setNomeInput(String nomeInput) {
        this.nomeInput = nomeInput;
    }

    public Double getValorInput() {
        return valorInput;
    }

    public void setValorInput(Double valorInput) {
        this.valorInput = valorInput;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
