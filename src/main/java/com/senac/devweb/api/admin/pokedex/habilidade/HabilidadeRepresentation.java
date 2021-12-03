package com.senac.devweb.api.admin.pokedex.habilidade;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

public interface HabilidadeRepresentation {
    @Data
    @Getter
    @Setter
    class CreateOrUpdate {
        @NotNull(message = "O pokemon é obrigatório")
        private Integer pokemon;

        @NotNull(message = "O campo nome não pode ser nulo")
        private String nome;

        private String descricao;
    }

    @Data
    @Getter
    @Setter
    @Builder
    class Detail {

        private Integer id;
        private String nome;
        private String descricao;

        public static HabilidadeRepresentation.Detail from(Habilidade habilidade) {
            return HabilidadeRepresentation.Detail.builder()
                    .id(habilidade.getId())
                    .nome(habilidade.getNome())
                    .descricao(habilidade.getDescricao())
                    .build();
        }

        public static List<HabilidadeRepresentation.Detail> from(List<Habilidade> habilidades) {
            return habilidades.stream()
                    .map(HabilidadeRepresentation.Detail::from)
                    .collect(Collectors.toList());
        }
    }

    @Data
    @Getter
    @Setter
    @Builder
    class Lista {
        private Integer id;
        private String nome;
        private String descricao;

        private static HabilidadeRepresentation.Lista from(Habilidade habilidade) {
            return HabilidadeRepresentation.Lista.builder()
                    .id(habilidade.getId())
                    .nome(habilidade.getNome())
                    .descricao(habilidade.getDescricao())
                    .build();
        }

        public static List<HabilidadeRepresentation.Lista> from(List<Habilidade> habilidades) {
            return habilidades.stream()
                    .map(HabilidadeRepresentation.Lista::from)
                    .collect(Collectors.toList());
        }
    }
}
