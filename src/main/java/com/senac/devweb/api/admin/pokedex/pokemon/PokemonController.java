package com.senac.devweb.api.admin.pokedex.pokemon;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.senac.devweb.api.admin.pokedex.exceptions.NotFoundException;
import com.senac.devweb.api.admin.pokedex.utils.Paginacao;
import com.senac.devweb.api.admin.pokedex.pokemon.QPokemon;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/pokemon")
@AllArgsConstructor
@CrossOrigin("*")
public class PokemonController {

    private final PokemonService pokemonService;
    private final PokemonRepository pokemonRepository;

    @GetMapping("/")
    public ResponseEntity<Paginacao> getAll(
            @QuerydslPredicate(root = Pokemon.class) Predicate filtroPokemon,
            @RequestParam(name = "filtro", required = false, defaultValue = "") String filtro,
            @RequestParam(name = "paginaSelecionada", defaultValue = "0") Integer paginaSelecionada,
            @RequestParam(name = "tamanhoPagina", defaultValue = "20") Integer tamanhoPagina) {

        Pageable pageRequest = PageRequest.of(paginaSelecionada, tamanhoPagina);

        Page<Pokemon> pokemonList = this.pokemonRepository.findAll(filtroPokemon, pageRequest);

        Paginacao paginacao = Paginacao.builder()
                .conteudo(PokemonRepresentation.Lista
                        .from(pokemonList.getContent()))
                .paginaSelecionada(paginaSelecionada)
                .tamanhoPagina(tamanhoPagina)
                .build();

        return ResponseEntity.ok(paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PokemonRepresentation.Detail> getOneProduto(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(PokemonRepresentation.Detail.from(this.pokemonService.getProduto(id)));
    }

    // teste API
    @GetMapping("/get")
    public @ResponseBody ResponseEntity<String> get() {
        return new ResponseEntity<String>("GET Response", HttpStatus.OK);
    }
}
