package br.com.futurodev.labsupermarketapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "produtos")
@Getter
@Setter
public class Produto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_categoria", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_categoria"))
    private Categoria categoriaProduto;

    @Column(nullable = false)
    private String nomeProduto;

    @Column(nullable = false, precision = 2)
    private Double valorProduto;

    private boolean statusProduto;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return id.equals(produto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
