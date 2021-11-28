package com.senac.devweb.api.admin.pokedex.vantagem;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.senac.devweb.api.admin.pokedex.exceptions.NotFoundException;
import com.senac.devweb.api.admin.pokedex.pokemon.Pokemon;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VantagemService {

    private final VantagemRepository vantagemRepository;

    public Vantagem save(VantagemRepresentation.CreateOrUpdate createOrUpdate,
                           Pokemon pokemon) {

        Vantagem vantagem = Vantagem.builder()
                .pro(createOrUpdate.getPro())
                .imune(createOrUpdate.getImune())
                .tipoPokemon(createOrUpdate.getTipoPokemon())
                .pokemon(pokemon)
                .build();

        return this.vantagemRepository.save(vantagem);
    }

    public Vantagem update(Integer id, VantagemRepresentation.CreateOrUpdate createOrUpdate, Pokemon pokemon) {

        Vantagem vantagem = this.getVantagem(id);

        Vantagem newVantagem = vantagem.toBuilder()
                .pro(createOrUpdate.getPro())
                .imune(createOrUpdate.getImune())
                .tipoPokemon(createOrUpdate.getTipoPokemon())
                .pokemon(pokemon)
                .build();

        return this.vantagemRepository.save(newVantagem);
    }

    public void delete(Integer id) {
        Vantagem vantagem = this.getVantagem(id);

        this.vantagemRepository.delete(vantagem);
    }

    public List<Vantagem> getAllVantagens() {
        return this.vantagemRepository.findAll();
    }

    public Vantagem getVantagem(Integer id) {
        BooleanExpression filter =
                QVantagem.vantagem.id.eq(id);
        return this.vantagemRepository.findOne(filter)
                .orElseThrow(() -> new NotFoundException("Vantagem não encontrada."));
    }
}
