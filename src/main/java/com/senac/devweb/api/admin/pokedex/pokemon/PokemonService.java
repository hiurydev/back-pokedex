package com.senac.devweb.api.admin.pokedex.pokemon;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.senac.devweb.api.admin.pokedex.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PokemonService {
    private final PokemonRepository pokemonRepository;

    public Pokemon save(PokemonRepresentation.CreateOrUpdate createOrUpdate) {

        Pokemon pokemon = Pokemon.builder()
                .nome(createOrUpdate.getNome())
                .porte(createOrUpdate.getPorte())
                .tipoPokemon(createOrUpdate.getTipoPokemon())
                .build();

        return this.pokemonRepository.save(pokemon);
    }

    public Pokemon update(Integer id, PokemonRepresentation.CreateOrUpdate createOrUpdate) {

        Pokemon pokemon = this.getPokemon(id);

        Pokemon newPokemon = pokemon.toBuilder()
                .nome(createOrUpdate.getNome())
                .porte(createOrUpdate.getPorte())
                .tipoPokemon(createOrUpdate.getTipoPokemon())
                .build();

        return this.pokemonRepository.save(newPokemon);
    }

    public void delete(Integer id) {
        Pokemon pokemon = this.getPokemon(id);

        this.pokemonRepository.delete(pokemon);
    }

    public List<Pokemon> getAllPokemon() {
        return this.pokemonRepository.findAll();
    }

    public Pokemon getPokemon(Integer id) {
        BooleanExpression filter =
                com.senac.devweb.api.admin.pokedex.pokemon.QPokemon.pokemon.id.eq(id);
        return this.pokemonRepository.findOne(filter)
                .orElseThrow(() -> new NotFoundException("Pokemon não encontrado."));
    }
}
