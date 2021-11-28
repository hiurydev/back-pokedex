package com.senac.devweb.api.admin.pokedex.habilidade;

import com.querydsl.core.types.Predicate;
import com.senac.devweb.api.admin.pokedex.pokemon.Pokemon;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HabilidadeRepository extends CrudRepository<Habilidade, Long>,
        QuerydslPredicateExecutor<Habilidade> {

    List<Habilidade> findAll(Predicate filter);
    List<Habilidade> findAll();
}