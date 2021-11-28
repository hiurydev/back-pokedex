package com.senac.devweb.api.admin.pokedex.vantagem;

import com.querydsl.core.types.Predicate;
import com.senac.devweb.api.admin.pokedex.pokemon.Pokemon;
import com.senac.devweb.api.admin.pokedex.pokemon.PokemonRepresentation;
import com.senac.devweb.api.admin.pokedex.pokemon.PokemonService;
import com.senac.devweb.api.admin.pokedex.utils.Paginacao;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/vantagem")
@AllArgsConstructor
@CrossOrigin("*")
public class VantagemController {

    private final VantagemService vantagemService;
    private final VantagemRepository vantagemRepository;
    private final PokemonService pokemonService;

    @PostMapping("/")
    public ResponseEntity<VantagemRepresentation.Detail> createVantagem(
            @Valid @RequestBody VantagemRepresentation.CreateOrUpdate createOrUpdate) {

        Pokemon pokemon = this.pokemonService.getPokemon(createOrUpdate.getPokemon());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(VantagemRepresentation
                        .Detail.from(this.vantagemService.save(createOrUpdate, pokemon)));

    }

    @PutMapping ("/{id}")
    public ResponseEntity<VantagemRepresentation.Detail> updateVantagem(
            @PathVariable("id") Integer id,
            @Valid @RequestBody VantagemRepresentation.CreateOrUpdate createOrUpdate) {

        Pokemon pokemon = this.pokemonService.getPokemon(createOrUpdate.getPokemon());

        return ResponseEntity.status(HttpStatus.OK)
                .body(VantagemRepresentation
                        .Detail.from(this.vantagemService.update(id, createOrUpdate, pokemon)));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PokemonRepresentation.Detail> deleteVantagem(
            @PathVariable("id") Integer id) {

        this.vantagemService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/")
    public ResponseEntity<Paginacao> getAll(
            @QuerydslPredicate(root = Vantagem.class) Predicate filtroVantagem,
            @RequestParam(name = "filtro", required = false, defaultValue = "") String filtro,
            @RequestParam(name = "paginaSelecionada", defaultValue = "0") Integer paginaSelecionada,
            @RequestParam(name = "tamanhoPagina", defaultValue = "20") Integer tamanhoPagina) {

        Pageable pageRequest = PageRequest.of(paginaSelecionada, tamanhoPagina);

        Page<Vantagem> vantagemList = this.vantagemRepository.findAll(filtroVantagem, pageRequest);

        Paginacao paginacao = Paginacao.builder()
                .conteudo(VantagemRepresentation.Lista
                        .from(vantagemList.getContent()))
                .paginaSelecionada(paginaSelecionada)
                .tamanhoPagina(tamanhoPagina)
                .build();

        return ResponseEntity.ok(paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VantagemRepresentation.Detail> getOneVantagem(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(VantagemRepresentation.Detail.from(this.vantagemService.getVantagem(id)));
    }
}
