package com.senac.devweb.api.admin.pokedex.habilidade;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.senac.devweb.api.admin.pokedex.exceptions.NotFoundException;
import com.senac.devweb.api.admin.pokedex.pokemon.Pokemon;
import com.senac.devweb.api.admin.pokedex.pokemon.PokemonRepresentation;
import com.senac.devweb.api.admin.pokedex.pokemon.PokemonService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HabilidadeService {

    private final HabilidadeRepository habilidadeRepository;

    public Habilidade save(HabilidadeRepresentation.CreateOrUpdate createOrUpdate,
                          Pokemon pokemon) {

        Habilidade habilidade = Habilidade.builder()
                .nome(createOrUpdate.getNome())
                .descricao(createOrUpdate.getDescricao())
                .pokemon(pokemon)
                .build();

        return this.habilidadeRepository.save(habilidade);
    }

    public Habilidade update(Integer id, HabilidadeRepresentation.CreateOrUpdate createOrUpdate, Pokemon pokemon) {

        Habilidade habilidade = this.getHabilidade(id);

        Habilidade newHabilidade = habilidade.toBuilder()
                .nome(createOrUpdate.getNome())
                .descricao(createOrUpdate.getDescricao())
                .pokemon(pokemon)
                .build();

        return this.habilidadeRepository.save(newHabilidade);
    }

    public void delete(Integer id) {
        Habilidade habilidade = this.getHabilidade(id);

        this.habilidadeRepository.delete(habilidade);
    }

    public List<Habilidade> getAllHabilidades() {
        return this.habilidadeRepository.findAll();
    }

    public Habilidade getHabilidade(Integer id) {
        BooleanExpression filter =
                com.senac.devweb.api.admin.pokedex.habilidade.QHabilidade.habilidade.id.eq(id);
        return this.habilidadeRepository.findOne(filter)
                .orElseThrow(() -> new NotFoundException("Habilidade não encontrada."));
    }
}
