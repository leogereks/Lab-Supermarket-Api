package br.com.futurodev.labsupermarketapi.input;

import lombok.Getter;
import lombok.Setter;

//@Getter
//@Setter
public class CategoriaInput {

    private Long idInput;
    private String nomeInput;
    private String descricaoInput;

    public Long getIdInput() {
        return idInput;
    }

    public void setIdInput(Long idInput) {
        this.idInput = idInput;
    }

    public String getNomeInput() {
        return nomeInput;
    }

    public void setNomeInput(String nomeInput) {
        this.nomeInput = nomeInput;
    }

    public String getDescricaoInput() {
        return descricaoInput;
    }

    public void setDescricaoInput(String descricaoInput) {
        this.descricaoInput = descricaoInput;
    }
}
