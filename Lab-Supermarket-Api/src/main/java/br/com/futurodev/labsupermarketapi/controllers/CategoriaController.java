package br.com.futurodev.labsupermarketapi.controllers;


import br.com.futurodev.labsupermarketapi.dto.CategoriaRM;
import br.com.futurodev.labsupermarketapi.input.CategoriaInput;
import br.com.futurodev.labsupermarketapi.model.Categoria;
import br.com.futurodev.labsupermarketapi.service.CategoriaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "Categorias")
@RestController
@RequestMapping (value = "/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    private CategoriaRM toModel(Categoria categoria){
        CategoriaRM categoriaRM = new CategoriaRM();
        categoriaRM.setId(categoria.getId());
        categoriaRM.setNome(categoria.getNomeCategoria());
        categoriaRM.setDescricao(categoria.getDescricaoCategoria());
        return categoriaRM;
    }

    private List<CategoriaRM> toCollectionModel(List<Categoria> categorias){
        return categorias.stream().map(categoria -> toModel(categoria)).collect(Collectors.toList());
    }

    private Categoria toDomainObject(CategoriaInput categoriaInput){
        Categoria categoria = new Categoria();
        categoria.setId(categoriaInput.getIdInput());
        categoria.setNomeCategoria(categoriaInput.getNomeInput());
        categoria.setDescricaoCategoria(categoriaInput.getDescricaoInput());
        return categoria;
    }

    @ApiOperation("Listar todas as categorias cadastradas")
    @GetMapping(value = "/lista", produces = "application/json")
    public ResponseEntity<List<CategoriaRM>> listaCategorias() {
        List<Categoria> categorias = categoriaService.ListaCategorias();
        List<CategoriaRM> categoriasRM = toCollectionModel(categorias);
        return new ResponseEntity<List<CategoriaRM>>(categoriasRM, HttpStatus.OK);
    }

    @ApiOperation("Salvar categoria")
    @PostMapping(value = "/",produces = "application/json")
    public ResponseEntity<CategoriaRM>cadastra(@RequestBody CategoriaInput categoriaInput){
        Categoria categoria = categoriaService.salva(toDomainObject(categoriaInput));
        return new ResponseEntity<CategoriaRM>(toModel(categoria), HttpStatus.CREATED);
    }

    @ApiOperation("Deletar categoria")
    @DeleteMapping(value = "/")
    @ResponseBody
    public ResponseEntity<String>deleta(@RequestParam Long idCategoria){
        categoriaService.delete(idCategoria);
        return new ResponseEntity<String>("A categoria foi deletada com sucesso.",HttpStatus.OK);
    }

    @ApiOperation("Busca categoria utilizando ID")
    @GetMapping(value = "/",produces = "appplication/json")
    public ResponseEntity<CategoriaRM>buscaId(@RequestParam(name = "id")Long idCategoria){
        Categoria categoria=categoriaService.busca(idCategoria);
        CategoriaRM categoriaRM =toModel(categoria);
        return new ResponseEntity<CategoriaRM>(categoriaRM,HttpStatus.OK);
    }

    @ApiOperation("Busca categoria atraves da descriçao")
    @GetMapping(value = "/buscarDescricao", produces = "application/json")
    public ResponseEntity<List<CategoriaRM>> buscaDescricao(@RequestParam(name = "descricao") String descricao) {
        List<Categoria> categorias = categoriaService.buscaDescricao(descricao);
        List<CategoriaRM> categoriasRM = toCollectionModel(categorias);
        return new ResponseEntity<List<CategoriaRM>>(categoriasRM, HttpStatus.OK);
    }

    @ApiOperation("Busca categoria pelo ID URL")
    @GetMapping(value = "/{idCategoria}", produces = "application/json")
    public ResponseEntity<CategoriaRM> buscaUrl(@PathVariable(value = "idCategoria") Long idCategoria) {
        Categoria categoria = categoriaService.busca(idCategoria);
        CategoriaRM categoriaRM = toModel(categoria);
        return new ResponseEntity<CategoriaRM>(categoriaRM, HttpStatus.OK);
    }

}
