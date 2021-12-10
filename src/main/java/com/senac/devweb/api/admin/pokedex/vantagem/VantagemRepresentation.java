package com.senac.devweb.api.admin.pokedex.vantagem;

import com.senac.devweb.api.admin.pokedex.pokemon.PokemonRepresentation;
import com.senac.devweb.api.admin.pokedex.utils.TipoPokemon;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

public interface VantagemRepresentation {
    @Data
    @Getter
    @Setter
    class CreateOrUpdate {
        @NotNull(message = "O pokemon é obrigatório")
        private Integer pokemon;

        @NotNull(message = "O campo pro não pode ser nulo")
        private Boolean pro;

        @NotNull(message = "O campo imune não pode ser nulo")
        private Boolean imune;

        @NotNull(message = "O campo tipo pokemon não pode ser nulo")
        private TipoPokemon tipoPokemon;

    }

    @Data
    @Getter
    @Setter
    @Builder
    class Detail {
        private Integer id;
        private Boolean pro;
        private Boolean imune;
        private TipoPokemon tipoPokemon;
        private Integer idPokemon;

        public static VantagemRepresentation.Detail from(Vantagem vantagem) {
            return Detail.builder()
                    .id(vantagem.getId())
                    .pro(vantagem.getPro())
                    .imune(vantagem.getImune())
                    .tipoPokemon(vantagem.getTipoPokemon())
                    .idPokemon(vantagem.getPokemon().getId())
                    .build();
        }
        public static List<Detail> from(List<Vantagem> vantagens) {
            return vantagens.stream()
                    .map(VantagemRepresentation.Detail::from)
                    .collect(Collectors.toList());
        }
    }

    @Data
    @Getter
    @Setter
    @Builder
    class Lista {
        private Integer id;
        private Boolean pro;
        private Boolean imune;
        private TipoPokemon tipoPokemon;
        private PokemonRepresentation.Detail pokemon;

        private static VantagemRepresentation.Lista from(Vantagem vantagem) {
            return VantagemRepresentation.Lista.builder()
                    .id(vantagem.getId())
                    .pro(vantagem.getPro())
                    .imune(vantagem.getImune())
                    .tipoPokemon(vantagem.getTipoPokemon())
                    .pokemon(PokemonRepresentation.Detail.from(vantagem.getPokemon()))
                    .build();
        }

        public static List<Lista> from(List<Vantagem> vantagens) {
            return vantagens.stream()
                    .map(VantagemRepresentation.Lista::from)
                    .collect(Collectors.toList());
        }
    }
}
