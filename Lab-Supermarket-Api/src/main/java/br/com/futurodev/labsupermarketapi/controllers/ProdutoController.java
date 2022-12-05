package br.com.futurodev.labsupermarketapi.controllers;

import br.com.futurodev.labsupermarketapi.dto.ProdutoRM;
import br.com.futurodev.labsupermarketapi.input.ProdutoInput;
import br.com.futurodev.labsupermarketapi.model.Produto;
import br.com.futurodev.labsupermarketapi.service.ProdutoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "Produtos")
@RestController
@RequestMapping(value = "/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @ApiOperation("Valor das compras (produtos status 'true')")
    @GetMapping(value = "/total")
    public String totalCompras(){
        Double total = produtoService.totalCompras();
        return "O total das compras foi de R$ " + total;
    }

    @ApiOperation("Listar todos os produtos cadastrados")
    @GetMapping(value = "/lista", produces = "application/json")
    public ResponseEntity<List<ProdutoRM>> listaProdutos() {
        List<Produto> produtos = produtoService.ListarProdutos();
        List<ProdutoRM> produtosRepresentationModel = toCollectionModel(produtos);
        return new ResponseEntity<List<ProdutoRM>>(produtosRepresentationModel, HttpStatus.OK);
    }

    @ApiOperation("Salvaum produto")
    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<ProdutoRM> cadastra(@RequestBody ProdutoInput produtoInput) {
        Produto produto = produtoService.salva(toDomainObject(produtoInput));
        return new ResponseEntity<ProdutoRM>(toModel(produto), HttpStatus.CREATED);
    }

    @ApiOperation("Atualiza um produto")
    @PutMapping(value = "/", produces = "application/json")
    public ResponseEntity<ProdutoRM> atualiza(@RequestBody ProdutoInput produtoInput) {
        Produto produto = produtoService.salva(toDomainObject(produtoInput));
        return new ResponseEntity<ProdutoRM>(toModel(produto), HttpStatus.OK);
    }

    @ApiOperation("Deleta um produto")
    @DeleteMapping
    @ResponseBody
    public ResponseEntity<String> deleta(@RequestParam Long idProduto) {
        produtoService.deleta(idProduto);
        return new ResponseEntity<String>("O produto foi deletado com sucesso.", HttpStatus.OK);
    }

    @ApiOperation("Busca produto utilizando ID")
    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<ProdutoRM> buscaId(@RequestParam(name = "id") Long idProduto) {
        Produto produto = produtoService.busca(idProduto);
        ProdutoRM produtosRepresentationModel = toModel(produto);
        return new ResponseEntity<ProdutoRM>(produtosRepresentationModel, HttpStatus.OK);
    }

    @ApiOperation("Busca um produto através de seu nome")
    @GetMapping(value = "/buscaPorNome", produces = "application/json")
    public ResponseEntity<List<ProdutoRM>> buscarPorNome(@RequestParam(name = "nome") String nome) {
        List<Produto> produtos = produtoService.buscaNome(nome);
        List<ProdutoRM> produtosRepresentationModel = toCollectionModel(produtos);
        return new ResponseEntity<List<ProdutoRM>>(produtosRepresentationModel, HttpStatus.OK);
    }

    @ApiOperation("Busca o produto pelo ID através URL")
    @GetMapping(value = "/{idProduto}", produces = "application/json")
    public ResponseEntity<ProdutoRM> buscar(@PathVariable(value = "idProduto") Long idProduto) {
        Produto produto = produtoService.busca(idProduto);
        ProdutoRM produtoRM = toModel(produto);
        return new ResponseEntity<ProdutoRM>(produtoRM, HttpStatus.OK);
    }

    private List<ProdutoRM> toCollectionModel(List<Produto> produtos){
        return produtos.stream().map(produto -> toModel(produto)).collect(Collectors.toList());
    }

    private Produto toDomainObject(ProdutoInput produtoInput){
        Produto produto = new Produto();
        produto.setId(produtoInput.getId());
        produto.setCategoria(produtoInput.getCategoria());
        produto.setNomeProduto(produtoInput.getNomeInput());
        produto.setValorProduto(produtoInput.getValorInput());
        produto.setStatusProduto(produtoInput.isStatus());
        return produto;
    }
    private ProdutoRM toModel(Produto produto){
        ProdutoRM produtoRM = new ProdutoRM();
        produtoRM.setId(produto.getId());
        produtoRM.setCategoria(produto.getCategoria());
        produtoRM.setNomeProduto(produto.getNomeProduto());
        produtoRM.setValorProduto(produto.getValorProduto());
        produtoRM.setStatusProduto(produto.isStatusProduto());
        return produtoRM;
    }
}
