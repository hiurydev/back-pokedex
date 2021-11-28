package com.senac.devweb.api.admin.pokedex.vantagem;

import com.querydsl.core.types.Predicate;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VantagemRepository extends CrudRepository<Vantagem, Long>,
        QuerydslPredicateExecutor<Vantagem> {

    List<Vantagem> findAll(Predicate filter);
    List<Vantagem> findAll();
}