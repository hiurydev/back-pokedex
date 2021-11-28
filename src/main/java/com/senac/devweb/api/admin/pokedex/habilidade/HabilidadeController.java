package com.senac.devweb.api.admin.pokedex.habilidade;

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
@RequestMapping("/habilidade")
@AllArgsConstructor
@CrossOrigin("*")
public class HabilidadeController {

    private final HabilidadeService habilidadeService;
    private final HabilidadeRepository habilidadeRepository;
    private final PokemonService pokemonService;

    @PostMapping("/")
    public ResponseEntity<HabilidadeRepresentation.Detail> createHabilidade(
            @Valid @RequestBody HabilidadeRepresentation.CreateOrUpdate createOrUpdate) {

        Pokemon pokemon = this.pokemonService.getPokemon(createOrUpdate.getPokemon());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(HabilidadeRepresentation
                        .Detail.from(this.habilidadeService.save(createOrUpdate, pokemon)));

    }

    @PutMapping ("/{id}")
    public ResponseEntity<HabilidadeRepresentation.Detail> updateHabilidade(
            @PathVariable("id") Integer id,
            @Valid @RequestBody HabilidadeRepresentation.CreateOrUpdate createOrUpdate) {

        Pokemon pokemon = this.pokemonService.getPokemon(createOrUpdate.getPokemon());

        return ResponseEntity.status(HttpStatus.OK)
                .body(HabilidadeRepresentation
                        .Detail.from(this.habilidadeService.update(id, createOrUpdate, pokemon)));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PokemonRepresentation.Detail> deleteHabilidade(
            @PathVariable("id") Integer id) {

        this.habilidadeService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/")
    public ResponseEntity<Paginacao> getAll(
            @QuerydslPredicate(root = Habilidade.class) Predicate filtroHabilidade,
            @RequestParam(name = "filtro", required = false, defaultValue = "") String filtro,
            @RequestParam(name = "paginaSelecionada", defaultValue = "0") Integer paginaSelecionada,
            @RequestParam(name = "tamanhoPagina", defaultValue = "20") Integer tamanhoPagina) {

        Pageable pageRequest = PageRequest.of(paginaSelecionada, tamanhoPagina);

        Page<Habilidade> habilidadeList = this.habilidadeRepository.findAll(filtroHabilidade, pageRequest);

        Paginacao paginacao = Paginacao.builder()
                .conteudo(HabilidadeRepresentation.Lista
                        .from(habilidadeList.getContent()))
                .paginaSelecionada(paginaSelecionada)
                .tamanhoPagina(tamanhoPagina)
                .build();

        return ResponseEntity.ok(paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HabilidadeRepresentation.Detail> getOneHabilidade(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(HabilidadeRepresentation.Detail.from(this.habilidadeService.getHabilidade(id)));
    }
}
