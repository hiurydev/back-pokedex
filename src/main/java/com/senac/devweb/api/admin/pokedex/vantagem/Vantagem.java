package com.senac.devweb.api.admin.pokedex.vantagem;

import com.senac.devweb.api.admin.pokedex.pokemon.Pokemon;
import com.senac.devweb.api.admin.pokedex.utils.TipoPokemon;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "vantagem")
public class Vantagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_vantagem")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_pokemon", referencedColumnName="id_pokemon")
    private Pokemon pokemon;

    @NotNull(message = "O campo pro não pode ser nulo")
    @Column(name = "pro")
    private Boolean pro;

    @NotNull(message = "O campo imune não pode ser nulo")
    @Column(name = "imune")
    private Boolean imune;

    @NotNull(message = "O campo tipo não pode ser nulo")
    @Column(name = "tipo")
    private TipoPokemon tipoPokemon;
}
