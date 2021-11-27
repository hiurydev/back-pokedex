package com.senac.devweb.api.admin.pokedex.pokemon;

import com.querydsl.core.types.Predicate;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PokemonRepository extends CrudRepository<Pokemon, Long>,
        QuerydslPredicateExecutor<Pokemon> {

    List<Pokemon> findAll(Predicate filter);
    List<Pokemon> findAll();
}