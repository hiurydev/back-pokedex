package com.senac.devweb.api.admin.pokedex.pokemon;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.senac.devweb.api.admin.pokedex.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PokemonService {
    private final PokemonRepository pokemonRepository;

    public List<Pokemon> getAllPokemon() {
        return this.pokemonRepository.findAll();
    }

    public Pokemon getProduto(Integer id) {
        BooleanExpression filter =
                com.senac.devweb.api.admin.pokedex.pokemon.QPokemon.pokemon.id.eq(id);
        return this.pokemonRepository.findOne(filter)
                .orElseThrow(() -> new NotFoundException("Pokemon não encontrada."));
    }
}
