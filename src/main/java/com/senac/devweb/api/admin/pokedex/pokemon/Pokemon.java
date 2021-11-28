package com.senac.devweb.api.admin.pokedex.pokemon;

import com.senac.devweb.api.admin.pokedex.habilidade.Habilidade;
import com.senac.devweb.api.admin.pokedex.utils.TipoPokemon;
import com.senac.devweb.api.admin.pokedex.vantagem.Vantagem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "pokemon")
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_pokemon")
    private Integer id;

    @NotNull(message = "O campo nome não pode ser nulo")
    @Size(min = 1, max = 100, message = "O campo nome deve conter entre 1 e 100 caracteres")
    @Column(name="nome")
    private String nome;

    @NotNull(message = "O campo porte não pode ser nulo")
    @Column(name="porte")
    private Porte porte;

    @NotNull(message = "O campo tipo não pode ser nulo")
    @Column(name="tipo")
    @Enumerated(EnumType.STRING)
    private TipoPokemon tipoPokemon;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "pokemon", orphanRemoval = true)
    private List<Vantagem> vantagens  = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "pokemon", orphanRemoval = true)
    private List<Habilidade> habilidades = new ArrayList<>();


    enum Porte {
        PEQUENO,
        MEDIO,
        GRANDE
    }
}
