package com.senac.devweb.api.admin.pokedex.pokemon;

import com.senac.devweb.api.admin.pokedex.utils.TipoPokemon;
import com.senac.devweb.api.admin.pokedex.habilidade.Habilidade;
import com.senac.devweb.api.admin.pokedex.vantagem.Vantagem;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

public interface PokemonRepresentation {

    @Data
    @Getter
    @Setter
    class CreateOrUpdate {

        // fazer o resto apos validar
        @NotNull(message = "O campo nome não pode ser nulo")
        @Size(min = 1, max = 100, message = "O campo nome deve conter entre 1 e 100 caracteres")
        private String nome;

        @NotNull(message = "O campo porte não pode ser nulo")
        private Pokemon.Porte porte;

        @NotNull(message = "O campo tipo não pode ser nulo")
        private TipoPokemon tipoPokemon;
    }

    @Data
    @Getter
    @Setter
    @Builder
    class Detail {

        private Integer id;
        private String nome;
        private Pokemon.Porte porte;
        private TipoPokemon tipoPokemon;

        public static Detail from(Pokemon pokemon) {
            return Detail.builder()
                    .id(pokemon.getId())
                    .nome(pokemon.getNome())
                    .porte(pokemon.getPorte())
                    .tipoPokemon(pokemon.getTipoPokemon())
                    .build();
        }
    }

    @Data
    @Getter
    @Setter
    @Builder
    class Lista {
        private Integer id;
        private String nome;
        private Pokemon.Porte porte;
        private TipoPokemon tipoPokemon;
        private Vantagem vantagem;
        private Habilidade habilidade;

        private static Lista from(Pokemon pokemon) {
            return Lista.builder()
                    .id(pokemon.getId())
                    .nome(pokemon.getNome())
                    .porte(pokemon.getPorte())
                    .tipoPokemon(pokemon.getTipoPokemon())
                    .build();
        }

        public static List<Lista> from(List<Pokemon> pokemons) {
            return pokemons.stream()
                    .map(Lista::from)
                    .collect(Collectors.toList());
        }
    }
}
