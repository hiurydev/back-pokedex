package com.senac.devweb.api.admin.pokedex.utils;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
@Builder
public class Paginacao {

    private Integer tamanhoPagina;
    private Integer paginaSelecionada;
    private boolean proximaPagina;
    private List<?> conteudo;
}
