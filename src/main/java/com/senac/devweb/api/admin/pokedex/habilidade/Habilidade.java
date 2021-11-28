package com.senac.devweb.api.admin.pokedex.habilidade;

import com.senac.devweb.api.admin.pokedex.pokemon.Pokemon;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "habilidade")
public class Habilidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_habilidade")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_pokemon", referencedColumnName="i_pokemon")
    private Pokemon pokemon;

    @NotNull(message = "O campo nome não pode ser nulo")
    @Size(min = 1, max = 100, message = "O campo nome deve conter entre 1 e 100 caracteres")
    @Column(name="nome")
    private String nome;

    @Column(name="descricao")
    private String descricao;
}
